package odontologica.clinica.clinicaOdont.dto.agendamento;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.model.Paciente;
import odontologica.clinica.clinicaOdont.model.Servico;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoCreateDTO(
        @Future
        @NotNull
        LocalDateTime dataHora,

        @NotNull
        StatusAgendamento statusAgendamento,

        @NotNull
        Dentista dentista,

        @NotNull
        Paciente paciente,

        @NotNull
        Servico servico) {
}


