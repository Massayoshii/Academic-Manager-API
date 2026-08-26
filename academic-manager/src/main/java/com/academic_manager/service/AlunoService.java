package com.academic_manager.service;

import com.academic_manager.dto.AlunoRequestDTO;
import com.academic_manager.dto.AlunoResponseDTO;
import com.academic_manager.entity.Aluno;
import com.academic_manager.entity.Curso;
import com.academic_manager.exception.AlreadyExistsException;
import com.academic_manager.exception.ResourceNotFoundException;
import com.academic_manager.repository.AlunoRepository;
import com.academic_manager.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;
    private final CursoRepository cursoRepository;

    @Transactional
    public AlunoResponseDTO cadastrar(AlunoRequestDTO request) {
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Curso nao encontrado com o id: "+ request.cursoId()));

        Aluno aluno = new Aluno();

        aluno.setNome(request.nome());
        aluno.setEmail(request.email());
        aluno.setCpf(request.cpf());
        aluno.setDataNascimento(request.dataNascimento());
        aluno.setCurso(curso);

        Aluno alunoSalvo = repository.save(aluno);
        return AlunoResponseDTO.fromEntity(alunoSalvo);
    }

    @Transactional(readOnly = true)
    public List<AlunoResponseDTO> listar() {
        return repository.findAll().stream().map(AlunoResponseDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO buscarPorId(Long id){
        Aluno aluno = buscarEntidadePorId(id);
        return AlunoResponseDTO.fromEntity(aluno);
    }

    @Transactional
    public AlunoResponseDTO atualizar(Long id , AlunoRequestDTO request){
        Aluno aluno = buscarEntidadePorId(id);
        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Curso nao encontrado com o id: "+ request.cursoId()));

        if (repository.existsByEmailAndIdNot(request.email() , id)){
            throw new AlreadyExistsException("Aluno ja cadastrado com email: " + request.email());
        }
        if (repository.existsByCpfAndIdNot(request.cpf() , id)){
            throw new AlreadyExistsException("CPF ja cadastraddo com CPF : " + request.cpf());
        }

        aluno.setNome(request.nome());
        aluno.setEmail(request.email());
        aluno.setCpf(request.cpf());
        aluno.setDataNascimento(request.dataNascimento());
        aluno.setCurso(curso);

        Aluno alunoAtualizado = repository.save(aluno);

        return AlunoResponseDTO.fromEntity(alunoAtualizado);
    }

    @Transactional
    public void deletar(Long id){
        Aluno aluno = buscarEntidadePorId(id);
        repository.delete(aluno);
    }



    private Aluno buscarEntidadePorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno nao encontrado com o id: " + id));
    }
}
