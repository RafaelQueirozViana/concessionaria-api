package com.concessionaria.repository;

import com.concessionaria.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarroRepository extends JpaRepository<Carro, Long> {
    boolean existsByChassi(String chassi);
    boolean existsByPlaca(String placa);
    List<Carro> findByCorIgnoreCase(String cor);
    List<Carro> findByAnoModelo(Integer anoModelo);
    List<Carro> findByCorIgnoreCaseOrAnoModelo(String cor, Integer anoModelo);
}