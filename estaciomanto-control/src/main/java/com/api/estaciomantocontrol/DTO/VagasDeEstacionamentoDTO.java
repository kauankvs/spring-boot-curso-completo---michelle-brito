package com.api.estaciomantocontrol.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class VagasDeEstacionamentoDTO {

    @NotBlank
    private String vagaNúmero;

    @NotBlank
    @Size(max = 7)
    private String carroPlaca;

    @NotBlank
    private String carroFabricante;

    @NotBlank
    private String carroModelo;

    @NotBlank
    private String carroCor;

    @NotBlank
    private String responsavelNome;

    @NotBlank
    private String apartamento;

    @NotBlank
    private String bloco;

}
