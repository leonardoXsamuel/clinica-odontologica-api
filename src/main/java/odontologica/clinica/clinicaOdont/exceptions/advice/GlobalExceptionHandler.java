package odontologica.clinica.clinicaOdont.exceptions.advice;

import jakarta.servlet.http.HttpServletRequest;
import odontologica.clinica.clinicaOdont.exceptions.ResourceNotFoundException;
import odontologica.clinica.clinicaOdont.exceptions.agendamento.AgendamentoAlreadsExistsException;
import odontologica.clinica.clinicaOdont.exceptions.dentista.DentistaAlreadsExistsException;
import odontologica.clinica.clinicaOdont.exceptions.dentista.InvalidCroException;
import odontologica.clinica.clinicaOdont.exceptions.paciente.PacienteAlreadsExistsException;
import odontologica.clinica.clinicaOdont.exceptions.servico.ServicoAlreadsExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AgendamentoAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerAgendamentoAlreadsExistsException(AgendamentoAlreadsExistsException exception) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                null // request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorJson);
    }


    @ExceptionHandler(DentistaAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerDentistaAlreadsExistsException(DentistaAlreadsExistsException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                null //request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorJson);
    }

    @ExceptionHandler(InvalidCroException.class)
    public ResponseEntity<ErrorJson> HandlerInvalidCroException(InvalidCroException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                null //request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorJson> HandlerResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                null //request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson);
    }

    @ExceptionHandler(PacienteAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerPacienteAlreadsExistsException(PacienteAlreadsExistsException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                null //request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson);
    }

    @ExceptionHandler(ServicoAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerServicoAlreadsExistsException(ServicoAlreadsExistsException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT.value(),
                null // request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson);
    }

}
