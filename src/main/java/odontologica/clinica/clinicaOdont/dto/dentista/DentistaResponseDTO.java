package odontologica.clinica.clinicaOdont.dto.dentista;

import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;

public record DentistaResponseDTO(String nome, String cro, Especialidade especialidade) {

    public DentistaResponseDTO(Dentista dentista) {
        this(dentista.getNome(), dentista.getCro(), dentista.getEspecialidade());
    }

}
