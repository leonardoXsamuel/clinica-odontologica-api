package odontologica.clinica.clinicaOdont.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
import odontologica.clinica.clinicaOdont.dto.servico.ServicoCreateDTO;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "tb_servicos")
public class Servico {

    public Servico() { }

    public Servico(ServicoCreateDTO dto){
        this.nome = dto.nome();
        this.descricao = dto.descricao();
        this.valor = dto.valor();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "o atributo NOME é obrigatório.")
    private String nome;

    @Column(nullable = false)
    @NotBlank(message = "o atributo DESCRICAO é obrigatório.")
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = "o atributo VALOR é obrigatório.")
    @Positive(message = "o atributo VALOR deve ser POSITIVO.")
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
