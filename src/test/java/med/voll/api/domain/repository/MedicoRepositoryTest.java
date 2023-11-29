package med.voll.api.domain.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.dto.cadastro.DadosCadastraMedico;
import med.voll.api.domain.dto.cadastro.DadosCadastraPaciente;
import med.voll.api.domain.dto.cadastro.DadosEndereco;
import med.voll.api.domain.model.Consulta;
import med.voll.api.domain.model.Especialidade;
import med.voll.api.domain.model.Medico;
import med.voll.api.domain.model.Paciente;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private TestEntityManager entityManager;
	
	
	@Test
	@DisplayName("Deve Devolver nulo quando o médico cadastrado não está disponivel na data!")
	void testEscolherMedicoAleatorio() {
		
		var proximaSegundaAs10Horas = LocalDate.now()
				.with(TemporalAdjusters
					.next(DayOfWeek.MONDAY))
					.atTime(10, 0);
		
		var medico = cadastrarMedico("medico", "email@show.med", "123654", Especialidade.CARDIOLOGIA);
		var paciente = cadastrarPaciente("Paciente", "email@show.paciente", "80000000000");
		var consulta = cadastrarConsulta(medico, paciente, proximaSegundaAs10Horas);
		
		
		var medicoLivre = medicoRepository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAs10Horas);
		assertThat(medicoLivre).isNull();
		
	}
	
	@Test
	@DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
	void escolherMedicoAleatorioLivreNaDataCenario2() {
	     var proximaSegundaAs10 = LocalDate.now()
	                    .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
	                    .atTime(10, 0);
	    var medico = cadastrarMedico("Medico", "medico@voll.med", "123456", Especialidade.CARDIOLOGIA);

	    var medicoLivre = medicoRepository.escolherMedicoAleatorio(Especialidade.CARDIOLOGIA, proximaSegundaAs10);
	    System.out.println(medicoLivre.getNome());
	    assertThat(medicoLivre).isEqualTo(medico);
	}
	
	
	private Consulta cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
		var consulta = entityManager.persist(new Consulta(null, medico, paciente, data, null));
		return consulta;
	}
	
	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
		var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
		entityManager.persist(medico);
		return medico;
	
	}
	
	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
		var paciente = new Paciente(dadosPaciente(nome, email, cpf));
		entityManager.persist(paciente);
		return paciente;
	}
	
	private DadosCadastraMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
		return new DadosCadastraMedico(
			nome,
			email,
			"3832233223",
			crm,
			especialidade,
			dadosEndereco()
		);
	}
	
	private DadosCadastraPaciente dadosPaciente(String nome, String email, String cpf) {
		return new DadosCadastraPaciente(
			nome,
			email,
			"3832233223",
			cpf,
			dadosEndereco()
		);
	}
	
	private DadosEndereco dadosEndereco() {
		return new DadosEndereco(
			"rua dos caras",
			"bairro dos caras",
			"00000000",
			"quixada",
			"ce",
			null,
			null
		);
	}
	

}
