package odontologica.clinica.clinicaOdont.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import odontologica.clinica.clinicaOdont.dto.agendamento.AgendamentoCreateDTO;
import odontologica.clinica.clinicaOdont.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_agendamentos")
public class Agendamento {

    public Agendamento() { }

    public Agendamento(
            LocalDateTime dataHora,
            StatusAgendamento status,
            Dentista dentista,
            Paciente paciente,
            Servico servico
    ) {
        this.dataHora = dataHora;
        this.status = status;
        this.dentista = dentista;
        this.paciente = paciente;
        this.servico = servico;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future
    @NotNull
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;


    // GETTERS E SETTERS
    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;

    }

}
