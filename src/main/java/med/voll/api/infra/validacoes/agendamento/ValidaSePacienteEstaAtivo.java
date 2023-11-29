package med.voll.api.infra.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.domain.repository.PacienteRepository;

@Component
public class ValidaSePacienteEstaAtivo implements InterfaceValida{

	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		var pacienteEstaAtivo = pacienteRepository.findAtivoById(dados.idPaciente());
		
		if(!pacienteEstaAtivo) {
			throw new RuntimeException("Paciente não está ativo");
		}
	}
}
