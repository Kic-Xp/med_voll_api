package med.voll.api.infra.validacoes.agendamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidaTempoAntecedenciaConsulta implements InterfaceValida{

	public void validar(DadosAgendamentoConsulta dados) {
		
		var dataConsulta = dados.data();
		var agora = LocalDateTime.now();
		
		var diferencaEmMinutos = Duration.between(agora, dataConsulta).toMinutes();
		
		if(diferencaEmMinutos < 30) {
			throw new ValidacaoException("Consulta deve ser agendada com no mínimo 30 minutos de antecedência");
		}
	}
}
