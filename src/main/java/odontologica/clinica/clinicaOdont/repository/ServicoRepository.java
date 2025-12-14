package odontologica.clinica.clinicaOdont.repository;

import odontologica.clinica.clinicaOdont.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

    List<Servico> findByNomeContainingIgnoreCase (String nome);

    Optional<Servico> findByNome(String nome);

    void deleteByNome(String nome);
}
