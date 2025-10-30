package odontologica.clinica.clinicaOdont.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tb_dentistas")
public class Dentista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "o atributo NOME é obrigatório.")
    @Column(nullable = false)
    private String nome;

    @NotBlank(message = "o atributo CRO é obrigatório.")
    @Column(nullable = false, unique = true)
    private String cro;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private Especialidade especialidade;

    public enum Especialidade{
        ORTODONTIA,
        ENDODONTIA,
        PERIODONTIA,
        ODONTOPEDIATRIA,
        CIRURGIA_BUCAL
    }

    @NotNull
    @Column(nullable = false, unique = true)
    private String telefone;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCro() {
        return cro;
    }

    public void setCro(String cro) {
        this.cro = cro;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
