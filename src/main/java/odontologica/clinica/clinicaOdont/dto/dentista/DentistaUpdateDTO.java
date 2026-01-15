package odontologica.clinica.clinicaOdont.dto.dentista;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;

public record DentistaUpdateDTO(
        @NotBlank(message = "O atributo NOME é obrigatório.")
        @Schema(description = "Nome completo do dentista", example = "Maria Souza")
        String nome,

        @NotBlank(message = "O atributo CRO é obrigatório.")
        @Schema(description = "Número do CRO do dentista", example = "23456")
        String cro,

        @NotNull(message = "Especialidade deve conter somente: ORTODONTIA, ENDODONTIA, PERIODONTIA, ODONTOPEDIATRIA ou CIRURGIA_BUCAL")
        @Schema(description = "Especialidade do dentista", example = "ORTODONTIA", allowableValues = {
                "ORTODONTIA",
                "ENDODONTIA",
                "PERIODONTIA",
                "ODONTOPEDIATRIA",
                "CIRURGIA_BUCAL"
        })
        Especialidade especialidade,

        @NotBlank(message = "O atributo TELEFONE é obrigatório.")
        @Schema(description = "Telefone do dentista", example = "11999990002")
        String telefone,

        @NotBlank(message = "O atributo EMAIL é obrigatório.")
        @Schema(description = "Email do dentista", example = "maria.souza@email.com")
        String email
) {
}
