package odontologica.clinica.clinicaOdont.service;

import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.ConflictException;
import odontologica.clinica.clinicaOdont.exceptions.ResourceNotFoundException;
import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;
import odontologica.clinica.clinicaOdont.repository.AgendamentoRepository;
import odontologica.clinica.clinicaOdont.repository.DentistaRepository;
import odontologica.clinica.clinicaOdont.repository.PacienteRepository;
import odontologica.clinica.clinicaOdont.repository.ServicoRepository;
import odontologica.clinica.clinicaOdont.service.validator.AtualizaCampos;
import odontologica.clinica.clinicaOdont.service.validator.BuscaEntidadesPorId;
import odontologica.clinica.clinicaOdont.service.validator.ValidaConflito;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final DentistaRepository dentistaRepository;
    private final PacienteRepository pacienteRepository;
    private final ServicoRepository servicoRepository;

    private final ValidaConflito validaConflito;
    private final AtualizaCampos atualizaCampos;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, DentistaRepository dentistaRepository, PacienteRepository pacienteRepository, ServicoRepository servicoRepository, ValidaConflito validaConflito, AtualizaCampos atualizaCampos) {
        this.agendamentoRepository = agendamentoRepository;
        this.dentistaRepository = dentistaRepository;
        this.pacienteRepository = pacienteRepository;
        this.servicoRepository = servicoRepository;
        this.validaConflito = validaConflito;
        this.atualizaCampos = atualizaCampos;
    }

    public List<AgendamentoResponseDTO> getAgendamentos() {
        List<Agendamento> agendamentoList = agendamentoRepository.findAll();

        if (agendamentoList.isEmpty()) {
            throw new ResourceNotFoundException("Não existe nenhum registro de agendamentos no Banco de Dados.");
        }

        return agendamentoList.stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    public AgendamentoResponseDTO getAgendamentoById(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        return new AgendamentoResponseDTO(agendamento);
    }

    public List<AgendamentoResponseDTO> getAgendamentosByStatus(StatusAgendamento status) {
        List<Agendamento> agendamentos = agendamentoRepository.findByStatus(status);

        if (agendamentos.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum agendamento encontrado");
        }

        return agendamentos.stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    public List<AgendamentoResponseDTO> getAgendamentosByDate(LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23, 59, 59);

        List<Agendamento> agendamentos = agendamentoRepository
                .findByDataHoraBetween(inicio, fim)
                .orElseThrow(ResourceNotFoundException::new);

        return agendamentos
                .stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    public AgendamentoResponseDTO createAgendamento(AgendamentoCreateDTO dto) {

        // busca entidades por id
        BuscaEntidadesPorId busca = new BuscaEntidadesPorId(dentistaRepository, pacienteRepository, servicoRepository);
        BuscaEntidadesPorId.Entidades entidades = busca.buscar(dto.dentistaId(), dto.pacienteId(), dto.servicoId());

        Optional<Agendamento> existente = agendamentoRepository
                .findByDentistaIdAndDataHora(dto.dentistaId(), dto.dataHora());

        if (existente.isPresent()) {
            throw new ConflictException("O dentista já possui um agendamento nesse horário.");
        }

        Agendamento agendamento = new Agendamento(
                dto.dataHora(),
                dto.statusAgendamento(),
                entidades.dentista(),
                entidades.paciente(),
                entidades.servico());
        agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamento);
    }

    public List<AgendamentoResponseDTO> createAgendamentos(List<AgendamentoCreateDTO> dtoList) {

        BuscaEntidadesPorId busca = new BuscaEntidadesPorId(dentistaRepository, pacienteRepository, servicoRepository);

        List<Agendamento> agendamentoList = dtoList.stream()
                .map(dto -> {
                    BuscaEntidadesPorId.Entidades entidades = busca.buscar(
                            dto.dentistaId(),
                            dto.pacienteId(),
                            dto.servicoId()
                    );

                    return new Agendamento(
                            dto.dataHora(),
                            dto.statusAgendamento(),
                            entidades.dentista(),
                            entidades.paciente(),
                            entidades.servico()
                    );
                })
                .toList();

        List<Agendamento> agendamentos = agendamentoRepository.saveAll(agendamentoList);

        return agendamentos.stream()
                .map(AgendamentoResponseDTO::new)
                .toList();
    }

    private Agendamento buscarAgendamento(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("o id requisitado não foi localizado"));
    }


    @Transactional
    public AgendamentoResponseDTO updateAgendamentoById(Long id, AgendamentoUpdateDTO novoAgendamento) {
        Agendamento antigoAgendamento = buscarAgendamento(id);
        validaConflito.validarConflito(id, novoAgendamento);
        atualizaCampos.atualizarCampos(novoAgendamento, antigoAgendamento);
        Agendamento agendamento = agendamentoRepository.save(antigoAgendamento);

        return new AgendamentoResponseDTO(agendamento);
    }

    @Transactional
    public void deleteAgendamentoById(Long id) {

        agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("o id " + id + " não foi localizado"));

        agendamentoRepository.deleteById(id);
    }

    @Transactional
    public void deleteAgendamentoByStatus(StatusAgendamento statusAgendamento) {

        List<Agendamento> agendamentosToDelete = agendamentoRepository.findByStatus(statusAgendamento);

        if (agendamentosToDelete.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum agendamento encontrado");
        }

        agendamentoRepository.deleteByStatus(statusAgendamento);
    }

}
