package com.academic_manager.service;

import com.academic_manager.dto.AlunoResponseDTO;
import com.academic_manager.dto.CursoRequestDTO;
import com.academic_manager.dto.CursoResponseDTO;
import com.academic_manager.entity.Curso;
import com.academic_manager.exception.AlreadyExistsException;
import com.academic_manager.exception.ResourceNotFoundException;
import com.academic_manager.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CursoService {

    private final CursoRepository repository;

    @Transactional
    public CursoResponseDTO criar(CursoRequestDTO request){
        if (repository.existsByNome(request.nome())){
            throw new AlreadyExistsException("Curso ja cadastrado com o nome: " + request.nome());
        }

        Curso curso = new Curso();
        curso.setNome(request.nome());
        curso.setDescricao(request.descricao());

        Curso cursoSalvo = repository.save(curso);
        return CursoResponseDTO.fromEntity(cursoSalvo);
    }

    @Transactional(readOnly = true)
    public List<CursoResponseDTO> listar(){
        return repository.findAll().stream().map(CursoResponseDTO::fromEntity).toList();
    }

    @Transactional(readOnly = true)
    public CursoResponseDTO buscarPorId(Long id){
        Curso curso = buscarEntidadePorId(id);
        return CursoResponseDTO.fromEntity(curso);
    }

    @Transactional
    public CursoResponseDTO atualizar(Long id , CursoRequestDTO request){
        Curso curso = buscarEntidadePorId(id);

        if (repository.existsByNomeAndIdNot(request.nome() , id)){
            throw new AlreadyExistsException("Curso ja cadastrado com o nome: " + request.nome());
        }

        curso.setNome(request.nome());
        curso.setDescricao(request.descricao());

        Curso cursoAtualizado = repository.save(curso);

        return CursoResponseDTO.fromEntity(cursoAtualizado);
    }

    @Transactional
    public void deletar(Long id){
        Curso curso = buscarEntidadePorId(id);

        repository.delete(curso);
    }



    private Curso buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Curso nao encontrado com o id: " + id));
    }
}
