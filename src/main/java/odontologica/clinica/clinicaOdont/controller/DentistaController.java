package odontologica.clinica.clinicaOdont.controller;

import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.service.DentistaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // GET
    @GetMapping("/id/{id}")
    public Optional<Dentista> getDentistaById(@PathVariable Long id) throws Exception {
        return dentistaService.getDentistaById(id);
    }

    @GetMapping("/especialidade")
    public Optional<Dentista> getDentistaByEspecialidade(@RequestParam Dentista.Especialidade especialidade) throws Exception {
        return dentistaService.getDentistaByEspecialidade(especialidade);
    }

    @GetMapping("/cro/{cro}")
    public Optional<Dentista> getDentistaByCro(@PathVariable String cro) throws Exception {
        return dentistaService.getDentistaByCro(cro);
    }

    @GetMapping("/lote")
    public List<Dentista> getDentistas() {
        return dentistaService.getDentistas();
    }

    // POST
    @PostMapping
    public Dentista createDentista(@RequestBody Dentista dentista) {
        return dentistaService.createDentista(dentista);
    }

    @PostMapping("/lote")
    public List<Dentista> createDentistas(@RequestBody List<Dentista> dentistaList) {
        return dentistaService.createDentistas(dentistaList);
    }

    // PUT
    @PutMapping("/{id}")
    public Dentista updateDentistaById(@PathVariable Long id, @RequestBody Dentista novoDentista) throws Exception {
        return dentistaService.updateDentistaById(id, novoDentista);
    }

    @PutMapping("/cro/{cro}")
    public Dentista updateDentistaByCro(@PathVariable String cro, @RequestBody Dentista novoDentista) throws Exception {
        return dentistaService.updateDentistaByCro(cro, novoDentista);
    }

    @PutMapping("/nome/{nome}")
    public Dentista updateDentistaByNome(@PathVariable String nome, @RequestBody Dentista novoDentista) throws Exception {
        return dentistaService.updateDentistaByNome(nome, novoDentista);
    }

    // DELETE
    @DeleteMapping("{id}")
    public void deleteDentistaById(@PathVariable Long id) throws Exception {
        dentistaService.deleteDentistaById(id);
    }

    @DeleteMapping("/nome/{nome}")
    public void deleteDentistaByNome(@PathVariable String nome) throws Exception {
        dentistaService.deleteDentistaByNome(nome);
    }

    @DeleteMapping("/especialidade/{especialidade}")
    public void deleteDentistaByEspecialidade(@PathVariable Dentista.Especialidade especialidade) throws Exception {
        dentistaService.deleteDentistaByEspecialidade(especialidade);
    }
}
