package odontologica.clinica.clinicaOdont.dto.paciente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

public record PacienteCreateDTO (
        @NotBlank(message = "o atributo NOME é obrigatório.")
        String nome,

        @NotBlank(message = "o atributo TELEFONE é obrigatório.")
        String telefone,

        @Email
        @NotBlank(message = "o atributo EMAIL é obrigatório.")
        String email,

        @NotBlank(message = "o atributo CPF é obrigatório.")
        String cpf,

        @Past
        @NotNull
        LocalDate dataNascimento) { }