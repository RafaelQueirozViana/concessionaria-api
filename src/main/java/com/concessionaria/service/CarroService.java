package com.concessionaria.service;

import com.concessionaria.dto.CarroRequestDTO;
import com.concessionaria.dto.CarroResponseDTO;
import com.concessionaria.exception.RecursoNaoEncontradoException;
import com.concessionaria.exception.RegistroDuplicadoException;
import com.concessionaria.model.Carro;
import com.concessionaria.model.StatusCarro;
import com.concessionaria.repository.CarroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarroService {

    private final CarroRepository carroRepository;

    public CarroService(CarroRepository carroRepository) {
        this.carroRepository = carroRepository;
    }

    public CarroResponseDTO cadastrar(CarroRequestDTO dto) {
        if (dto.placa() != null && carroRepository.existsByPlaca(dto.placa())) {
            throw new RegistroDuplicadoException("já existe um carro cadastrado com essa placa");
        }
        if (carroRepository.existsByChassi(dto.chassi())) {
            throw new RegistroDuplicadoException("já existe um carro cadastrado com esse chassi");
        }

        Carro carro = new Carro();
        carro.setModelo(dto.modelo());
        carro.setMarca(dto.marca());
        carro.setAnoFabricacao(dto.anoFabricacao());
        carro.setAnoModelo(dto.anoModelo());
        carro.setCor(dto.cor());
        carro.setPlaca(dto.placa());
        carro.setChassi(dto.chassi());
        carro.setQuilometragem(dto.quilometragem());
        carro.setPreco(dto.preco());
        carro.setTipo(dto.tipo());
        carro.setStatus(StatusCarro.DISPONIVEL);

        return toResponseDTO(carroRepository.save(carro));
    }

    public List<CarroResponseDTO> listar(String cor, Integer anoModelo) {
        List<Carro> carros;

        if (cor != null && anoModelo != null) {
            carros = carroRepository.findByCorIgnoreCaseOrAnoModelo(cor, anoModelo);
        } else if (cor != null) {
            carros = carroRepository.findByCorIgnoreCase(cor);
        } else if (anoModelo != null) {
            carros = carroRepository.findByAnoModelo(anoModelo);
        } else {
            carros = carroRepository.findAll();
        }

        return carros.stream().map(this::toResponseDTO).toList();
    }

    public CarroResponseDTO buscarPorId(Long id) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("carro não encontrado"));
        return toResponseDTO(carro);
    }

    public void remover(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new RecursoNaoEncontradoException("carro não encontrado");
        }
        carroRepository.deleteById(id);
    }

    private CarroResponseDTO toResponseDTO(Carro carro) {
        return new CarroResponseDTO(
                carro.getId(),
                carro.getModelo(),
                carro.getMarca(),
                carro.getAnoFabricacao(),
                carro.getAnoModelo(),
                carro.getCor(),
                carro.getPlaca(),
                carro.getChassi(),
                carro.getQuilometragem(),
                carro.getPreco(),
                carro.getTipo(),
                carro.getStatus()
        );
    }
}