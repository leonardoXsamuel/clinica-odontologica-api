package odontologica.clinica.clinicaOdont.repository;

import odontologica.clinica.clinicaOdont.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    void deleteByStatus(Agendamento.StatusAgendamento statusAgendamento);

    Optional<Agendamento> findByStatus(Agendamento.StatusAgendamento statusAgendamento);

    List<Agendamento> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);

    Optional<Agendamento> findByDentistaIdAndDataHora(Long dentistaId, LocalDateTime dataHora);

}
