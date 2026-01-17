package odontologica.clinica.clinicaOdont.service;

import jakarta.transaction.Transactional;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteCreateDTO;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteResponseDTO;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.ResourceNotFoundException;
import odontologica.clinica.clinicaOdont.model.Paciente;
import odontologica.clinica.clinicaOdont.repository.PacienteRepository;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public List<PacienteResponseDTO> getPacientes() {
        List<Paciente> pacienteList = pacienteRepository.findAll();

        if (pacienteList.isEmpty()) {
            throw new ResourceNotFoundException("Não há pacientes registrados no banco de dados.");
        }

        return pacienteList
                .stream()
                .map(PacienteResponseDTO::new)
                .toList();
    }

    public PacienteResponseDTO getPacienteById(Long id) {

        Paciente paciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não localizado."));

        return new PacienteResponseDTO(paciente);
    }

    public List<PacienteResponseDTO> getPacienteByNome(String nome) {
        List<Paciente> pacientes = pacienteRepository.findByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não localizado."));

        return pacientes.stream()
                .map(PacienteResponseDTO::new)
                .toList();
    }

    public PacienteResponseDTO getPacienteByCPF(String cpf) {
        Paciente paciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não localizado."));

        return new PacienteResponseDTO(paciente);
    }

    public PacienteResponseDTO getPacienteByTelefone(String telefone) {
        Paciente paciente = pacienteRepository.findByTelefone(telefone)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não localizado."));

        return new PacienteResponseDTO(paciente);
    }

    public PacienteResponseDTO createPaciente(PacienteCreateDTO dto) {
        Paciente p = new Paciente(dto);
        pacienteRepository.save(p);
        return new PacienteResponseDTO(p);
    }

    public List<PacienteResponseDTO> createPacientes(List<PacienteCreateDTO> dtoList) {

        List<Paciente> pacientes = dtoList.stream()
                .map(Paciente::new)
                .toList();

        return pacienteRepository.saveAll(pacientes)
                .stream()
                .map(PacienteResponseDTO::new)
                .toList();
    }

    private void atualizarAtributos(Paciente antigoPaciente, PacienteUpdateDTO dto){
        antigoPaciente.setNome(dto.nome());
        antigoPaciente.setTelefone(dto.telefone());
        antigoPaciente.setEmail(dto.email());
        antigoPaciente.setDataNascimento(dto.dataNascimento());
        antigoPaciente.setCpf(dto.cpf());
    }

    @Transactional
    public PacienteResponseDTO updatePacienteById(Long id, PacienteUpdateDTO dto) {
        Paciente antigoPaciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O ID requerido não foi localizado"));
        atualizarAtributos(antigoPaciente, dto);

        Paciente paciente = pacienteRepository.save(antigoPaciente);
        return new PacienteResponseDTO(paciente);
    }

    @Transactional
    public PacienteResponseDTO updatePacienteByNome(String nome, PacienteUpdateDTO dto) {
        Paciente antigoPaciente = pacienteRepository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(() -> new ResourceNotFoundException("O NOME requerido não foi localizado"));

        atualizarAtributos(antigoPaciente, dto);

        Paciente paciente = pacienteRepository.save(antigoPaciente);
        return new PacienteResponseDTO(paciente);
    }

    public PacienteResponseDTO updatePacienteByCPF(String cpf, PacienteUpdateDTO dto) {
        Paciente antigoPaciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("O CPF requerido não foi localizado"));

        atualizarAtributos(antigoPaciente, dto);

        Paciente paciente = pacienteRepository.save(antigoPaciente);
        return new PacienteResponseDTO(paciente);
    }

    @Transactional
    public void deletePacienteById(Long id) {
        pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("O ID requerido não foi localizado"));

        pacienteRepository.deleteById(id);
    }

    @Transactional
    public void deletePacienteByCPF(String CPF) {
        pacienteRepository.findByNome(CPF)
                .orElseThrow(() -> new ResourceNotFoundException("O CPF requerido não foi localizado"));

        pacienteRepository.deleteByNome(CPF);
    }

}
