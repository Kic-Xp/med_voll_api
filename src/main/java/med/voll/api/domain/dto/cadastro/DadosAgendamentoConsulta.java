package med.voll.api.domain.dto.cadastro;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.model.Especialidade;

public record DadosAgendamentoConsulta(
		Long idMedico,
		
		@NotNull
		Long idPaciente,
		
		@NotNull
		@Future
		LocalDateTime data,
		
		Especialidade especialidade
		
		) {
	
}
