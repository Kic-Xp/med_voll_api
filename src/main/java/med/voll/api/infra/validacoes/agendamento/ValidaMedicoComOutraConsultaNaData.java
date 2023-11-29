package med.voll.api.infra.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidaMedicoComOutraConsultaNaData implements InterfaceValida{

	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		var medicoPossuiConsultaNaMesmaData = consultaRepository
				.existsByMedicoIdAndDataAndMotivoIsNull(dados.idMedico(), dados.data());
		
		if(medicoPossuiConsultaNaMesmaData) {
			throw new ValidacaoException("Médico já possui consulta na data informada!");
		}
		
	}
}
