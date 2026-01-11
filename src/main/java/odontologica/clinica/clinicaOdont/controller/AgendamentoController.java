package odontologica.clinica.clinicaOdont.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.advice.ErrorJson;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;
import odontologica.clinica.clinicaOdont.service.AgendamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Agendamentos", description = "gerenciador de endpoints de AGENDAMENTO.")
@ApiResponses({
        @ApiResponse(
                responseCode = "500",
                description = "Erro Interno.",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorJson.class)
                )
        )
})
@RequestMapping("/agendamentos")
@RestController
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
    @Operation(summary = "Retorna agendamento por id",
            description = "retorna um pedido existente via id do agendamento. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Agendamento localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgendamentoResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID digitado inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Agendamento não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    public AgendamentoResponseDTO getAgendamentoById(@PathVariable Long id) {
        return agendamentoService.getAgendamentoById(id);
    }

    @Operation(summary = "Retorna lista de agendamentos existentes.",
            description = "busca agendamentos existentes. lança RESOURCE NOT FOUND EXC. se não for encontrado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Agendamento(s) encontrado(s)."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Agendamento(s) não encontrado(s). RESOURCE NOT FOUND EXC"
            ), @ApiResponse(
            responseCode = "400", description = " status inválido."
    )
    })
    @GetMapping("/lote")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentos() {
        return ResponseEntity.ok(agendamentoService.getAgendamentos());
    }

    @Operation(summary = "buscar lista de agendamentos por status",
            description = "retorna agendamentos existentes. lança RESOURCE NOT FOUND EXC. se não for encontrado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Agendamento(s) encontrado(s)."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Agendamento(s) não encontrado(s). RESOURCE NOT FOUND EXC"
            ), @ApiResponse(
            responseCode = "400", description = " status inválido."
    )
    })
    @GetMapping("/status/{status}")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentosByStatus(
            @Parameter(description = "Status do agendamento", example = "PENDENTE", required = true)
            @PathVariable("status")
            @Schema(implementation = StatusAgendamento.class) StatusAgendamento statusAgendamento) {
        return ResponseEntity.ok(agendamentoService.getAgendamentosByStatus(statusAgendamento));
    }

    @Operation(summary = "buscar lista de agendamentos por data",
            description = "retorna agendamentos existentes. lança RESOURCE NOT FOUND EXC. se não for encontrado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Agendamento(s) encontrado(s)."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Agendamento(s) não encontrado(s). RESOURCE NOT FOUND EXC"
            ),
            @ApiResponse(
                    responseCode = "400", description = "data inválida."
            )
    })
    @GetMapping("/data/{data}")
    public ResponseEntity<List<AgendamentoResponseDTO>> getAgendamentosByDate(
            @Parameter(description = "data", example = "2026-01-10", required = true)
            @PathVariable("data")
            @Schema(type = "string", example = "2026-01-10") LocalDate data) {
        return ResponseEntity.ok(agendamentoService.getAgendamentosByDate(data));
    }

    @Operation(summary = "criar agendamento",
            description = "cria um agendamento. pode falhar em caso de dados inválidos ou conflito de horário/dentista")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Agendamento criado."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos na requisição."
            ),
            @ApiResponse(
                    responseCode = "409", description = "Agendamento conflitante. horário/dentista indisponível"
            ),
            @ApiResponse(
                    responseCode = "404", description = "Dentista/paciente não localizado."
            )
    })
    @PostMapping
    public ResponseEntity<AgendamentoResponseDTO> createAgendamento(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados para criação do agendamento",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgendamentoCreateDTO.class)
                    )
            )
            @RequestBody AgendamentoCreateDTO agendamento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoService.createAgendamento(agendamento));
    }

    @Operation(summary = "Cria uma lista de agendamentos em lote.",
            description = "Cria uma lista de agendamentos em lote. pode falhar em caso de dados inválidos ou conflito de horário/dentista")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Agendamento(s) criado(s)."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos na requisição.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Agendamento(s) conflitante(s). horário(s)/dentista(s) indisponível",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dentista(s)/paciente(s) não localizado(s).",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            )})
    @PostMapping("/lote")
    public ResponseEntity<List<AgendamentoResponseDTO>> createAgendamentos(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados para criação dos agendamentos",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AgendamentoCreateDTO.class))
                    )
            )
            @RequestBody List<AgendamentoCreateDTO> listAgendamentos) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(agendamentoService.createAgendamentos(listAgendamentos));
    }

    @Operation(summary = "atualiza agendamento por id",
            description = "atualiza agendamento por id. pode falhar em caso de dados inválidos ou conflito de horário/dentista")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Agendamento atualizado."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos na requisição.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Agendamento(s) conflitante(s). horário(s)/dentista(s) indisponível",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dentista(s)/paciente(s) não localizado(s).",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            )})
    @PutMapping("atualizarAgendamento/{id}")
    public ResponseEntity<AgendamentoResponseDTO> updateAgendamentoById(
            @Parameter(
                    description = "ID do agendamento",
                    example = "2L",
                    required = true
            ) @PathVariable Long id,

            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "Dados para atualização do agendamento",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AgendamentoUpdateDTO.class))
            )
            @RequestBody AgendamentoUpdateDTO novoAgendamento) {
        return ResponseEntity.ok(agendamentoService.updateAgendamentoById(id, novoAgendamento));
    }

    @Operation(summary = "deleta agendamento por id",
            description = "atualiza agendamento por id. pode falhar em caso de id inválido/inexistente ou conflito de horário/dentista")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Agendamento deletado."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dentista(s)/paciente(s) não localizado(s).",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            )})
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteAgendamentoById(
            @Parameter(
                    description = "ID do agendamento",
                    example = "2L",
                    required = true
            )
            @PathVariable Long id
    ) {
        agendamentoService.deleteAgendamentoById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "deleta agendamento por status",
            description = "atualiza agendamento por status. pode falhar em caso de status inválido/inexistente ou conflito de horário/dentista")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Agendamento deletado."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dentista(s)/paciente(s) não localizado(s).",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    )
            )})
    @DeleteMapping("status/{status}")
    public ResponseEntity<Void> deleteAgendamentoByStatus(
            @Parameter(
                    description = "Status do agendamento",
                    example = "CONFIRMADO",
                    required = true
            )
            @PathVariable
            @Schema(implementation = StatusAgendamento.class) StatusAgendamento status) {
        agendamentoService.deleteAgendamentoByStatus(status);
        return ResponseEntity.noContent().build();
    }
}
