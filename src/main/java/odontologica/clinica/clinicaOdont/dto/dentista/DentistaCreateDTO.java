package odontologica.clinica.clinicaOdont.dto.dentista;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;

public record DentistaCreateDTO(
        @NotBlank(message = "O atributo NOME é obrigatório.")
        @Column(nullable = false)
        String nome,

        @NotBlank(message = "o atributo CRO é obrigatório.")
        @Column(nullable = false, unique = true)
        String cro,

        @Enumerated(EnumType.STRING)
        @NotNull(message = "Especialidade deve conter somente: ORTODONTIA, ENDODONTIA, PERIODONTIA, ODONTOPEDIATRIA ou CIRURGIA_BUCAL")
        @Column(nullable = false)
        Especialidade especialidade,

        @NotBlank(message = "o atributo TELEFONE é obrigatório.")
        @Column(nullable = false, unique = true)
        String telefone,

        @NotBlank(message = "o atributo EMAIL é obrigatório.")
        @Column(nullable = false, unique = true)
        String email) {
}
