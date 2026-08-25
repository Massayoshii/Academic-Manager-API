package com.academic_manager.dto;

import com.academic_manager.entity.Curso;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlunoRequestDTO(
        String nome,
        String email,
        String cpf,
        LocalDate dataNascimento,
        Long cursoId
) {
}
