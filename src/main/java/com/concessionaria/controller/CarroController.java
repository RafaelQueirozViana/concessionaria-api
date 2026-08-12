package com.concessionaria.controller;

import com.concessionaria.dto.CarroRequestDTO;
import com.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Carros", description = "Consulta e cadastro do estoque de veículos")
@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroService carroService;

    public CarroController(CarroService carroService) {
        this.carroService = carroService;
    }

    @Operation(summary = "Cadastra um novo carro", description = "Registra um carro no estoque, sempre com status disponível.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Carro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos no corpo da requisição"),
            @ApiResponse(responseCode = "409", description = "Já existe um carro com essa placa ou chassi")
    })
    @PostMapping
    public ResponseEntity<CarroResponseDTO> cadastrar(@Valid @RequestBody CarroRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carroService.cadastrar(dto));
    }

    @Operation(summary = "Lista os carros do estoque", description = "Retorna todos os carros. Pode filtrar por cor ou ano do modelo através dos query params.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetro de filtro inválido")
    })
    @GetMapping
    public List<CarroResponseDTO> listar(
            @RequestParam(required = false) String cor,
            @RequestParam(required = false) Integer anoModelo) {
        return carroService.listar(cor, anoModelo);
    }

    @Operation(summary = "Busca um carro pelo id", description = "Retorna os dados de um carro específico do estoque.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carro encontrado"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado")
    })
    @GetMapping("/{id}")
    public CarroResponseDTO buscarPorId(@PathVariable Long id) {
        return carroService.buscarPorId(id);
    }

    @Operation(summary = "Remove um carro do estoque", description = "Exclui definitivamente um carro cadastrado.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Carro removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        carroService.remover(id);
        return ResponseEntity.noContent().build();
    }
}