package odontologica.clinica.clinicaOdont.exceptions.dentista;

public class InvalidCroException extends RuntimeException {
    public InvalidCroException() {
        super("CRO inválido.");
    }

    public InvalidCroException(String message) {
        super(message);
    }
}
