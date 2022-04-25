package com.api.estaciomantocontrol.repository;

import com.api.estaciomantocontrol.model.VagasDeEstacionamentoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VagasDeEstacionamentoRepository extends JpaRepository<VagasDeEstacionamentoModel, UUID> {

    boolean existsByCarroPlaca(String carroPlaca);
    boolean existsByVagaNúmero(String vagaNúmero);
    boolean existsByApartamentoAndAndBloco(String apartamento, String bloco);

}
