package odontologica.clinica.clinicaOdont.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException() {
        super("Erros na persistência de dados");
    }

    public DatabaseException(String message) {
        super(message);
    }
}
