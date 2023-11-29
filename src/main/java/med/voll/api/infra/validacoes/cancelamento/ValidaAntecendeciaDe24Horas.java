package med.voll.api.infra.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.cancelamento.DadosCancelaConsulta;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidaAntecendeciaDe24Horas implements InterfaceValidaCancelamento {

	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Override
	public void validar(DadosCancelaConsulta dados) {

		var dataConsulta = consultaRepository.getReferenceById(dados.idConsulta()).getData();
		var agora = LocalDateTime.now();
		
		var diferencaEmMinutos = Duration.between(agora, dataConsulta).toHours();
		
		if(diferencaEmMinutos < 24) {
			throw new ValidacaoException("Consulta deve ser Cancelada com antecedência de 24 horas");
		}
	}

}
