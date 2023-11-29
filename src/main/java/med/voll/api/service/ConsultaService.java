package med.voll.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.domain.dto.cancelamento.DadosCancelaConsulta;
import med.voll.api.domain.dto.detalhados.DadosDetalhamentoConsulta;
import med.voll.api.domain.model.Consulta;
import med.voll.api.domain.model.Medico;
import med.voll.api.domain.repository.ConsultaRepository;
import med.voll.api.domain.repository.MedicoRepository;
import med.voll.api.domain.repository.PacienteRepository;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.infra.validacoes.agendamento.InterfaceValida;
import med.voll.api.infra.validacoes.cancelamento.InterfaceValidaCancelamento;

@Service
public class ConsultaService {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private List<InterfaceValida> validacoesAgendamento;
	
	@Autowired
	private List<InterfaceValidaCancelamento> validacoesCancelamento;
	
	public DadosDetalhamentoConsulta agendarConsulta(DadosAgendamentoConsulta dados) {
		
		if(!pacienteRepository.existsById(dados.idPaciente())) {
			throw new ValidacaoException("Paciente não encontrado");
		}
		
		if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
			throw new ValidacaoException("Médico não encontrado");
		}
		
		validacoesAgendamento.forEach(v -> v.validar(dados));

		var medico = escolheMedico(dados);
		if(medico == null) {
			throw new ValidacaoException("Nenhum médico desponivel na data informada");
		}
		
		var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
		
		var consulta = new Consulta(null, medico, paciente, dados.data(), null);
		
		consultaRepository.save(consulta);
		
		return new DadosDetalhamentoConsulta(consulta);
	}

	private Medico escolheMedico(DadosAgendamentoConsulta dados) {
		if(dados.idMedico() != null) {
			return medicoRepository.getReferenceById(dados.idMedico());
		}
		if(dados.especialidade() == null) {
			throw new ValidacaoException("Especialidade precisa ser informada quando o médico não for informado!");
		}
		return medicoRepository.escolherMedicoAleatorio(dados.especialidade(), dados.data());
	}

	public void cancelaConsulta(DadosCancelaConsulta dados) {
		if(!consultaRepository.existsById(dados.idConsulta())) {
			throw new ValidacaoException("Consulta não encontrada!!!");
		}
		validacoesCancelamento.forEach(v -> v.validar(dados));
		
		var consulta = consultaRepository.getReferenceById(dados.idConsulta());
		consulta.cancelarConsulta(dados);
		
		consultaRepository.deleteById(dados.idConsulta());
		
	}
}
