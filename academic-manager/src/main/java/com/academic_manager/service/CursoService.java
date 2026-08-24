package com.academic_manager.service;

import com.academic_manager.entity.Curso;
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
    public Curso criar(Curso curso){
        if (repository.existsByNome(curso.getNome())){
            throw new IllegalArgumentException("Ja existe curso com o nome: " + curso.getNome());
        }
        return repository.save(curso);

    }

    @Transactional(readOnly = true)
    public List<Curso> listar(){
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Curso buscarPorId(Long id){
        return buscarEntidadePorId(id);
    }

    @Transactional
    public Curso atualizar(Long id , Curso cursoAtualizado){
        Curso curso = buscarEntidadePorId(id);

        if (repository.existsByNomeAndIdNot(cursoAtualizado.getNome() , id)){
            throw new IllegalArgumentException("Já existe curso com o nome: " + cursoAtualizado.getNome());
        }

        curso.setNome(cursoAtualizado.getNome());
        curso.setDescricao(cursoAtualizado.getDescricao());
        return curso;
    }

    @Transactional
    public void deletar(Long id){
        Curso curso = buscarEntidadePorId(id);

        repository.delete(curso);
    }



    private Curso buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso nao encontrado pelo id: " + id));
    }
}
