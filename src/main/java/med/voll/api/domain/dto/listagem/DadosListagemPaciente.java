package med.voll.api.domain.dto.listagem;

import med.voll.api.domain.model.Paciente;

public record DadosListagemPaciente(Long id, String nome, String email, String telefone) {

	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone());
	}
}
