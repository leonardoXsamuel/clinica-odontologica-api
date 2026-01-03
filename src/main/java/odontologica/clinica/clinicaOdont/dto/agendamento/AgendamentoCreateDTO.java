package odontologica.clinica.clinicaOdont.dto.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoCreateDTO(

        @Future
        @NotNull
        LocalDateTime dataHora,

        @NotNull
        StatusAgendamento statusAgendamento,

        @NotNull
        Long dentistaId,

        @NotNull
        Long pacienteId,

        @NotNull
        Long servicoId
) {}
