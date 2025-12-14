package odontologica.clinica.clinicaOdont.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import odontologica.clinica.clinicaOdont.dto.dentista.DentistaCreateDTO;
import odontologica.clinica.clinicaOdont.model.enums.Especialidade;

@Getter
@Setter
@Entity
@Table(name = "tb_dentistas")
public class Dentista {


    public Dentista(DentistaCreateDTO dto) {
        this.nome = dto.nome();
        this.cro = dto.cro();
        this.especialidade = dto.especialidade();
        this.telefone = dto.telefone();
        this.email = dto.email();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo NOME é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "o atributo CRO é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cro;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Especialidade deve conter somente: ORTODONTIA, ENDODONTIA, PERIODONTIA, ODONTOPEDIATRIA ou CIRURGIA_BUCAL")
    @Column(nullable = false)
    private Especialidade especialidade;

    @NotBlank(message = "o atributo TELEFONE é obrigatório.")
    @Column(nullable = false, unique = true)
    private String telefone;

    @NotBlank(message = "o atributo EMAIL é obrigatório.")
    @Column(nullable = false, unique = true)
    private String email;


}
