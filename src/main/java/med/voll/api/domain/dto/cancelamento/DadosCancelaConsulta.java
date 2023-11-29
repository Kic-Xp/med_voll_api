package med.voll.api.domain.dto.cancelamento;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.model.MotivoCancelamento;

public record DadosCancelaConsulta(
		
		@NotNull
		Long idConsulta,
		
		@NotNull
		MotivoCancelamento motivo) {

}
