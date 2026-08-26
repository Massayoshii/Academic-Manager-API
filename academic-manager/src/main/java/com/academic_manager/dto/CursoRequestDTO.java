package com.academic_manager.dto;

import jakarta.validation.constraints.NotBlank;

public record CursoRequestDTO(
        @NotBlank(message = "O nome é obrigatorio")
        String nome,
        @NotBlank(message = "A descricao é obrigatorio")
        String descricao
) {
}
