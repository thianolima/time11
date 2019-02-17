package br.com.time11.handlers;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ErrorDetail {
	
	String mensagem;
	String objeto;
	String campo;	
	String detalhes;
	
}
