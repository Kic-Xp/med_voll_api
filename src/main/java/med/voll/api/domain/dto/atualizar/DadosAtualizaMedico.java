package med.voll.api.domain.dto.atualizar;

import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.dto.cadastro.DadosEndereco;

public record DadosAtualizaMedico(@NotNull Long id, String nome, String telefone, DadosEndereco endereco) {

	
}
