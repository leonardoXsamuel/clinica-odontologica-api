package odontologica.clinica.clinicaOdont.repository;

import odontologica.clinica.clinicaOdont.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByCpf(String cpf);

    Optional<Paciente> findByTelefone(String telefone);

    Optional<Paciente> findByNomeContainingIgnoreCase(String nome);

    Optional<Paciente> findByNome(String nome);

    void deleteByNome(String nome);
}
