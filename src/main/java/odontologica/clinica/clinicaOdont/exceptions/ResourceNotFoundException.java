package odontologica.clinica.clinicaOdont.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("O recurso solicitado não foi localizado.");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }


}

