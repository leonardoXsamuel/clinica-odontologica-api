package odontologica.clinica.clinicaOdont.controller;

import odontologica.clinica.clinicaOdont.model.Paciente;
import odontologica.clinica.clinicaOdont.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/teste")
    public String testeDeRota(){
        return "ROTA TESTE FUNCIONANDO PERFEITAMENTE";
    }

    // GET
    @GetMapping
    public List<Paciente> getPacientes(){
        return pacienteService.getPacientes();
    }

    @GetMapping("{id}")
    public Optional<Paciente> getPacientesById(@PathVariable Long id){
        return pacienteService.getPacienteById(id);
    }

    @GetMapping("cpf/{cpf}")
    public Optional<Paciente> getPacienteByCPF(@PathVariable String cpf){
        return pacienteService.getPacienteByCPF(cpf);
    }

    @GetMapping("nome/{nome}")
    public Optional<Paciente> getPacienteByNome(@PathVariable String nome) throws Exception {
        return pacienteService.getPacienteByNome(nome);
    }

    @GetMapping("telefone/{telefone}")
    public Optional<Paciente> getPacienteByTelefone(@PathVariable String telefone){
        return pacienteService.getPacienteByTelefone(telefone);
    }

    // POST
    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente){
        return pacienteService.createPaciente(paciente);
    }

    @PostMapping("/lote")
    public List<Paciente> createPacientes(@RequestBody List<Paciente> pacienteList){
        return pacienteService.createPacientes(pacienteList);
    }

    // PUT
    @PutMapping("{id}")
    public Paciente updatePacienteById(@PathVariable Long id, @RequestBody Paciente novoPaciente) throws Exception {
        return pacienteService.updatePacienteById(id, novoPaciente);
    }

    @PutMapping("/cpf/{cpf}")
    public Paciente updatePacienteByCPF(@PathVariable String cpf, @RequestBody Paciente novoPaciente) throws Exception {
        return pacienteService.updatePacienteByCPF(cpf, novoPaciente);
    }

    @PutMapping("/nome")
    public Paciente updatePacienteByNome(@RequestParam String nome, @RequestBody Paciente novoPaciente) throws Exception {
        return pacienteService.updatePacienteByNome(nome, novoPaciente);
    }

    // DELETE
    @DeleteMapping("{id}")
    public void deletePacienteById(@PathVariable Long id) throws Exception {
        pacienteService.deletePacienteById(id);
    }

    @DeleteMapping("/cpf/{cpf}")
    public void deletePacienteByCPF(@PathVariable String cpf) throws Exception {
        pacienteService.deletePacienteByCPF(cpf);
    }
}
