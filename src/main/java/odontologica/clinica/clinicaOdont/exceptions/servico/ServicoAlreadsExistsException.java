package odontologica.clinica.clinicaOdont.exceptions.servico;

public class ServicoAlreadsExistsException extends RuntimeException {
    public ServicoAlreadsExistsException() {
        super("O serviço já existe.");
    }

    public ServicoAlreadsExistsException(String message) {
        super(message);
    }
}
