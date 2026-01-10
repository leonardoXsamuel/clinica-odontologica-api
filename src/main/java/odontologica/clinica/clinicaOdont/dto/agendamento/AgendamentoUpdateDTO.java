package odontologica.clinica.clinicaOdont.dto.agendamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoUpdateDTO(
        @Future
        @NotNull
        @Schema(type = "string", example = "2026-01-10T14:30:00")
        LocalDateTime dataHora,

        @NotNull
        StatusAgendamento statusAgendamento,

        @NotNull
        Long dentistaId,

        @NotNull
        Long pacienteId,

        @NotNull
        Long servicoId
) {
}
