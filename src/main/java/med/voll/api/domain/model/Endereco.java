package med.voll.api.domain.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.dto.cadastro.DadosEndereco;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String bairro;
	private String cidade;
	private String uf;
	
	public Endereco(DadosEndereco endereco) {
		this.logradouro = endereco.logradouro();
		this.numero = endereco.numero();
		this.complemento = endereco.complemento();
		this.cep = endereco.cep();
		this.bairro = endereco.bairro();
		this.cidade = endereco.cidade();
		this.uf = endereco.uf();
	}

	public void atualizarEndereco(DadosEndereco endereco) {

		if (endereco.logradouro() != null) {
			this.logradouro = endereco.logradouro();
		}
		if (endereco.numero() != null) {
			this.numero = endereco.numero();
		}
		if (endereco.complemento() != null) {
			this.complemento = endereco.complemento();
		}
		if (endereco.cep() != null) {
			this.cep = endereco.cep();
		}
		if (endereco.bairro() != null) {
			this.bairro = endereco.bairro();
		}
		if (endereco.cidade() != null) {
			this.cidade = endereco.cidade();
		}
		if (endereco.uf() != null) {
			this.uf = endereco.uf();
	}
}
}
