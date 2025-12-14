package odontologica.clinica.clinicaOdont.service;

import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoCreateDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoResponseDTO;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoUpdateDTO;
import odontologica.clinica.clinicaOdont.exceptions.ConflictException;
import odontologica.clinica.clinicaOdont.exceptions.ResourceNotFoundException;
import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;
import odontologica.clinica.clinicaOdont.repository.AgendamentoRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository) {
        this.agendamentoRepository = agendamentoRepository;
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

    public AgendamentoResponseDTO getAgendamentosByStatus(StatusAgendamento status) {
        Agendamento agendamento = agendamentoRepository.findByStatus(status)
                .orElseThrow(ResourceNotFoundException::new);

        return new AgendamentoResponseDTO(agendamento);
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

    public Agendamento createAgendamento(AgendamentoCreateDTO dto) {
        Optional<Agendamento> existente = agendamentoRepository
                .findByDentistaIdAndDataHora(dto.dentista().getId(), dto.dataHora());

        if (existente.isPresent()) {
            throw new ConflictException("O dentista já possui um agendamento nesse horário.");
        }

        Agendamento agendamento = new Agendamento(dto);

        return agendamentoRepository.save(agendamento);

    }

    public List<AgendamentoResponseDTO> createAgendamentos(List<AgendamentoCreateDTO> dtoList) {

        List<Agendamento> agendamentoList = dtoList.stream()
                .map(Agendamento::new)
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

    private void validarConflito(Long id, @NotNull AgendamentoUpdateDTO novoAgendamento) {
        Optional<Agendamento> conflito = agendamentoRepository
                .findByDentistaIdAndDataHora(novoAgendamento.dentista().getId(), novoAgendamento.dataHora());

        if (conflito.isPresent() && !conflito.get().getId().equals(id)) {
            throw new ConflictException("O dentista já possui um agendamento nesse horário.");
        }

    }

    private void atualizarCampos(@NotNull AgendamentoUpdateDTO novoAgendamento, Agendamento antigoAgendamento) {
        if (novoAgendamento.dataHora() != null) {
            antigoAgendamento.setDataHora(novoAgendamento.dataHora());
        }

        if (novoAgendamento.statusAgendamento() != null) {
            antigoAgendamento.setStatus(novoAgendamento.statusAgendamento());
        }
    }

    @Transactional
    public Agendamento updateAgendamentoById(Long id, AgendamentoUpdateDTO novoAgendamento) {
        Agendamento antigoAgendamento = buscarAgendamento(id);
        validarConflito(id, novoAgendamento);
        atualizarCampos(novoAgendamento, antigoAgendamento);
        return agendamentoRepository.save(antigoAgendamento);
    }

    @Transactional
    public void deleteAgendamentoById(Long id) {

        agendamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("o id " + id + " não foi localizado"));

        agendamentoRepository.deleteById(id);
    }

    @Transactional
    public void deleteAgendamentoByStatus(StatusAgendamento statusAgendamento) {

        agendamentoRepository.findByStatus(statusAgendamento)
                .orElseThrow(() -> new ResourceNotFoundException("o STATUS " + statusAgendamento + " não foi localizado"));

        agendamentoRepository.deleteByStatus(statusAgendamento);
    }

}
