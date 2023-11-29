package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.domain.model.Usuario;
import med.voll.api.infra.exception.ValidacaoException;

@Service
public class TokenService {

	
	@Value("${med.voll.api.jwt.secret}")
	private String secret;
	
	public String generateToken(Usuario usuario) {
		try {
			System.out.println(secret);
			var algoritmo = Algorithm.HMAC256(secret);
			return JWT.create()
					.withIssuer("med.voll.api")
					.withSubject(usuario.getLogin())
					.withClaim("id", usuario.getId())
					.withExpiresAt(dataExpiracao())
					.sign(algoritmo);
			
		}catch(JWTCreationException e) {
			throw new RuntimeException("Erro ao criar Token" + e.getMessage());
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	public String getSubject(String tokenJWT) {
		try {
		var algoritmo = Algorithm.HMAC256(secret);
		return JWT.require(algoritmo)
				.withIssuer("med.voll.api")
				.build()
				.verify(tokenJWT)
				.getSubject();
		}catch(JWTVerificationException e) {
			throw new RuntimeException("Token JWT inválido");
		}
		}
}
