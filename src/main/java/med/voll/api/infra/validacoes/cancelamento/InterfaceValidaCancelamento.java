package med.voll.api.infra.validacoes.cancelamento;

import med.voll.api.domain.dto.cancelamento.DadosCancelaConsulta;

public interface InterfaceValidaCancelamento {

	void validar(DadosCancelaConsulta dados);
}
