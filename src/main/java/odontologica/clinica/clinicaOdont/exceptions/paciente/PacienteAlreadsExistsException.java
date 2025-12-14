package odontologica.clinica.clinicaOdont.exceptions.paciente;

public class PacienteAlreadsExistsException extends RuntimeException {
    public PacienteAlreadsExistsException() {
        super("O paciente já existe.");
    }

    public PacienteAlreadsExistsException(String message) {
        super(message);
    }
}
