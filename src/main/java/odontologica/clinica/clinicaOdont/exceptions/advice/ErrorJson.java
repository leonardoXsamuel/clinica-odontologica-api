package odontologica.clinica.clinicaOdont.exceptions.advice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ErrorJson {

    private String message;
    private String error;
    private int status;
    private String path;

    public ErrorJson(String message, String error, int status, String path) {
        this.message = message;
        this.error = error;
        this.status = status;
        this.path = path;
    }
}
