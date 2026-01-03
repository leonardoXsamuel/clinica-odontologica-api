package odontologica.clinica.clinicaOdont.service.validator;

import odontologica.clinica.clinicaOdont.exceptions.ResourceNotFoundException;
import odontologica.clinica.clinicaOdont.model.Dentista;
import odontologica.clinica.clinicaOdont.model.Paciente;
import odontologica.clinica.clinicaOdont.model.Servico;
import odontologica.clinica.clinicaOdont.repository.DentistaRepository;
import odontologica.clinica.clinicaOdont.repository.PacienteRepository;
import odontologica.clinica.clinicaOdont.repository.ServicoRepository;

public class BuscaEntidadesPorId {

    private final DentistaRepository dentistaRepository;
    private final PacienteRepository pacienteRepository;
    private final ServicoRepository servicoRepository;

    public BuscaEntidadesPorId(DentistaRepository dentistaRepository, PacienteRepository pacienteRepository, ServicoRepository servicoRepository) {
        this.dentistaRepository = dentistaRepository;
        this.pacienteRepository = pacienteRepository;
        this.servicoRepository = servicoRepository;
    }

        public Entidades buscar(Long dentistaId, Long pacienteId, Long servicoId) {
            Dentista dentista = dentistaRepository.findById(dentistaId)
                    .orElseThrow(() -> new ResourceNotFoundException("Dentista não encontrado"));

            Paciente paciente = pacienteRepository.findById(pacienteId)
                    .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));

            Servico servico = servicoRepository.findById(servicoId)
                    .orElseThrow(() -> new ResourceNotFoundException("Serviço não encontrado"));

            return new Entidades(dentista, paciente, servico);
        }

        public record Entidades(Dentista dentista, Paciente paciente, Servico servico) {}
    }


