package odontologica.clinica.clinicaOdont.dto.agendamento;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
        @Enumerated(EnumType.STRING)
        StatusAgendamento statusAgendamento,

        @ManyToOne
        @NotNull
        @JoinColumn(name = "dentista_id", nullable = false)
        Dentista dentista,

        @ManyToOne
        @NotNull
        @JoinColumn(name = "dentista_id", nullable = false)
        Paciente paciente,

        @ManyToOne
        @NotNull
        @JoinColumn(name = "dentista_id", nullable = false)
        Servico servico) {
}


