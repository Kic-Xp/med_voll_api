package med.voll.api.domain.dto.atualizar;

import jakarta.validation.Valid;
import med.voll.api.domain.dto.cadastro.DadosEndereco;

public record DadosAtualizaPaciente(Long id, String nome,String telefone,@Valid DadosEndereco endereco) {

}
