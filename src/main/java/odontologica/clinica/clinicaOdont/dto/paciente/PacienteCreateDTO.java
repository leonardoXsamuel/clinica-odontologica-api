package odontologica.clinica.clinicaOdont.dto.paciente;

import java.time.LocalDate;

public record PacienteCreateDTO (String nome, String telefone, String email, String cpf, LocalDate dataNascimento) {
}
