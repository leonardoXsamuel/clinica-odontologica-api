package odontologica.clinica.clinicaOdont.dto.servico;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.NotBlank;

public record ServicoUpdateDTO(
        @NotBlank(message = "o atributo NOME é obrigatório.")
        String nome,

        @NotBlank(message = "o atributo DESCRICAO é obrigatório.")
        String descricao,

        @NotNull(message = "o atributo VALOR é obrigatório.")
        @Positive(message = "o atributo VALOR deve ser POSITIVO.")
        double valor) {
}
