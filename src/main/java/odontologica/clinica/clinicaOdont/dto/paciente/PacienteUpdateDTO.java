package odontologica.clinica.clinicaOdont.dto.paciente;

import java.time.LocalDate;

public record PacienteUpdateDTO (String nome, String telefone, String email, String cpf, LocalDate dataNascimento) {
}
