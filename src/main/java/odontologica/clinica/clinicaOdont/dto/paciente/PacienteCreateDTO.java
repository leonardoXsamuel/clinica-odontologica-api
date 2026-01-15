package odontologica.clinica.clinicaOdont.dto.paciente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;

import java.time.LocalDate;

public record PacienteCreateDTO (
        @NotBlank(message = "o atributo NOME é obrigatório.")
        @Schema(description = "Nome completo do paciente", example = "Sarah Rabetti")
        String nome,

        @NotBlank(message = "o atributo TELEFONE é obrigatório.")
        @Schema(description = "Telefone do paciente", example = "11999990001")
        String telefone,

        @Email
        @NotBlank(message = "o atributo EMAIL é obrigatório.")
        @Schema(description = "Email do paciente", example = "bruno.ferreira@email.com")
        String email,

        @NotBlank(message = "o atributo CPF é obrigatório.")
        @Schema(description = "CPF do paciente", example = "23456789012")
        String cpf,

        @Past (message = "a data de nascimento deve ser no passado.")
        @NotNull (message = "a data de nascimento deve ser preenchida.")
        @Schema(type = "string", description = "Data de nascimento do paciente", example = "1990-07-22")
        LocalDate dataNascimento) { }