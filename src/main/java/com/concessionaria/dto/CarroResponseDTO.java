package com.concessionaria.dto;

import com.concessionaria.model.StatusCarro;
import com.concessionaria.model.TipoCarro;

import java.math.BigDecimal;

public record CarroResponseDTO(
        Long id,
        String modelo,
        String marca,
        Integer anoFabricacao,
        Integer anoModelo,
        String cor,
        String placa,
        String chassi,
        Integer quilometragem,
        BigDecimal preco,
        TipoCarro tipo,
        StatusCarro status
) {}