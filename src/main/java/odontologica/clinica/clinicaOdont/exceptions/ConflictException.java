package odontologica.clinica.clinicaOdont.exceptions;

public class ConflictException extends RuntimeException {

    public ConflictException() {
        super("Conflito de Horário!");
    }

    public ConflictException(String message) {
        super(message);
    }
}

