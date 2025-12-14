package odontologica.clinica.clinicaOdont.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Getter;
import lombok.Setter;
import odontologica.clinica.clinicaOdont.dto.paciente.PacienteCreateDTO;
import org.hibernate.validator.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tb_pacientes")
public class Paciente {

    public Paciente (PacienteCreateDTO dto){
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.dataNascimento = dto.dataNascimento();
        this.email = dto.email();
        this.telefone = dto.telefone();
    }

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

}
