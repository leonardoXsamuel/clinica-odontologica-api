package odontologica.clinica.clinicaOdont.service.validator;

import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.ConflictException;
import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.repository.AgendamentoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ValidaConflito {

    private final AgendamentoRepository agendamentoRepository;

    public ValidaConflito(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
    }

    public void validarConflito(Long id, @NotNull AgendamentoUpdateDTO novoAgendamento) {
        Optional<Agendamento> conflito = agendamentoRepository
                .findByDentistaIdAndDataHora(
                        novoAgendamento.dentistaId(),
                        novoAgendamento.dataHora()
                );

        if (conflito.isPresent() && !conflito.get().getId().equals(id)) {
            throw new ConflictException("O dentista já possui um agendamento nesse horário.");
        }

    }
}
