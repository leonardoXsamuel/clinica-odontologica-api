package odontologica.clinica.clinicaOdont.controller;

import odontologica.clinica.clinicaOdont.dto.servico.ServicoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoUpdateDTO;
import odontologica.clinica.clinicaOdont.model.Servico;
import odontologica.clinica.clinicaOdont.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/servicos")
@RestController
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @GetMapping("/teste")
    public String testeDeRota() {
        return "ROTA TESTE FUNCIONANDO PERFEITAMENTE";
    }

    @GetMapping("/lote")
    public ResponseEntity<List<ServicoResponseDTO>> getServicos() {
        return ResponseEntity.ok(servicoService.getServicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> getServicoById(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.getServicoById(id));
    }

    @GetMapping("/nome/")
    public ResponseEntity<List<ServicoResponseDTO>> getServicoByNome(@RequestParam String nome) {
        return ResponseEntity.ok(servicoService.getServicoByNome(nome));
    }

    @PostMapping
    public ResponseEntity<ServicoResponseDTO> createServico(@RequestBody ServicoCreateDTO createDTO){
        ServicoResponseDTO responseDTO = servicoService.createServico(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/lote")
    public ResponseEntity<List<ServicoResponseDTO>> createServicos(@RequestBody List<ServicoCreateDTO> servicoList){
        List<ServicoResponseDTO> dtoList =  servicoService.createServicos(servicoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> updateServicoById(@PathVariable Long id, @RequestBody ServicoUpdateDTO novoServicoDTO) {
        ServicoResponseDTO dto = servicoService.updateServicoById(id, novoServicoDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/nome/")
    public ResponseEntity<ServicoResponseDTO> updateServicoByNome(@RequestParam String nome, @RequestBody ServicoUpdateDTO novoServicoDTO) throws Exception {
        ServicoResponseDTO dto = servicoService.updateServicoByNome(nome, novoServicoDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteServicoById(@PathVariable Long id) {
        servicoService.deleteServicoById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/nome/")
    public ResponseEntity<Void> deleteServicoByNome(@RequestParam String nome) {
        servicoService.deleteServicoByNome(nome);
        return ResponseEntity.noContent().build();
    }

}
