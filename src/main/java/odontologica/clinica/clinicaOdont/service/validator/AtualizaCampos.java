package odontologica.clinica.clinicaOdont.service.validator;

import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoUpdateDTO;
import odontologica.clinica.clinicaOdont.model.Agendamento;
import org.jetbrains.annotations.NotNull;

public class AtualizaCampos {

    public static void atualizarCampos(@NotNull AgendamentoUpdateDTO novoAgendamento, Agendamento antigoAgendamento) {
        if (novoAgendamento.dataHora() != null) {
            antigoAgendamento.setDataHora(novoAgendamento.dataHora());
        }

        if (novoAgendamento.statusAgendamento() != null) {
            antigoAgendamento.setStatus(novoAgendamento.statusAgendamento());
        }
    }
}
