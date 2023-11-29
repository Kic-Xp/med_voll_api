package med.voll.api.domain.dto.cadastro;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastraPaciente(
		@NotBlank String nome,
		@NotBlank String email, 
		@NotBlank String telefone, 
		@NotBlank String cpf, 
		@NotNull @Valid DadosEndereco endereco) {

}
