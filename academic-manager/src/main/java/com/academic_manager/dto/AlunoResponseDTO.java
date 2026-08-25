package com.academic_manager.dto;

import java.time.LocalDate;

public record AlunoResponseDTO(
        Long id,
        String nome,
        String email,
        String cpf,
        LocalDate dataNascimento,
        Long cursoId,
        String cursoNome
) {

}
