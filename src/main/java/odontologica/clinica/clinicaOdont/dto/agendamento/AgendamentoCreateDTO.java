package odontologica.clinica.clinicaOdont.dto.agendamento;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoCreateDTO(

        @Future
        @NotNull
        @Schema(type = "string", example = "2026-01-10T14:30:00", description = "data e hora do agendamento")
        LocalDateTime dataHora,

        @NotNull
        @Schema(type = "string", description = "Status do agendamento", example = "PENDENTE", allowableValues = {
                "PENDENTE", "CONCLUIDO", "CONFIRMADO", "CANCELADO"
        })
        StatusAgendamento statusAgendamento,

        @NotNull
        @Schema (description = "id do dentista relacionado ao agendamento")
        Long dentistaId,

        @NotNull
        @Schema (description = "id do paciente relacionado ao agendamento")
        Long pacienteId,

        @NotNull
        @Schema (description = "id do serviço prestado relacionado ao agendamento")
        Long servicoId
) {}
