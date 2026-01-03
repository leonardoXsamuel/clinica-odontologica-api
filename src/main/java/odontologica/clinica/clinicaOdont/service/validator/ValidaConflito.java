package odontologica.clinica.clinicaOdont.service.validator;

import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.ConflictException;
import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.repository.AgendamentoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ValidaConflito {

    @Autowired
    private static AgendamentoRepository agendamentoRepository;

    public static void validarConflito(Long id, @NotNull AgendamentoUpdateDTO novoAgendamento) {
        Optional<Agendamento> conflito = agendamentoRepository
                .findByDentistaIdAndDataHora(novoAgendamento.dentista().getId(), novoAgendamento.dataHora());

        if (conflito.isPresent() && !conflito.get().getId().equals(id)) {
            throw new ConflictException("O dentista já possui um agendamento nesse horário.");
        }

    }
}
