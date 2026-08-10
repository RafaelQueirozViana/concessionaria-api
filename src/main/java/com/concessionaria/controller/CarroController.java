package com.concessionaria.controller;

import com.concessionaria.model.Carro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private final CarroRepository carroRepository;

    public CarroController(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    @PostMapping
    public Carro cadastrar(@RequestBody Carro carro) {
        return carroRepository.save(carro);
    }

    @GetMapping
    public List<Carro> listar() {
        return carroRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carro> buscarPorId(@PathVariable Long id) {
        return carroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if (!carroRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        carroRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}