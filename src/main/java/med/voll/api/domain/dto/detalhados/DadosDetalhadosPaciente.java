package med.voll.api.domain.dto.detalhados;

import med.voll.api.domain.model.Endereco;
import med.voll.api.domain.model.Paciente;

public record DadosDetalhadosPaciente(Long id, String nome, String email, String telefone, String cpf, Endereco endereco, Boolean ativo) {

	public DadosDetalhadosPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(),
				paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco(), paciente.getAtivo());
	}
	

}
