package odontologica.clinica.clinicaOdont.dto.dentista;

import odontologica.clinica.clinicaOdont.model.enums.Especialidade;

public record DentistaCreateDTO(String nome, String cro, Especialidade especialidade, String telefone, String email) {}
