package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.dto.atualizar.DadosAtualizaPaciente;
import med.voll.api.domain.dto.cadastro.DadosCadastraPaciente;
import med.voll.api.domain.dto.detalhados.DadosDetalhadosPaciente;
import med.voll.api.domain.dto.listagem.DadosListagemPaciente;
import med.voll.api.domain.model.Paciente;
import med.voll.api.domain.repository.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		var page =  pacienteRepository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(page);
	}
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhadosPaciente> detalhar(@PathVariable Long id) {
		var paciente = pacienteRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhadosPaciente(paciente));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhadosPaciente> cadastrar(@RequestBody DadosCadastraPaciente dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		pacienteRepository.save(paciente);
		
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhadosPaciente(paciente));
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhadosPaciente> atualizar(@RequestBody DadosAtualizaPaciente dados) {
		var paciente = pacienteRepository.getReferenceById(dados.id());
		paciente.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhadosPaciente(paciente));
	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity inativar(@PathVariable Long id) {
		var paciente = pacienteRepository.getReferenceById(id);
		paciente.inativar();
		return ResponseEntity.noContent().build();
	}
	
}
