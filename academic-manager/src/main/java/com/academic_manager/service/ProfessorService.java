package com.academic_manager.service;

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
    public Professor cadastrar(Professor professor){
        if (repository.existsByEmail(professor.getEmail())){
            throw new AlreadyExistsException("Professor ja cadastrado com o email: " + professor.getEmail());
        }

        return repository.save(professor);
    }

    //READ
    @Transactional(readOnly = true)
    public List<Professor> listar(){
        return repository.findAll();
    }

    //FIND BY ID
    @Transactional(readOnly = true)
    public Professor buscarPorId(Long id){
        return buscarEntidadePorId(id);
    }

    //Atualizar
    @Transactional
    public Professor atualizar(Long id , Professor professorAtualizado){
        Professor professor = buscarEntidadePorId(id);
        if (repository.existsByEmailAndIdNot(professorAtualizado.getEmail() , id)){
            throw new AlreadyExistsException("Ja existe email com esse nome: "+ professorAtualizado.getEmail());
        }

        professor.setNome(professorAtualizado.getNome());
        professor.setEmail(professorAtualizado.getEmail());
        professor.setEspecialidade(professorAtualizado.getEspecialidade());

        return professor;
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
