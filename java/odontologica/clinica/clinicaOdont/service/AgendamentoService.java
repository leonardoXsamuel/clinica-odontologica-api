package odontologica.clinica.clinicaOdont.service;

import odontologica.clinica.clinicaOdont.model.Agendamento;
import odontologica.clinica.clinicaOdont.repository.AgendamentoRepository;
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

    //GET
    public List<Agendamento> getAgendamentos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> getAgendamentoById(Long id) {
        return agendamentoRepository.findById(id);
    }

    public Optional<Agendamento> getAgendamentosByStatus(Agendamento.StatusAgendamento statusAgendamento) {
        return agendamentoRepository.findByStatus(statusAgendamento);
    }

    public List<Agendamento> getAgendamentosByDate(LocalDate data) {
        LocalDateTime inicio = data.atStartOfDay();
        LocalDateTime fim = data.atTime(23, 59, 59);
        return agendamentoRepository.findByDataHoraBetween(inicio, fim);
    }

    //POST
    public Agendamento createAgendamento(Agendamento agendamento) throws Exception {
        Optional<Agendamento> existente = agendamentoRepository
                .findByDentistaIdAndDataHora(agendamento.getDentista().getId(), agendamento.getDataHora());

        if (existente.isPresent()) {
            throw new Exception("O dentista já possui um agendamento nesse horário.");
        }

        return agendamentoRepository.save(agendamento);
    }

    //POST
    public List<Agendamento> createAgendamentos(List<Agendamento> agendamentoLista) throws Exception {

        for (Agendamento agendamento : agendamentoLista) {
            Optional<Agendamento> existente = agendamentoRepository
                    .findByDentistaIdAndDataHora(agendamento.getDentista().getId(), agendamento.getDataHora());

            if (existente.isPresent()) {
                throw new Exception("O dentista já possui um agendamento nesse horário: " + agendamento.getDataHora());
            }
        }
        return agendamentoRepository.saveAll(agendamentoLista);
    }

    //PUT
    // @Transactional -> garante que uma operação no banco de dados seja feita por completo ou revertida totalmente caso dê erro
    @Transactional
    public Agendamento updateAgendamentoById(Long id, Agendamento novoAgendamento) throws Exception {

        Agendamento antigoAgendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new Exception("o id requisitado não foi localizado"));

        Optional<Agendamento> conflito = agendamentoRepository
                .findByDentistaIdAndDataHora(novoAgendamento.getDentista().getId(), novoAgendamento.getDataHora());

        if (conflito.isPresent() && !conflito.get().getId().equals(id)) {
            throw new Exception("O dentista já possui um agendamento nesse horário.");
        }

        if (novoAgendamento.getDataHora() != null) {
            antigoAgendamento.setDataHora(novoAgendamento.getDataHora());
        }

        if (novoAgendamento.getStatus() != null) {
            antigoAgendamento.setStatus(novoAgendamento.getStatus());
        }

        return agendamentoRepository.save(antigoAgendamento);
    }

    //DELETE
    @Transactional
    public void deleteAgendamentoById(Long id) throws Exception {

        agendamentoRepository.findById(id)
                .orElseThrow(() -> new Exception("o id requisitado não foi localizado"));

        agendamentoRepository.deleteById(id);
    }

    @Transactional
    public void deleteAgendamentoByStatus(Agendamento.StatusAgendamento statusAgendamento) throws Exception {

        agendamentoRepository.findByStatus(statusAgendamento)
                .orElseThrow(() -> new Exception("o Status requisitado não foi localizado"));

        agendamentoRepository.deleteByStatus(statusAgendamento);
    }

}
