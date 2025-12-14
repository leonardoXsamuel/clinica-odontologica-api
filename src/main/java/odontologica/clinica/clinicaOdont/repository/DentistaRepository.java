package odontologica.clinica.clinicaOdont.repository;

import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    Optional<Dentista> findByEspecialidade(Especialidade especialidade);

    List<Dentista> findByNomeContainingIgnoreCase(String nome);

    List<Dentista> findByCroContainingIgnoreCase(String cro);

    Optional<Dentista> findByCro(String cro);

    Optional<Dentista> findByNome(String nome);

    void deleteByNome(String nome);

    void deleteByEspecialidade(Especialidade especialidade);

    boolean existsByCroAndIdNot(String cro, Long id);

    boolean existsByEmailAndIdNot(String cro, Long id);

    boolean existsByTelefoneAndIdNot(String email, Long id);
}
