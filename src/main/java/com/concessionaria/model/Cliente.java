package com.concessionaria.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(example = "João da Silva")
    private String nome;

    @Column(nullable = false, unique = true, length = 11)
    @Schema(example = "12345678901")
    private String cpf;

    @Column(nullable = false)
    @Schema(example = "11999999999")
    private String telefone;

    @Column(nullable = false)
    @Schema(example = "joao@email.com")
    private String email;
}