package med.voll.api.domain.dto.detalhados;

import med.voll.api.domain.model.Endereco;
import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.Medico;

public record DadosDetalhadosMedico(Long id, String nome, String email,
		String telefone, String crm,Especialidade especialidade, Endereco endereco, Boolean ativo) {

	public DadosDetalhadosMedico(Medico medico) {
		this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(),
				medico.getCrm(), medico.getEspecialidade(), medico.getEndereco(), medico.getAtivo());
	}
	
}
