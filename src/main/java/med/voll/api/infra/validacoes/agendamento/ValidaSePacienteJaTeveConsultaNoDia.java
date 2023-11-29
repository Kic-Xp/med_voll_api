package med.voll.api.infra.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidaSePacienteJaTeveConsultaNoDia implements InterfaceValida{

	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		
		var primeiroHorario = dados.data().withHour(7);
		var ultimoHorario = dados.data().withHour(18);
		
		var pacienteTemOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);
		
		if(pacienteTemOutraConsultaNoDia) {
			throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia!");
		}
		
	}
}
