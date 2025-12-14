package odontologica.clinica.clinicaOdont.controller;

import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoResponseDTO;
import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.service.AgendamentoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RequestMapping("/agendamentos")
@RestController
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    /*
     * @PathVariable -> parte da rota(obrigatório>>>/dentistas/10)
     * @RequestParam -> usado para filtros(opcional)
     * @RequestBody  -> usado quando os dados do método virão de um OBJETO COMPLEXO(JSON ou XML)
     */

    @GetMapping("/teste")
    public String testeDeRota() {
        return "ROTA TESTE FUNCIONANDO PERFEITAMENTE";
    }
    
    @GetMapping("/{id}")
    public AgendamentoResponseDTO getAgendamentoById(@PathVariable Long id) {
        return agendamentoService.getAgendamentoById(id);
    }

    @GetMapping("/lote")
    public List<Agendamento> getAgendamentos() {
        return agendamentoService.getAgendamentos();
    }

    @GetMapping("/status/{status}")
    public Optional<Agendamento> getAgendamentosByStatus(@PathVariable StatusAgendamento statusAgendamento) {
        return agendamentoService.getAgendamentosByStatus(statusAgendamento);
    }

    @GetMapping("/data/{data}")
    public List<Agendamento> getAgendamentosByDate(@PathVariable LocalDate data) {
        return agendamentoService.getAgendamentosByDate(data);
    }

    @PostMapping
    public Agendamento createAgendamento(@RequestBody Agendamento agendamento) throws Exception {
        return agendamentoService.createAgendamento(agendamento);
    }

    @PostMapping("/lote")
    public List<Agendamento> createAgendamentos(@RequestBody List<Agendamento> listAgendamentos) throws Exception {
        return agendamentoService.createAgendamentos(listAgendamentos);
    }

    @PutMapping("atualizarAgendamento/{id}")
    public Agendamento updateAgendamentoById(@PathVariable Long id, @RequestBody Agendamento novoAgendamento) throws Exception {
        return agendamentoService.updateAgendamentoById(id, novoAgendamento);
    }

    @DeleteMapping("{id}")
    public void deleteAgendamentoById(@PathVariable Long id) throws Exception {
        agendamentoService.deleteAgendamentoById(id);
    }

    @DeleteMapping("status/{status}")
    public void deleteAgendamentoByStatus(@PathVariable Agendamento.StatusAgendamento status) throws Exception {
        agendamentoService.deleteAgendamentoByStatus(status);
    }
}
