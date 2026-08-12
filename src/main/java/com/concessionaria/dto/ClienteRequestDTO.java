package com.concessionaria.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ClienteRequestDTO(

        @NotBlank(message = "informe o nome do cliente")
        @Schema(example = "João da Silva")
        String nome,

        @NotBlank(message = "informe o CPF")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 números, sem letras ou símbolos")
        @Schema(example = "12345678901")
        String cpf,

        @NotBlank(message = "informe o telefone")
        @Schema(example = "11999999999")
        String telefone,

        @NotBlank(message = "informe o e-mail")
        @Email(message = "e-mail inválido")
        @Schema(example = "joao@email.com")
        String email
) {}