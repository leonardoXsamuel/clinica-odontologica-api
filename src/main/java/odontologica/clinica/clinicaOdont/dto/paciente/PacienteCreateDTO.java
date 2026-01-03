package odontologica.clinica.clinicaOdont.dto.paciente;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

public record PacienteCreateDTO (
        @Column(nullable = false)
        @NotBlank(message = "o atributo NOME é obrigatório.")
        String nome,

        @NotBlank(message = "o atributo TELEFONE é obrigatório.")
        @Column(nullable = false, unique = true)
        String telefone,

        @Email
        @NotBlank(message = "o atributo EMAIL é obrigatório.")
        @Column(nullable = false, unique = true)
        String email,

        @Column(nullable = false, unique = true)
        @NotBlank(message = "o atributo CPF é obrigatório.")
        String cpf,

        @Past
        @NotNull
        @Column(nullable = false)
        LocalDate dataNascimento) { }