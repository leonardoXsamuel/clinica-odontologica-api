package odontologica.clinica.clinicaOdont.dto.servico;

import odontologica.clinica.clinicaOdont.model.Servico;

public record ServicoResponseDTO(String nome, String descricao, double valor) {

    public ServicoResponseDTO (Servico servico) {
        this(servico.getNome(), servico.getDescricao(), servico.getValor());
    }

}
