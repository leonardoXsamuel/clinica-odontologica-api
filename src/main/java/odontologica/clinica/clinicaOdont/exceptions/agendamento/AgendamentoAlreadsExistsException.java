package odontologica.clinica.clinicaOdont.exceptions.agendamento;

public class AgendamentoAlreadsExistsException extends RuntimeException {
    public AgendamentoAlreadsExistsException() {
        super("O agendamento já existe.");
    }

    public AgendamentoAlreadsExistsException(String message) {
        super(message);
    }

}
