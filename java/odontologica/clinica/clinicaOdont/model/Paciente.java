package odontologica.clinica.clinicaOdont.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Table(name = "tb_pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "o atributo NOME é obrigatório.")
    private String nome;

    @NotBlank(message = "o atributo TELEFONE é obrigatório.")
    @Column(nullable = false, unique = true)
    private String telefone;

    @Email
    @NotBlank(message = "o atributo EMAIL é obrigatório.")
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "o atributo CPF é obrigatório.")
    private String cpf;

    @Past
    @NotNull
    @Column(nullable = false)
    private LocalDate dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

}
