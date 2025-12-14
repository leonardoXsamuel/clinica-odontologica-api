package odontologica.clinica.clinicaOdont.exceptions.dentista;

public class DentistaAlreadsExistsException extends RuntimeException {
    public DentistaAlreadsExistsException() {
        super("O dentista já existe.");
    }

    public DentistaAlreadsExistsException(String message) {
        super(message);
    }
}
