package com.academic_manager.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AlunoRequestDTO(
        @NotBlank(message = "O nome é obrigatorio")
        String nome,

        @Email(message = "O email deve ser valido")
        @NotBlank(message = "O email é obrigatorio")
        String email,

        @NotBlank(message = "O CPF é obrigatorio")
        String cpf,

        @NotNull(message = "A data de nascimento é obrigatorio")
        @Past(message = "A data de nascimento deve estar no passado")
        LocalDate dataNascimento,

        @NotNull(message = "O curso é obrigatorio")
        Long cursoId
) {
}
