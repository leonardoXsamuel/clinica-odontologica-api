package odontologica.clinica.clinicaOdont.repository;

import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    void deleteByStatus(StatusAgendamento statusAgendamento);

    Optional<Agendamento> findByStatus(StatusAgendamento statusAgendamento);

    Optional <List<Agendamento>> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    Optional<Agendamento> findByDentistaIdAndDataHora(Long dentistaId, LocalDateTime dataHora);

}
