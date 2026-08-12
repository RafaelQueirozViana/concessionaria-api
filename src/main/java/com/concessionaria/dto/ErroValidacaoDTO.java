package com.concessionaria.dto;

import java.util.List;

public record ErroValidacaoDTO(int status, List<ErroCampoDTO> erros) {}