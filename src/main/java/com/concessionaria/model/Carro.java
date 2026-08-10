package com.concessionaria.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "carro")
@Getter
@Setter
@NoArgsConstructor
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(example = "Corolla")
    private String modelo;

    @Column(nullable = false)
    @Schema(example = "Toyota")
    private String marca;

    @Column(name = "ano_fabricacao", nullable = false)
    @Schema(example = "2023")
    private Integer anoFabricacao;

    @Column(name = "ano_modelo", nullable = false)
    @Schema(example = "2024")
    private Integer anoModelo;

    @Column(nullable = false)
    @Schema(example = "Prata")
    private String cor;

    @Column(unique = true)
    @Schema(example = "ABC1D23")
    private String placa;

    @Column(nullable = false, unique = true)
    @Schema(example = "9BWZZZ377VT004251")
    private String chassi;

    @Column(nullable = false)
    @Schema(example = "0")
    private Integer quilometragem;

    @Column(nullable = false, precision = 12, scale = 2)
    @Schema(example = "145000.00")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoCarro tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCarro status;
}