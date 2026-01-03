package odontologica.clinica.clinicaOdont.dto.dentista;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;

public record DentistaCreateDTO(
        @NotBlank(message = "O atributo NOME é obrigatório.")
        String nome,

        @NotBlank(message = "o atributo CRO é obrigatório.")
        String cro,

        @NotNull(message = "Especialidade deve conter somente: ORTODONTIA, ENDODONTIA, PERIODONTIA, ODONTOPEDIATRIA ou CIRURGIA_BUCAL")
        Especialidade especialidade,

        @NotBlank(message = "o atributo TELEFONE é obrigatório.")
        String telefone,

        @NotBlank(message = "o atributo EMAIL é obrigatório.")
        String email) {
}
