package odontologica.clinica.clinicaOdont.service;

import jakarta.transaction.Transactional;
import odontologica.clinica.clinicaOdont.model.Paciente;
import odontologica.clinica.clinicaOdont.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // GET
    public List<Paciente> getPacientes() {
        return pacienteRepository.findAll();
    }

    public Optional<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id);
    }

    public Optional<Paciente> getPacienteByNome(String nome) throws Exception {
        return pacienteRepository.findByNome(nome);
    }

    public Optional<Paciente> getPacienteByCPF(String cpf) {
        return pacienteRepository.findByCpf(cpf);
    }

    public Optional<Paciente> getPacienteByTelefone(String telefone) {
        return pacienteRepository.findByTelefone(telefone);
    }

    // POST
    public Paciente createPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    public List<Paciente> createPacientes(List<Paciente> pacienteList) {
        return pacienteRepository.saveAll(pacienteList);
    }


    // PUT

    @Transactional
    public Paciente updatePacienteById(Long id, Paciente novoPaciente) throws Exception {
        Paciente antigoPaciente = pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("O ID requerido não foi localizado"));

        antigoPaciente.setNome(novoPaciente.getNome());
        antigoPaciente.setTelefone(novoPaciente.getTelefone());
        antigoPaciente.setEmail(novoPaciente.getEmail());
        antigoPaciente.setDataNascimento(novoPaciente.getDataNascimento());
        antigoPaciente.setCpf(novoPaciente.getCpf());

        return pacienteRepository.save(antigoPaciente);
    }

    @Transactional
    public Paciente updatePacienteByNome(String nome, Paciente novoPaciente) throws Exception {
        Paciente antigoPaciente = pacienteRepository.findByNomeContainingIgnoreCase(nome)
                .orElseThrow(() -> new Exception("O NOME requerido não foi localizado"));

        antigoPaciente.setNome(novoPaciente.getNome());
        antigoPaciente.setTelefone(novoPaciente.getTelefone());
        antigoPaciente.setEmail(novoPaciente.getEmail());
        antigoPaciente.setDataNascimento(novoPaciente.getDataNascimento());
        antigoPaciente.setCpf(novoPaciente.getCpf());

        return pacienteRepository.save(antigoPaciente);
    }


    public Paciente updatePacienteByCPF(String cpf, Paciente novoPaciente) throws Exception {
        Paciente antigoPaciente = pacienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new Exception("O CPF requerido não foi localizado"));

        antigoPaciente.setNome(novoPaciente.getNome());
        antigoPaciente.setTelefone(novoPaciente.getTelefone());
        antigoPaciente.setEmail(novoPaciente.getEmail());
        antigoPaciente.setDataNascimento(novoPaciente.getDataNascimento());
        antigoPaciente.setCpf(novoPaciente.getCpf());

        return pacienteRepository.save(antigoPaciente);
    }

    // DELETE

    @Transactional
    public void deletePacienteById(Long id) throws Exception {
        pacienteRepository.findById(id)
                .orElseThrow(() -> new Exception("O ID requerido não foi localizado"));

        pacienteRepository.deleteById(id);
    }

    @Transactional
    public void deletePacienteByCPF(String CPF) throws Exception {
        pacienteRepository.findByNome(CPF)
                .orElseThrow(() -> new Exception("O CPF requerido não foi localizado"));

        pacienteRepository.deleteByNome(CPF);
    }

}
