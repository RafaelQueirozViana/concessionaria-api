package com.concessionaria.dto;

import com.concessionaria.model.TipoCarro;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CarroRequestDTO(

        @NotBlank(message = "informe o modelo do carro")
        @Schema(example = "Corolla")
        String modelo,

        @NotBlank(message = "informe a marca do carro")
        @Schema(example = "Toyota")
        String marca,

        @NotNull(message = "informe o ano de fabricação")
        @Min(value = 1950, message = "ano de fabricação inválido")
        @Max(value = 2026, message = "ano de fabricação não pode ser no futuro")
        @Schema(example = "2023")
        Integer anoFabricacao,

        @NotNull(message = "informe o ano do modelo")
        @Min(value = 1950, message = "ano do modelo inválido")
        @Max(value = 2027, message = "ano do modelo não pode passar de 2027")
        @Schema(example = "2024")
        Integer anoModelo,

        @NotBlank(message = "informe a cor do carro")
        @Schema(example = "Prata")
        String cor,

        @Schema(example = "ABC1D23")
        String placa,

        @NotBlank(message = "informe o chassi do carro")
        @Size(min = 17, max = 17, message = "chassi deve ter 17 caracteres")
        @Schema(example = "9BWZZZ377VT004251")
        String chassi,

        @NotNull(message = "informe a quilometragem")
        @PositiveOrZero(message = "quilometragem não pode ser negativa")
        @Schema(example = "0")
        Integer quilometragem,

        @NotNull(message = "informe o preço")
        @Positive(message = "preço deve ser maior que zero")
        @Schema(example = "145000.00")
        BigDecimal preco,

        @NotNull(message = "informe se o carro é novo ou seminovo")
        TipoCarro tipo
) {}