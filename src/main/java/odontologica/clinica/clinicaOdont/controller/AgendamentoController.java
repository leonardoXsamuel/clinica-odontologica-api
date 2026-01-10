package odontologica.clinica.clinicaOdont.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoUpdateDTO;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;
import odontologica.clinica.clinicaOdont.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequestMapping("/agendamentos")
@RestController
@Tag(name = "Agendamentos", description = "gerenciador de endpoints de AGENDAMENTO.")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @GetMapping("/teste")
    public String testeDeRota() {
        return "ROTA TESTE FUNCIONANDO PERFEITAMENTE";
    }

    @GetMapping("/{id}")
    @Operation(summary = "retornar agendamento por id", description = "retorna um pedido existente via id do agendamento. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    public AgendamentoResponseDTO getAgendamentoById(@PathVariable Long id) {
        return agendamentoService.getAgendamentoById(id);
    }

    @GetMapping("/lote")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentos() {
        return ResponseEntity.ok(agendamentoService.getAgendamentos());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<AgendamentoResponseDTO> getAgendamentosByStatus(@PathVariable
                                                                              @Schema(implementation = StatusAgendamento.class) StatusAgendamento statusAgendamento) {
        return ResponseEntity.ok(agendamentoService.getAgendamentosByStatus(statusAgendamento));
    }

    @GetMapping("/data/{data}")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentosByDate(@PathVariable
                                                                                  @Schema(type = "string", example = "2026-01-10")LocalDate data) {
        return ResponseEntity.ok(agendamentoService.getAgendamentosByDate(data));
    }

    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> createAgendamento(@RequestBody AgendamentoCreateDTO agendamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.createAgendamento(agendamento));
    }

    @PostMapping("/lote")
    public ResponseEntity<List<AgendamentoResponseDTO>> createAgendamentos(@RequestBody List<AgendamentoCreateDTO> listAgendamentos) {
        return ResponseEntity.ok(agendamentoService.createAgendamentos(listAgendamentos));
    }

    @PutMapping("atualizarAgendamento/{id}")
    public ResponseEntity<AgendamentoResponseDTO> updateAgendamentoById(@PathVariable Long id, @RequestBody AgendamentoUpdateDTO novoAgendamento) {
        return ResponseEntity.ok(agendamentoService.updateAgendamentoById(id, novoAgendamento));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAgendamentoById(@PathVariable Long id) {
        agendamentoService.deleteAgendamentoById(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("status/{status}")
    public ResponseEntity<Void> deleteAgendamentoByStatus(@PathVariable @Schema(implementation = StatusAgendamento.class) StatusAgendamento status) {
        agendamentoService.deleteAgendamentoByStatus(status);
        return ResponseEntity.ok().build();
    }
}
