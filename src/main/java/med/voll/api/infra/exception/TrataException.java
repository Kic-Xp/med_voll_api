package med.voll.api.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TrataException{

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity NotFound404() {
		return ResponseEntity.notFound().build();
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity BadRequest400(MethodArgumentNotValidException ex) {
		var erros = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
	}
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<String> tratarErroRegraDeNegocio(ValidacaoException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<String> tratarErroRegraDeNegocio(HttpMessageNotReadableException ex) {
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<String> tratarErroAutenticacao(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
	}
	
	@ExceptionHandler(AuthenticationException.class)
	public ResponseEntity<String> tratarErroAutenticacao(AuthenticationException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erro de autenticação");
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<String> tratarErroAcessoNegado(AccessDeniedException ex) {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> tratarErroDesconhecido(Exception ex) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
	
	private record DadosErroValidacao(String campo, String mensagem) {
		public DadosErroValidacao(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
}
