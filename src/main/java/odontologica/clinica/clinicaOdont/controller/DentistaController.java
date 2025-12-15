package odontologica.clinica.clinicaOdont.controller;

import odontologica.clinica.clinicaOdont.dto.dentista.DentistaCreateDTO;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaResponseDTO;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaUpdateDTO;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;
import odontologica.clinica.clinicaOdont.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dentistas")
@RestController
public class DentistaController {

    private final DentistaService dentistaService;

    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @GetMapping("/teste")
    public String testeDeRota() {
        return "ROTA TESTE FUNCIONANDO PERFEITAMENTE";
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<DentistaResponseDTO> getDentistaById(@PathVariable Long id) {
        return ResponseEntity.ok(dentistaService.getDentistaById(id));
    }

    @GetMapping("/especialidade")
    public ResponseEntity<List<DentistaResponseDTO>> getDentistaByEspecialidade(@RequestParam Especialidade especialidade) {
        return ResponseEntity.ok(dentistaService.getDentistaByEspecialidade(especialidade));
    }

    @GetMapping("/nome")
    public ResponseEntity<List<DentistaResponseDTO>> getDentistaByNome(@RequestParam String nome) {
        return ResponseEntity.ok(dentistaService.getDentistaByNome(nome));
    }

    @GetMapping("/cro/{cro}")
    public ResponseEntity<DentistaResponseDTO> getDentistaByCro(@PathVariable String cro)  {
        return ResponseEntity.ok(dentistaService.getDentistaByCro(cro));
    }

    @GetMapping("/lote")
    public ResponseEntity<List<DentistaResponseDTO>> getDentistas() {
        return ResponseEntity.ok(dentistaService.getDentistas());
    }

    @PostMapping
    public ResponseEntity<DentistaResponseDTO> createDentista(@RequestBody DentistaCreateDTO dentistaCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaService.createDentista(dentistaCreateDTO));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<DentistaResponseDTO>> createDentistas(@RequestBody List<DentistaCreateDTO> dentistaList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaService.createDentistas(dentistaList));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DentistaResponseDTO> updateDentistaById(@PathVariable Long id, @RequestBody DentistaUpdateDTO novoDentista)  {
        return ResponseEntity.ok(dentistaService.updateDentistaById(id, novoDentista));
    }

    @PutMapping("/cro/{cro}")
    public ResponseEntity<DentistaResponseDTO> updateDentistaByCro(@PathVariable String cro, @RequestBody DentistaUpdateDTO novoDentista)  {
        return ResponseEntity.ok(dentistaService.updateDentistaByCro(cro, novoDentista));
    }

    @PutMapping("/nome/{nome}")
    public ResponseEntity<DentistaResponseDTO> updateDentistaByNome(@PathVariable String nome, @RequestBody DentistaUpdateDTO novoDentista)  {
        return ResponseEntity.ok(dentistaService.updateDentistaByNome(nome, novoDentista));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDentistaById(@PathVariable Long id)  {
        dentistaService.deleteDentistaById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deleteDentistaByNome(@PathVariable String nome)  {
        dentistaService.deleteDentistaByNome(nome);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/especialidade/{especialidade}")
    public ResponseEntity<Void> deleteDentistaByEspecialidade(@PathVariable Especialidade especialidade)  {
        dentistaService.deleteDentistaByEspecialidade(especialidade);
        return ResponseEntity.ok().build();
    }
}
