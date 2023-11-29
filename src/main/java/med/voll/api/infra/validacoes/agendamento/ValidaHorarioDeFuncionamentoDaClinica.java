package med.voll.api.infra.validacoes.agendamento;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidaHorarioDeFuncionamentoDaClinica implements InterfaceValida {

	public void validar(DadosAgendamentoConsulta dados) {
		
		var data = dados.data();
		var domingo = data.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAberturaDaClinica = data.getHour() < 7;
		var depoisDoFechamentoDaClinica = data.getHour() > 18;
		
		if (domingo || antesDaAberturaDaClinica || depoisDoFechamentoDaClinica) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
		}
	}
}
