package com.concessionaria.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String telefone,
        String email
) {}