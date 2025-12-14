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
    public List<ServicoResponseDTO> createServicos(@RequestBody List<ServicoCreateDTO> servicoList){
        return servicoService.createServicos(servicoList);
    }

    @PutMapping("/{id}")
    public Servico updateServicoById(@PathVariable Long id, @RequestBody ServicoUpdateDTO novoServicoDTO) {
        return servicoService.updateServicoById(id, novoServicoDTO);
    }

    @PutMapping("/nome/")
    public Servico updateServicoByNome(@RequestParam String nome, @RequestBody ServicoUpdateDTO novoServicoDTO) throws Exception {
        return servicoService.updateServicoByNome(nome, novoServicoDTO);
    }

    @DeleteMapping("{id}")
    public void deleteServicoById(@PathVariable Long id) {
        servicoService.deleteServicoById(id);
    }

    @DeleteMapping("/nome/")
    public void deleteServicoByNome(@RequestParam String nome) {
        servicoService.deleteServicoByNome(nome);
    }

}
