package br.com.agenda.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(max = 100)
    private String nome;

    @NotEmpty
    private LocalDate aniversario;

    @NotEmpty
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")
    private String cpf;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "\\(\\d{2}\\)\\d{4,5}-\\d{4}")
    private String telefone;

    public Usuario(String nome, LocalDate aniversario, String cpf, String email, String telefone) {
        this.nome = nome;
        this.aniversario = aniversario;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
