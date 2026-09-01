package com.academic_manager.controller;

import com.academic_manager.dto.ProfessorRequestDTO;
import com.academic_manager.dto.ProfessorResponseDTO;
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
    public ResponseEntity<ProfessorResponseDTO> cadastrarProfessor(@RequestBody ProfessorRequestDTO request){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(request));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorResponseDTO>> listarProfessores(){
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarProfessorPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizarProfessor(@PathVariable Long id,
                                                        @RequestBody ProfessorRequestDTO request){
        return ResponseEntity.ok(service.atualizar(id , request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProfessor(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
