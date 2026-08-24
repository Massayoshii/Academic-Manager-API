package com.academic_manager.service;

import com.academic_manager.entity.Aluno;
import com.academic_manager.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlunoService {

    private final AlunoRepository repository;

    @Transactional
    public Aluno cadastrar(Aluno aluno) {
        if (repository.existsByEmail(aluno.getEmail())) {
            throw new IllegalArgumentException("Aluno com email ou cpf ja cadastrado");
        }

        if (repository.existsByCpf(aluno.getCpf())){
            throw new IllegalArgumentException("Aluno com CPF ja cadastrado");
        }

        return repository.save(aluno);
    }

    @Transactional(readOnly = true)
    public List<Aluno> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Aluno buscarPorId(Long id){
        return buscarEntidadePorId(id);
    }

    @Transactional
    public Aluno atualizar(Long id , Aluno alunoAtualizado){
        Aluno aluno = buscarEntidadePorId(id);

        if (repository.existsByEmailAndIdNot(alunoAtualizado.getEmail() , id)){
            throw new IllegalArgumentException("Aluno ja cadastrado com email: " + alunoAtualizado.getEmail());
        }
        if (repository.existsByCpfAndIdNot(alunoAtualizado.getCpf() , id)){
            throw new IllegalArgumentException("CPF ja cadastraddo com CPF : " + alunoAtualizado.getCpf());
        }

        aluno.setNome(alunoAtualizado.getNome());
        aluno.setEmail(alunoAtualizado.getEmail());
        aluno.setCpf(alunoAtualizado.getCpf());

        return aluno;
    }

    @Transactional
    public void deletar(Long id){
        Aluno aluno = buscarEntidadePorId(id);
        repository.delete(aluno);
    }



    private Aluno buscarEntidadePorId(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno nao encontrado pelo id: " + id));
    }
}
