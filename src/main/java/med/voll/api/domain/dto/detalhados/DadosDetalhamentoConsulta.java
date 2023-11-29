package med.voll.api.domain.dto.detalhados;

import java.time.LocalDateTime;

import med.voll.api.domain.model.Consulta;

public record DadosDetalhamentoConsulta (
		Long idConsulta,
		
		Long idMedico,
		
		Long idPaciente,
		
		LocalDateTime data
		
		){

	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
	}

}
