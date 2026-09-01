package com.academic_manager.service;

import com.academic_manager.dto.ProfessorRequestDTO;
import com.academic_manager.dto.ProfessorResponseDTO;
import com.academic_manager.entity.Professor;
import com.academic_manager.exception.AlreadyExistsException;
import com.academic_manager.exception.ResourceNotFoundException;
import com.academic_manager.repository.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorService {

    private final ProfessorRepository repository;

    //CREATE
    @Transactional
    public ProfessorResponseDTO cadastrar(ProfessorRequestDTO request){
        if (repository.existsByEmail(request.email())){
            throw new AlreadyExistsException("Professor ja cadastrado com o email: " + request.email());
        }

        Professor professor = new Professor();
        professor.setNome(request.email());
        professor.setEmail(request.email());
        professor.setEspecialidade(request.especialidade());

        Professor professorSalvo = repository.save(professor);

        return ProfessorResponseDTO.fromEntity(professorSalvo);
    }

    //READ
    @Transactional(readOnly = true)
    public List<ProfessorResponseDTO> listar(){
        return repository.findAll().stream().map(ProfessorResponseDTO::fromEntity).toList();
    }

    //FIND BY ID
    @Transactional(readOnly = true)
    public ProfessorResponseDTO buscarPorId(Long id){
        Professor professor = buscarEntidadePorId(id);
        return ProfessorResponseDTO.fromEntity(professor);
    }

    //Atualizar
    @Transactional
    public ProfessorResponseDTO atualizar(Long id , ProfessorRequestDTO request){
        Professor professor = buscarEntidadePorId(id);
        if (repository.existsByEmailAndIdNot(request.email() , id)){
            throw new AlreadyExistsException("Ja existe email com esse nome: "+ request.email());
        }

        professor.setNome(request.nome());
        professor.setEmail(request.email());
        professor.setEspecialidade(request.especialidade());

        Professor professorAtualizado = repository.save(professor);

        return ProfessorResponseDTO.fromEntity(professorAtualizado);
    }

    @Transactional
    public void deletar(Long id ){
        Professor professor = buscarEntidadePorId(id);
        repository.delete(professor);
    }


    private Professor buscarEntidadePorId(Long id){
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Professor nao encontrado com o id: " + id));
    }
}
