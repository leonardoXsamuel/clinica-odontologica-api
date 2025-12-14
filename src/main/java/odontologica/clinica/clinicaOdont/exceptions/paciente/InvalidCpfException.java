package odontologica.clinica.clinicaOdont.exceptions.paciente;

public class InvalidCpfException extends RuntimeException {
    public InvalidCpfException() {
        super("CPF inválido.");
    }

    public InvalidCpfException(String message) {
        super(message);
    }
}
