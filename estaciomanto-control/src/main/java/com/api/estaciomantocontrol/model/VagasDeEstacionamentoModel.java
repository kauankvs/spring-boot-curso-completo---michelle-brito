package com.api.estaciomantocontrol.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "VAGA_ESTACIONAMENTO")
public class VagasDeEstacionamentoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String vagaNúmero;

    @Column(nullable = false, unique = true, length = 7)
    private String carroPlaca;

    @Column(nullable = false, length = 70)
    private String carroFabricante;

    @Column(nullable = false, length = 70)
    private String carroModelo;

    @Column(nullable = false, length = 70)
    private String carroCor;

    @Column(nullable = false)
    private LocalDateTime registroData;

    @Column(nullable = false, length = 130)
    private String responsavelNome;

    @Column(nullable = false, length = 30)
    private String apartamento;

    @Column(nullable = false, length = 30)
    private String bloco;

}

