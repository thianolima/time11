package br.com.time11.services;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.time11.dtos.SMSDto;
import br.com.time11.dtos.TransactionDto;
import br.com.time11.entities.Dependente;
import br.com.time11.entities.Estabelecimento;
import br.com.time11.entities.Movimento;
import br.com.time11.enums.Categoria;
import br.com.time11.exceptions.SemSaldoException;
import br.com.time11.repositories.DependenteRepository;
import br.com.time11.repositories.EstabelecimentoRepository;
import br.com.time11.repositories.MovimentacaoRepository;

@Service
public class MovimentoService {

	@Value("${zoop.markeplace_id}")
	String markeplace_id;
	
	@Value("${zoop.url_base}")
	String url_base;
	
	@Value("${zoop.api_published_key}")
	String api_published_key;
	
	@Value("${zoop.sellerid}")
	String sellerid;
	
	@Autowired
	MovimentacaoRepository movimentoRepository;
	
	@Autowired
	DependenteRepository dependenteRepository;
	
	@Autowired
	EstabelecimentoRepository estabelecimetoRepository;
	
	@Autowired
	SMSService smsService;
	
	@PersistenceContext
	EntityManager em;
	
	@Transactional
	public Dependente inserir(TransactionDto dto) throws JsonProcessingException, IOException {
		
		Double saldo = dependenteRepository.pesquisarSaldo(dto.getIdZoopDependente());
		Double valTransacao = (double) (dto.getAmount() / 100);
		
		if(saldo >= valTransacao) {			
			
			//-------------------------------------------
			// VALIDA TRANSACAO NA ZOOP
			//-------------------------------------------
			RestTemplate rest = new RestTemplate();	
			
			String url = url_base + "/v1/marketplaces/" + markeplace_id + "/transactions";
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			String json = ow.writeValueAsString(dto);
			
			RequestEntity<?> request = RequestEntity				
					.post(URI.create(url))
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.header("Authorization", api_published_key)
					.body(json);
			
			ResponseEntity<String> response = rest.exchange(request, String.class);
			json = response.getBody();	
			
			//-------------------------------------------
			// ATUALIZANDO SALDO NO BANCO
			//-------------------------------------------
			Dependente dependente = dependenteRepository.pesquisarIdZoop(dto.getIdZoopDependente());						 
			dependente.setSaldo(saldo - valTransacao);
			dependenteRepository.save(dependente);
			
			//-------------------------------------------
			// SALVANDO MOVIMENTACAO
			//-------------------------------------------
			Estabelecimento estabelecimento = estabelecimetoRepository.findByIdzoop(sellerid).get();
			
			Date datahoje = new Date();
			
			Movimento movimento = Movimento.builder()
					.dataHora(datahoje)
					.dependente(dependente)
					.valor(valTransacao)
					.estabelecimento(estabelecimento)
					.build();
			
			movimentoRepository.save(movimento);			
			
			smsService.sendSms(SMSDto.builder()
					.to(movimento.getDependente().getTitular().getTelefone())
					.message(dependente.getNome() + " , acabou de realizar uma despesa de R$" + movimento.getValor() + 
							", no estabelecimento " + estabelecimento.getNome())
					.carrier("")
					.build());
			
			return movimento.getDependente();
			
		} else {		
			throw new SemSaldoException("Saldo insuficiente para compra !");
		}
	}
	
	@Transactional
	public List<Movimento> listarMovimento(Date inicio, 
										   Date fim,
	                                       Categoria categoria,
	                                       Integer idDependente) {
		
		SimpleDateFormat frm = new SimpleDateFormat("yyyy-dd-MM");
		
		Query query =  em.createQuery("select m from Movimento m" 
					+ " where m.dataHora between '" + frm.format(inicio) + "' and '" + frm.format(fim) +"'"
					+ " and m.estabelecimento.categoria = " + categoria.getValue() 
					+ " and m.dependente.id = " + idDependente);
		
		List<Movimento> retorno = query.getResultList();
		
		return retorno;
	}
	
	public List<Movimento> listar(){
		return movimentoRepository.findAll();
	}
}
