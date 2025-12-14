package odontologica.clinica.clinicaOdont.dto.agendamento;

import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.model.Paciente;
import odontologica.clinica.clinicaOdont.model.Servico;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoCreateDTO(LocalDateTime dataHora,
                                   StatusAgendamento statusAgendamento,
                                   Dentista dentista,
                                   Paciente paciente,
                                   Servico servico) {
}


