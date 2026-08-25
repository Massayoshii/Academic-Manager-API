package com.academic_manager.dto;

import com.academic_manager.entity.Aluno;

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

    public static AlunoResponseDTO fromEntity(Aluno aluno){
        return new AlunoResponseDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getEmail(),
                aluno.getCpf(),
                aluno.getDataNascimento(),
                aluno.getCurso().getId(),
                aluno.getCurso().getNome()
        );
    }

}
