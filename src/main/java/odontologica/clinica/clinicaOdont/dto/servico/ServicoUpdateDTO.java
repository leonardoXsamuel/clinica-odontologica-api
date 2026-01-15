package odontologica.clinica.clinicaOdont.dto.servico;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.NotBlank;

public record ServicoUpdateDTO(
        @Schema(description = "Nome do serviço", example = "Limpeza Dental")
        @NotBlank(message = "o atributo NOME é obrigatório.")
        String nome,

        @NotBlank(message = "o atributo DESCRICAO é obrigatório.")
        @Schema(description = "Descrição do serviço", example = "Remoção de tártaro e placa bacteriana")
        String descricao,

        @Positive(message = "o atributo VALOR deve ser POSITIVO.")
        @Schema(type = "number", description = "Valor do serviço", example = "150")
        @NotNull (message = "o valor do serviço deve ser preenchido.")
        double valor
) { }
