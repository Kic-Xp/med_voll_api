package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.autenticacao.DadosAutenticacao;
import med.voll.api.domain.model.Usuario;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
		var autenticacaoToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
		var authetication = manager.authenticate(autenticacaoToken);
		
		var tokenJWT = tokenService.generateToken((Usuario) authetication.getPrincipal());
		
	return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
}
