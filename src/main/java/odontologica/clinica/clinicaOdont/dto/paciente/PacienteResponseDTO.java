package odontologica.clinica.clinicaOdont.dto.paciente;

import odontologica.clinica.clinicaOdont.model.Paciente;

import java.time.LocalDate;

public record PacienteResponseDTO(String nome, String telefone, String email, LocalDate dataNascimento) {

    public PacienteResponseDTO(Paciente paciente) {
        this (paciente.getNome(),
                paciente.getTelefone(),
                paciente.getEmail(),
                paciente.getDataNascimento());
    }

}
