package odontologica.clinica.clinicaOdont.dto.agendamento;

import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponseDTO(
        LocalDateTime dataHora,
        StatusAgendamento statusAgendamento,
        String nomeDentista,
        String nomeServico
) {
    public AgendamentoResponseDTO(Agendamento agendamento) {
        this(
                agendamento.getDataHora(),
                agendamento.getStatus(),
                agendamento.getDentista().getNome(),
                agendamento.getServico().getNome()
        );
    }
}
