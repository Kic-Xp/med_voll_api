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

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.dto.atualizar.DadosAtualizaMedico;
import med.voll.api.domain.dto.cadastro.DadosCadastraMedico;
import med.voll.api.domain.dto.detalhados.DadosDetalhadosMedico;
import med.voll.api.domain.dto.listagem.DadosListagemMedico;
import med.voll.api.domain.model.Medico;
import med.voll.api.domain.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	@Autowired
	private MedicoRepository medicoRepository;
	
	@GetMapping
	public  ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
		try {
		var medicos =  medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(medicos);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@GetMapping("/{id}")
	public ResponseEntity<DadosDetalhadosMedico> detalhar(@PathVariable Long id) {
		var medico = medicoRepository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhadosMedico(medico));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhadosMedico> cadastrar(@Valid @RequestBody DadosCadastraMedico dados) {
		var medico = medicoRepository.save(new Medico(dados));
		
		var uri = org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhadosMedico(medico));
	}
	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhadosMedico> atualizar(@Valid @RequestBody DadosAtualizaMedico dados) {
		var medico = medicoRepository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		return ResponseEntity.ok(new DadosDetalhadosMedico(medico));
	}
//	@DeleteMapping
//	@Transactional
//	public void excluir(@Valid @RequestBody DadosAtualizaMedico dados) {
//		var medicoRemovido = medicoRepository.getReferenceById(dados.id());
//		medicoRepository.deleteById(medicoRemovido.getId());
//	}
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable @Valid Long id) {
		var medico = medicoRepository.getReferenceById(id);
		medico.excluir();
		return ResponseEntity.noContent().build();
	}
	
}
