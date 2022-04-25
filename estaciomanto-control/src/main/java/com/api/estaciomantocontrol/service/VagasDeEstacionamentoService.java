package com.api.estaciomantocontrol.service;

import com.api.estaciomantocontrol.model.VagasDeEstacionamentoModel;
import com.api.estaciomantocontrol.repository.VagasDeEstacionamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VagasDeEstacionamentoService {

    final
    VagasDeEstacionamentoRepository vagasDeEstacionamentoRepository;

    public VagasDeEstacionamentoService(VagasDeEstacionamentoRepository vagasDeEstacionamentoRepository) {
        this.vagasDeEstacionamentoRepository = vagasDeEstacionamentoRepository;
    }

    @Transactional
    public VagasDeEstacionamentoModel salvar(VagasDeEstacionamentoModel vagasDeEstacionamentoModel) {
        return vagasDeEstacionamentoRepository.save(vagasDeEstacionamentoModel);
    }

    public boolean existsByCarroPlaca(String carroPlaca) {
        return vagasDeEstacionamentoRepository.existsByCarroPlaca(carroPlaca);
    }

    public boolean existsByVagaNúmero(String vagaNúmero) {
        return vagasDeEstacionamentoRepository.existsByVagaNúmero(vagaNúmero);
    }

    public boolean existsByApartamentoAndAndBloco(String apartamento, String bloco) {
        return vagasDeEstacionamentoRepository.existsByApartamentoAndAndBloco(apartamento, bloco);
    }
    public List<VagasDeEstacionamentoModel> findAll(){
        return vagasDeEstacionamentoRepository.findAll();
    }

    public Optional<VagasDeEstacionamentoModel> findById(UUID id) {
        return vagasDeEstacionamentoRepository.findById(id);
    }

    @Transactional
    public void delete(VagasDeEstacionamentoModel vagasDeEstacionamentoModel) {
        vagasDeEstacionamentoRepository.delete(vagasDeEstacionamentoModel);
    }
}


