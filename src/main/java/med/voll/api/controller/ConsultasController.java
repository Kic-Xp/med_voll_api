package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.cadastro.DadosAgendamentoConsulta;
import med.voll.api.domain.dto.cancelamento.DadosCancelaConsulta;
import med.voll.api.service.ConsultaService;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultasController {

	@Autowired
	private ConsultaService consultaService;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastraConsulta(@RequestBody @Valid DadosAgendamentoConsulta dados) {

		var consulta = consultaService.agendarConsulta(dados);
		return ResponseEntity.ok(consulta);
	}
	
	@DeleteMapping("/cancelar")
	@Transactional
	public ResponseEntity cancelaConsulta(@RequestBody @Valid DadosCancelaConsulta dados) {
		consultaService.cancelaConsulta(dados);
		return ResponseEntity.noContent().build();
	}
}
