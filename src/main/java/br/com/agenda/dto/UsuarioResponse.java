package br.com.agenda.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode
public class UsuarioResponse {
    private Long id;
    private String nome;
    private LocalDate aniversario;
    private String cpf;
    private String email;
    private String telefone;
}
