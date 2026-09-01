package com.academic_manager.dto;

import com.academic_manager.entity.Professor;

public record ProfessorResponseDTO(
        Long id,
        String nome,
        String email,
        String especialidade
) {

    public static ProfessorResponseDTO fromEntity(Professor professor){
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getNome(),
                professor.getEmail(),
                professor.getEspecialidade()
        );
    }
}
