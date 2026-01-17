package odontologica.clinica.clinicaOdont.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaCreateDTO;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaResponseDTO;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.advice.ErrorJson;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;
import odontologica.clinica.clinicaOdont.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Dentistas", description = "gerenciador de endpoints de DENTISTAS.")
@ApiResponses({
        @ApiResponse(
                responseCode = "500",
                description = "Erro Interno.",
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = ErrorJson.class)
                )
        ), @ApiResponse(
        responseCode = "400",
        description = "Requisição inválida.",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorJson.class)
        )
)
})
@RequestMapping("/dentistas")
@RestController
public class DentistaController {

    private final DentistaService dentistaService;

    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    @Operation(summary = "Retorna dentista por id",
            description = "retorna um dentista existente via id. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Dentista localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = DentistaResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Dentista não encontrado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @GetMapping("/id/{id}")
    public ResponseEntity<DentistaResponseDTO> getDentistaById(@PathVariable Long id) {
        return ResponseEntity.ok(dentistaService.getDentistaById(id));
    }

    @Operation(summary = "Retorna lista de dentistas por ESPECIALIDADE",
            description = "retorna lista de dentistas via ESPECIALIDADE. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Dentista localizado"),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @GetMapping("/especialidade")
    public ResponseEntity<List<DentistaResponseDTO>> getDentistaByEspecialidade(
            @Parameter(description = "Filtrar por especialidade", example = "ORTODONTIA", required = true)
            @RequestParam Especialidade especialidade) {
        return ResponseEntity.ok(dentistaService.getDentistaByEspecialidade(especialidade));
    }

    @Operation(summary = "Retorna lista de dentistas por NOME",
            description = "retorna lista de dentistas via NOME. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Dentista localizado"),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @GetMapping("/nome")
    public ResponseEntity<List<DentistaResponseDTO>> getDentistaByNome(
            @Parameter(description = "Filtrar por nome", example = "Leonardo Samuel", required = true)
            @RequestParam String nome) {
        return ResponseEntity.ok(dentistaService.getDentistaByNome(nome));
    }

    @Operation(summary = "Retorna dentista por CRO",
            description = "retorna dentista via CRO. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Dentista localizado"),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @GetMapping("/cro/{cro}")
    public ResponseEntity<DentistaResponseDTO> getDentistaByCro(
            @Parameter(description = "Filtrar por cro", example = "23456", required = true)
            @PathVariable String cro) {
        return ResponseEntity.ok(dentistaService.getDentistaByCro(cro));
    }

    @Operation(summary = "Retorna lista de dentistas cadastrados",
            description = "retorna lista de dentistas cadastrados. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Dentista(s) localizado(s)"),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @GetMapping("/lote")
    public ResponseEntity<List<DentistaResponseDTO>> getDentistas() {
        return ResponseEntity.ok(dentistaService.getDentistas());
    }

    @Operation(summary = "criar dentista",
            description = "cria um dentista. pode falhar em caso de dados inválidos ou dentista já existir")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Dentista criado."
            ),
            @ApiResponse(
                    responseCode = "409", description = "Dentista já existente."
            )
    })
    @PostMapping
    public ResponseEntity<DentistaResponseDTO> createDentista(@RequestBody DentistaCreateDTO dentistaCreateDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaService.createDentista(dentistaCreateDTO));
    }

    @Operation(summary = "criar lista de dentistas",
            description = "cria uma lista de dentistas. pode falhar em caso de dados inválidos ou dentista(s) já existir(em)")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Dentista(s) criado(s)."
            ),
            @ApiResponse(
                    responseCode = "409", description = "Dentista(s) já existente(s)."
            )
    })
    @PostMapping("/lote")
    public ResponseEntity<List<DentistaResponseDTO>> createDentistas(@RequestBody List<DentistaCreateDTO> dentistaList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dentistaService.createDentistas(dentistaList));
    }

    @Operation(summary = "atualiza dentista por id",
            description = "atualiza dentista por id. pode falhar em caso de dados inválidos ou dentista nao encontrado.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Dentista atualizado."
            ),
            @ApiResponse(
                    responseCode = "409", description = "Dentista(s) já existente(s)."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<DentistaResponseDTO> updateDentistaById(
            @Parameter(description = "Filtrar por ID", example = "2L", required = true)
            @PathVariable Long id,
            @RequestBody DentistaUpdateDTO novoDentista) {
        return ResponseEntity.ok(dentistaService.updateDentistaById(id, novoDentista));
    }

    @Operation(summary = "atualiza dentista por cro",
            description = "atualiza dentista por cro. pode falhar em caso de dados inválidos ou dentista nao encontrado.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Dentista atualizado."
            ),
            @ApiResponse(
                    responseCode = "409", description = "Dentista(s) já existente(s)."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PutMapping("/cro/{cro}")
    public ResponseEntity<DentistaResponseDTO> updateDentistaByCro(

            @Parameter(description = "Filtrar por CRO", example = "23456", required = true)
            @PathVariable String cro,
            @RequestBody DentistaUpdateDTO novoDentista) {
        return ResponseEntity.ok(dentistaService.updateDentistaByCro(cro, novoDentista));
    }

    @Operation(summary = "atualiza dentista por nome",
            description = "atualiza dentista por nome. pode falhar em caso de dados inválidos ou dentista nao encontrado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Dentista atualizado."
            ),
            @ApiResponse(
                    responseCode = "409", description = "Dentista(s) já existente(s)."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PutMapping("/nome/{nome}")
    public ResponseEntity<DentistaResponseDTO> updateDentistaByNome(
            @Parameter(description = "Filtrar por CRO", example = "Maria Souza", required = true)
            @PathVariable String nome,
            @RequestBody DentistaUpdateDTO novoDentista) {
        return ResponseEntity.ok(dentistaService.updateDentistaByNome(nome, novoDentista));
    }

    @Operation(summary = "deleta dentista por id",
            description = "deleta dentista por id. pode falhar em caso de dentista nao encontrado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Dentista deletado."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteDentistaById(@PathVariable Long id) {
        dentistaService.deleteDentistaById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "deleta dentista por nome",
            description = "deleta dentista por nome. pode falhar em caso de dentista nao encontrado.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Dentista deletado."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = ErrorJson.class)
            ))
    })
    @DeleteMapping("/nome/{nome}")
    public ResponseEntity<Void> deleteDentistaByNome(
            @Parameter(description = "Filtrar por NOME", example = "Carlos Mendes", required = true)
            @PathVariable String nome) {
        dentistaService.deleteDentistaByNome(nome);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "deleta dentista por especialidade",
            description = "deleta dentista por especialidade. pode falhar em caso de dentista nao encontrado")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Dentista deletado."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Dentista não encontrado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)
                    ))
    })
    @DeleteMapping("/especialidade/{especialidade}")
    public ResponseEntity<Void> deleteDentistaByEspecialidade(
            @Parameter(description = "Filtrar por ESPECIALIDADE", example = "PERIODONTIA", required = true)
            @PathVariable
            @Schema(implementation = Especialidade.class) Especialidade especialidade) {
        dentistaService.deleteDentistaByEspecialidade(especialidade);
        return ResponseEntity.noContent().build();
    }
}
