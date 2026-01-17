package odontologica.clinica.clinicaOdont.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteCreateDTO;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteResponseDTO;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.advice.ErrorJson;
import odontologica.clinica.clinicaOdont.service.PacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Pacientes", description = "gerenciador de endpoints de PACIENTES.")
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
        description = "Dado(s) inválido(s) na requisição.",
        content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorJson.class)
        )
)
})
@RequestMapping("/pacientes")
@RestController
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @Operation(summary = "Retorna os pacientes registrados",
            description = "Retorna os pacientes registrados. Pode falhar caso no exista nenhum paciente")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente(s) localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PacienteResponseDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente(s) não localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @GetMapping
    public ResponseEntity<List<PacienteResponseDTO>> getPacientes() {
        return ResponseEntity.ok(pacienteService.getPacientes());
    }

    @Operation(summary = "Retorna os pacientes por ID",
            description = "Retorna os pacientes por ID. Pode falhar caso ID seja inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente(s) não localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @GetMapping("{id}")
    public ResponseEntity<PacienteResponseDTO> getPacientesById(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.getPacienteById(id));
    }

    @Operation(summary = "Retorna os pacientes por CPF",
            description = "Retorna os pacientes por CPF. Pode falhar caso CPF seja inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente(s) não localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @GetMapping("cpf/{cpf}")
    public ResponseEntity<PacienteResponseDTO> getPacienteByCPF(
            @Parameter(
                    description = "CPF do paciente para busca",
                    example = "10101010101",
                    required = true
            )
            @PathVariable String cpf) {
        return ResponseEntity.ok(pacienteService.getPacienteByCPF(cpf));
    }

    @Operation(summary = "Retorna os pacientes por NOME",
            description = "Retorna os pacientes por NOME. Pode falhar caso NOME seja inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente localizado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PacienteResponseDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente(s) não localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @GetMapping("nome")
    public ResponseEntity<List<PacienteResponseDTO>> getPacienteByNome(
            @Parameter(
                    description = "NOME do paciente para busca",
                    example = "Leonardo Samuel",
                    required = true
            )
            @RequestParam String nome) {
        return ResponseEntity.ok(pacienteService.getPacienteByNome(nome));
    }

    @Operation(summary = "Retorna os pacientes por TELEFONE",
            description = "Retorna os pacientes por TELEFONE. Pode falhar caso TELEFONE seja inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente localizado",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PacienteResponseDTO.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente(s) não localizado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @GetMapping("telefone/{telefone}")
    public ResponseEntity<PacienteResponseDTO> getPacienteByTelefone(
            @Parameter(
                    description = "TELEFONE do paciente para busca",
                    example = "11900000000",
                    required = true
            )
            @PathVariable String telefone) {
        return ResponseEntity.ok(pacienteService.getPacienteByTelefone(telefone));
    }

    @Operation(summary = "Cria paciente",
            description = "Cria paciente. Pode falhar caso algum atributo seja inválido.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Paciente criado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Paciente já existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PostMapping
    public ResponseEntity<PacienteResponseDTO> createPaciente(@RequestBody PacienteCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.createPaciente(dto));
    }

    @Operation(summary = "Cria paciente(s)",
            description = "Cria paciente(s). Pode falhar caso algum atributo seja inválido.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Paciente(s) criado(s)",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = PacienteResponseDTO.class)))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Paciente(s) já existe(m)",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PostMapping("/lote")
    public ResponseEntity<List<PacienteResponseDTO>> createPacientes(@RequestBody List<PacienteCreateDTO> pacienteList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.createPacientes(pacienteList));
    }

    @Operation(summary = "Atualiza paciente via ID",
            description = "Atualiza paciente via ID. Pode falhar caso algum atributo seja inválido, o ID sejá inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente atualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não foi localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Paciente com esse atributo já existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PutMapping("{id}")
    public ResponseEntity<PacienteResponseDTO> updatePacienteById(@PathVariable Long id, @RequestBody PacienteUpdateDTO novoPaciente) {
        PacienteResponseDTO dto = pacienteService.updatePacienteById(id, novoPaciente);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Atualiza paciente via CPF",
            description = "Atualiza paciente via CPF. Pode falhar caso algum atributo seja inválido, o CPF sejá inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente atualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não foi localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Paciente com esse atributo já existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PutMapping("/cpf/{cpf}")
    public ResponseEntity<PacienteResponseDTO> updatePacienteByCPF(@PathVariable String cpf, @RequestBody PacienteUpdateDTO novoPaciente) {
        PacienteResponseDTO dto = pacienteService.updatePacienteByCPF(cpf, novoPaciente);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Atualiza paciente via NOME",
            description = "Atualiza paciente via NOME. Pode falhar caso algum atributo seja inválido, o NOME sejá inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Paciente atualizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = PacienteResponseDTO.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não foi localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Paciente com esse atributo já existe",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class)))
    })
    @PutMapping("/nome")
    public ResponseEntity<PacienteResponseDTO> updatePacienteByNome(@RequestParam String nome, @RequestBody PacienteUpdateDTO novoPaciente) {
        PacienteResponseDTO dto = pacienteService.updatePacienteByNome(nome, novoPaciente);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Deleta paciente via ID",
            description = "Deleta paciente via ID. Pode falhar caso o ID sejá inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Paciente deletado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não foi localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePacienteById(@PathVariable Long id) {
        pacienteService.deletePacienteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Deleta paciente via CPF",
            description = "Deleta paciente via CPF. Pode falhar caso o CPF sejá inválido ou inexistente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Paciente deletado"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Paciente não foi localizado",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorJson.class))
            )
    })
    @DeleteMapping("/cpf/{cpf}")
    public ResponseEntity<Void> deletePacienteByCPF(@PathVariable String cpf) {
        pacienteService.deletePacienteByCPF(cpf);
        return ResponseEntity.noContent().build();
    }
}
