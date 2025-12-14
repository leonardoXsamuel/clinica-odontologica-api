package odontologica.clinica.clinicaOdont.exceptions.advice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ErrorJson {

    private String mensagem;
    private String dataHora;
    private String error;
    private HttpStatus status;
    private String path;

    public ErrorJson(String mensagem, String error, HttpStatus status, String path) {
        this.mensagem = mensagem;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.dataHora = LocalDateTime.now().format(formatter);
        this.status = status;
        this.error = status.getReasonPhrase();
        this.path = path;
    }
}
