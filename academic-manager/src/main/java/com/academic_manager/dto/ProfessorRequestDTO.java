package com.academic_manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDTO(
        @NotBlank(message = "O nome é obrigatorio")
        String nome,
        @Email(message = "O email deve ser valido")
        @NotBlank(message = "O email é obrigatorio")
        String email,
        @NotBlank(message = "A especialidade é obrigatorio")
        String especialidade
) {
}
