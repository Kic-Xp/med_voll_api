package med.voll.api.domain.dto.listagem;

import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.Medico;

public record DadosListagemMedico(Long id,String nome, String email, String crm,
		Especialidade especialidade) {
	
	public DadosListagemMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
