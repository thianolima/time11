package br.com.time11.handlers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.time11.exceptions.SemSaldoException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {	
		return handleExceptionInternal(ex, getErros(ex), headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
		return handleExceptionInternal(ex, new ErrorDetail("Requisição inválida !", "", "", ex.getMessage()), headers, status, request);
	}
 
	@ExceptionHandler({DataIntegrityViolationException.class})
	public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
		return new ResponseEntity<Object>(new ErrorDetail("Registro Duplicado !","", "", ex.getMessage()),HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<Object> handleEmptyResultDataAccess(EmptyResultDataAccessException ex) {
		return new ResponseEntity<Object>(new ErrorDetail("Registro não encontrado no banco de dados !","", "", ex.getMessage()),HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler({RuntimeException.class})
	public ResponseEntity<Object> handleRunTimeExcepion(RuntimeException ex) {
		return new ResponseEntity<Object>(new ErrorDetail("Erro desconhecido !","", "", ex.getMessage()),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({SemSaldoException.class})
	public ResponseEntity<Object> handleSemSaldoException(SemSaldoException ex) {
		return new ResponseEntity<Object>(new ErrorDetail("Saldo Insuficiente para compra !","", "", ex.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	private List<ErrorDetail> getErros(MethodArgumentNotValidException ex) {
		List<ErrorDetail> erros = new ArrayList<>();
		for(FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
			ErrorDetail erro = new ErrorDetail(messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()),
											   fieldError.getObjectName(),                           
					                           fieldError.getField().toString(),                           					                           
					                           fieldError.toString());
			erros.add(erro);
		}
		return erros;
	}
}
