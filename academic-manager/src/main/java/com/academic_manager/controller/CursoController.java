package com.academic_manager.controller;

import com.academic_manager.dto.CursoRequestDTO;
import com.academic_manager.dto.CursoResponseDTO;
import com.academic_manager.entity.Curso;
import com.academic_manager.service.CursoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CursoResponseDTO> cadastrarCurso(@RequestBody @Valid CursoRequestDTO request){
        CursoResponseDTO response = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<CursoResponseDTO>> listarCursos(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> buscarCursoPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CursoResponseDTO> atualizarCurso(@PathVariable Long id,
                                                @RequestBody @Valid CursoRequestDTO request){

        return ResponseEntity.ok(service.atualizar(id , request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCurso(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}