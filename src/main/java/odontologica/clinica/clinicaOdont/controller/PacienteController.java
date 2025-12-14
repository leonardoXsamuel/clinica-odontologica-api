package odontologica.clinica.clinicaOdont.controller;

import odontologica.clinica.clinicaOdont.dto.paciente.PacienteCreateDTO;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteResponseDTO;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteUpdateDTO;
import odontologica.clinica.clinicaOdont.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @GetMapping("/teste")
    public String testeDeRota() {
        return "ROTA TESTE FUNCIONANDO PERFEITAMENTE";
    }

    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> getPacientes() {
        return ResponseEntity.ok(pacienteService.getPacientes());
    }

    @GetMapping("{id}")
    public ResponseEntity<PacienteResponseDTO> getPacientesById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.getPacienteById(id));
    }

    @GetMapping("cpf/{cpf}")
    public ResponseEntity<PacienteResponseDTO> getPacienteByCPF(@PathVariable String cpf) {
        return ResponseEntity.ok(pacienteService.getPacienteByCPF(cpf));
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<PacienteResponseDTO> getPacienteByNome(@PathVariable String nome) throws Exception {
        return ResponseEntity.ok(pacienteService.getPacienteByNome(nome));
    }

    @GetMapping("telefone/{telefone}")
    public ResponseEntity<PacienteResponseDTO> getPacienteByTelefone(@PathVariable String telefone) {
        return ResponseEntity.ok(pacienteService.getPacienteByTelefone(telefone));
    }

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> createPaciente(@RequestBody PacienteCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.createPaciente(dto));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<PacienteResponseDTO>> createPacientes(@RequestBody List<PacienteCreateDTO> pacienteList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.createPacientes(pacienteList));
    }

    @PutMapping("{id}")
    public ResponseEntity<PacienteResponseDTO> updatePacienteById(@PathVariable Long id, @RequestBody PacienteUpdateDTO novoPaciente) {
        PacienteResponseDTO dto = pacienteService.updatePacienteById(id, novoPaciente);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteResponseDTO> updatePacienteByCPF(@PathVariable String cpf, @RequestBody PacienteUpdateDTO novoPaciente) {
        PacienteResponseDTO dto = pacienteService.updatePacienteByCPF(cpf, novoPaciente);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/nome")
    public ResponseEntity<PacienteResponseDTO> updatePacienteByNome(@RequestParam String nome, @RequestBody PacienteUpdateDTO novoPaciente) {
        PacienteResponseDTO dto = pacienteService.updatePacienteByNome(nome, novoPaciente);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePacienteById(@PathVariable Long id) {
        pacienteService.deletePacienteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deletePacienteByCPF(@PathVariable String cpf) {
        pacienteService.deletePacienteByCPF(cpf);
        return ResponseEntity.noContent().build();
    }
}
