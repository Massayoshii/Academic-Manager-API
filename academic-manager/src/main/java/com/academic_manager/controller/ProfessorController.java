package com.academic_manager.controller;

import com.academic_manager.entity.Professor;
import com.academic_manager.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@RequiredArgsConstructor
public class ProfessorController {

    private final ProfessorService service;

    @PostMapping
    public ResponseEntity<Professor> cadastrarProfessor(@RequestBody Professor professor){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(professor));
    }

    @GetMapping
    public ResponseEntity<List<Professor>> listarProfessores(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarProfessorPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id,
                                                        @RequestBody Professor professor){
        return ResponseEntity.ok(service.atualizar(id , professor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
