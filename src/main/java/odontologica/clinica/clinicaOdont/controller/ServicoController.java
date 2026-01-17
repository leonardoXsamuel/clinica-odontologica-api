package odontologica.clinica.clinicaOdont.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.advice.ErrorJson;
import odontologica.clinica.clinicaOdont.service.ServicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Servicos", description = "gerenciador de endpoints de SERVICOS.")
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
@RequestMapping("/servicos")
@RestController
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @Operation(summary = "Retorna serviços em lote",
            description = "retorna todos os serviços existentes. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Serviço(s) localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ServicoResponseDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Serviço(s) não localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @GetMapping("/lote")
    public ResponseEntity<List<ServicoResponseDTO>> getServicos() {
        return ResponseEntity.ok(servicoService.getServicos());
    }

    @Operation(summary = "Retorna serviço por id",
            description = "retorna um serviço via id. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Serviço localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ServicoResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Serviço não localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "ID digitado inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> getServicoById(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.getServicoById(id));
    }

    @Operation(summary = "Retorna serviço por nome",
            description = "retorna um serviço via nome. lança RESOURCE NOT FOUND EXCEPTION em caso de erro.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Serviço localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ServicoResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Serviço não localizado.",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "nome digitado inválido",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @GetMapping("/nome")
    public ResponseEntity<List<ServicoResponseDTO>> getServicoByNome(
            @Parameter(
                    description = "Nome do serviço para busca",
                    example = "Limpeza Dental",
                    required = true
            )
            @RequestParam String nome) {
        return ResponseEntity.ok(servicoService.getServicoByNome(nome));
    }

    @Operation(summary = "criar serviço",
            description = "cria um serviço. pode falhar em caso de dados inválidos.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Serviço criado."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos na requisição."
            )
    })
    @PostMapping
    public ResponseEntity<ServicoResponseDTO> createServico(@RequestBody ServicoCreateDTO createDTO) {
        ServicoResponseDTO responseDTO = servicoService.createServico(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @Operation(summary = "criar uma lista de serviços",
            description = "criar uma lista de serviços. pode falhar em caso de dados inválidos.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201", description = "Serviço(s) criado(s)."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos na requisição."
            )
    })
    @PostMapping("/lote")
    public ResponseEntity<List<ServicoResponseDTO>> createServicos(@RequestBody List<ServicoCreateDTO> servicoList) {
        List<ServicoResponseDTO> dtoList = servicoService.createServicos(servicoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoList);
    }

    @Operation(summary = "atualizar serviço via ID",
            description = "atualizar serviço via ID. pode falhar em caso de dados inválidos.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Serviço atualizado."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos na requisição."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Serviço não localizado por ID."
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ServicoResponseDTO> updateServicoById(@PathVariable Long id, @RequestBody ServicoUpdateDTO novoServicoDTO) {
        ServicoResponseDTO dto = servicoService.updateServicoById(id, novoServicoDTO);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "atualizar serviço via NOME",
            description = "atualizar serviço via NOME. pode falhar em caso de dados inválidos ou serviço inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200", description = "Serviço atualizado."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos na requisição."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Serviço não localizado por ID."
            )
    })
    @PutMapping("/nome")
    public ResponseEntity<ServicoResponseDTO> updateServicoByNome(
            @Parameter(description = "Nome do Serviço",
                    example = "Limpeza Bucal",
                    required = true)
            @RequestParam String nome,
            @RequestBody ServicoUpdateDTO novoServicoDTO) {
        ServicoResponseDTO dto = servicoService.updateServicoByNome(nome, novoServicoDTO);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "deletar serviço via ID",
            description = "deletar serviço via ID. pode falhar em caso de id inválido ou ou serviço inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Serviço deletado."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos na requisição."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Serviço não localizado."
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteServicoById(@PathVariable Long id) {
        servicoService.deleteServicoById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "deletar serviço via NOME",
            description = "deletar serviço via NOME. pode falhar em caso de nome inválido ou ou serviço inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204", description = "Serviço deletado."
            ),
            @ApiResponse(
                    responseCode = "400", description = "Dados inválidos na requisição."
            ),
            @ApiResponse(
                    responseCode = "404", description = "Serviço não localizado."
            )
    })
    @DeleteMapping("/nome")
    public ResponseEntity<Void> deleteServicoByNome(@RequestParam String nome) {
        servicoService.deleteServicoByNome(nome);
        return ResponseEntity.noContent().build();
    }

}
