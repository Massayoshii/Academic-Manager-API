package com.academic_manager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "tb_alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "email" , unique = true , nullable = false)
    private String email;

    @Column(name = "cpf" , unique = true , nullable = false)
    private String cpf;

    @Column(name = "data_nascimento" , nullable = false)
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "curso_id" , nullable = false)
    private Curso curso;
}
