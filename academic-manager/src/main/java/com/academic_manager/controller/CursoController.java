package com.academic_manager.controller;

import com.academic_manager.entity.Curso;
import com.academic_manager.service.CursoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@RequiredArgsConstructor
public class CursoController {

    private final CursoService service;

    @PostMapping
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody Curso curso){
        Curso response = service.criar(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<Curso>> listarCursos(){
        List<Curso> response = service.listar();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> buscarCursoPorId(@PathVariable Long id){
        Curso response = service.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> atualizarCurso(@PathVariable Long id,
                                                @RequestBody Curso curso){
        Curso response = service.atualizar(id , curso);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}