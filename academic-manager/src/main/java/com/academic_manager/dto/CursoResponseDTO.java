package com.academic_manager.dto;

import com.academic_manager.entity.Curso;

public record CursoResponseDTO(
        Long id,
        String nome,
        String descricao
) {
    public static CursoResponseDTO fromEntity(Curso curso){
        return new CursoResponseDTO(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao()
        );
    }
}
