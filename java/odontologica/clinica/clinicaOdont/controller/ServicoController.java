package odontologica.clinica.clinicaOdont.controller;

import odontologica.clinica.clinicaOdont.model.Servico;
import odontologica.clinica.clinicaOdont.service.ServicoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // GET
    @GetMapping("/lote")
    public List<Servico> getServicos() {
        return servicoService.getServicos();
    }

    @GetMapping("/{id}")
    public Optional<Servico> getServicoById(@PathVariable Long id) {
        return servicoService.getServicoById(id);
    }

    @GetMapping("/nome/")
    public List<Servico> getServicoByNome(@RequestParam String nome) {
        return servicoService.getServicoByNome(nome);
    }

    //POST
    @PostMapping
    public Servico createServico(@RequestBody Servico servico){
        return servicoService.createServico(servico);
    }

    @PostMapping("/lote")
    public List<Servico> createServicos(@RequestBody List<Servico> servicoList){
        return servicoService.createServicos(servicoList);
    }

    // PUT
    @PutMapping("/{id}")
    public Servico updateServicoById(@PathVariable Long id, @RequestBody Servico novoServico) throws Exception {
        return servicoService.updateServicoById(id, novoServico);
    }

    @PutMapping("/nome/")
    public Servico updateServicoByNome(@RequestParam String nome, @RequestBody Servico novoServico) throws Exception {
        return servicoService.updateServicoByNome(nome, novoServico);
    }

    // DELETE
    @DeleteMapping("{id}")
    public void deleteServicoById(@PathVariable Long id) throws Exception {
        servicoService.deleteServicoById(id);
    }

    @DeleteMapping("/nome/")
    public void deleteServicoByNome(@RequestParam String nome) throws Exception {
        servicoService.deleteServicoByNome(nome);
    }

}
