package odontologica.clinica.clinicaOdont.repository;

import odontologica.clinica.clinicaOdont.model.Dentista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DentistaRepository extends JpaRepository<Dentista, Long> {
    Optional<Dentista> findByEspecialidade(Dentista.Especialidade especialidade);

    List<Dentista> findByNomeContainingIgnoreCase(String nome);

    Optional<Dentista> findByCroContainingIgnoreCase(String cro);

    List<Dentista> findByTelefoneContaining(String telefone);

    List<Dentista> findByEmailContaining(String email);

    Optional<Dentista> findByCro(String cro);

    Optional<Dentista> findByNome(String nome);

    void deleteByNome(String nome);

    void deleteByEspecialidade(Dentista.Especialidade especialidade);
}
