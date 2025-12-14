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
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AgendamentoAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerAgendamentoAlreadsExistsException(AgendamentoAlreadsExistsException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorJson);
    }


    @ExceptionHandler(DentistaAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerDentistaAlreadsExistsException(DentistaAlreadsExistsException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                HttpStatus.CONFLICT,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorJson);
    }

    @ExceptionHandler(InvalidCroException.class)
    public ResponseEntity<ErrorJson> HandlerInvalidCroException(InvalidCroException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorJson> HandlerResourceNotFoundException(ResourceNotFoundException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                HttpStatus.NOT_FOUND,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorJson);
    }

    @ExceptionHandler(PacienteAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerPacienteAlreadsExistsException(PacienteAlreadsExistsException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson);
    }

    @ExceptionHandler(ServicoAlreadsExistsException.class)
    public ResponseEntity<ErrorJson> HandlerServicoAlreadsExistsException(ServicoAlreadsExistsException exception, HttpServletRequest request) {
        ErrorJson errorJson = new ErrorJson(
                exception.getMessage(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                HttpStatus.BAD_REQUEST,
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorJson);
    }

}
