package med.voll.api.infra.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.domain.repository.MedicoRepository;

@Component
public class ValidaMedicoAtivo implements InterfaceValida{

	@Autowired
	private MedicoRepository repository;
	
	public void validar(DadosAgendamentoConsulta dados) {
		
		if(dados.idMedico() == null) {
			return;
		}
		
		Boolean medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
		
		if(!medicoEstaAtivo) {
			throw new RuntimeException("Não se pode agendar consulta com medico Excluído!");
		}
	}

}
