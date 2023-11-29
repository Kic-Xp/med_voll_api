package med.voll.api.infra.validacoes.agendamento;

import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;

public interface InterfaceValida {

	void validar(DadosAgendamentoConsulta dados);
}
