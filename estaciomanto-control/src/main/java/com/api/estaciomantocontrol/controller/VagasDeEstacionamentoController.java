package com.api.estaciomantocontrol.controller;

import com.api.estaciomantocontrol.DTO.VagasDeEstacionamentoDTO;
import com.api.estaciomantocontrol.model.VagasDeEstacionamentoModel;
import com.api.estaciomantocontrol.service.VagasDeEstacionamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vagas-estacionamento")
public class VagasDeEstacionamentoController {

    final
    VagasDeEstacionamentoService vagasDeEstacionamentoService;

    public VagasDeEstacionamentoController(VagasDeEstacionamentoService vagasDeEstacionamentoService) {
        this.vagasDeEstacionamentoService = vagasDeEstacionamentoService;
    }

    @PostMapping
    public ResponseEntity<Object> registrarVaga(@RequestBody @Valid VagasDeEstacionamentoDTO vagasDeEstacionamentoDTO) {

        if (vagasDeEstacionamentoService.existsByVagaNúmero(vagasDeEstacionamentoDTO.getVagaNúmero())) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: está vaga do estacionamento já foi alocada a um morador!");
        }

        if (vagasDeEstacionamentoService.existsByCarroPlaca(vagasDeEstacionamentoDTO.getCarroPlaca())) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: está placa já está registrada em uma vaga do estacionamento!");
        }

        if (vagasDeEstacionamentoService.existsByApartamentoAndAndBloco(vagasDeEstacionamentoDTO.getApartamento(), vagasDeEstacionamentoDTO.getBloco())) {
            ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: está residência já possui uma vaga do estacionamento!");
        }

        var vagasDeEstacionamentoModel = new VagasDeEstacionamentoModel();
        BeanUtils.copyProperties(vagasDeEstacionamentoDTO, vagasDeEstacionamentoModel);
        vagasDeEstacionamentoModel.setRegistroData(LocalDateTime.now(ZoneId.of("UTC")));

        return ResponseEntity.status(HttpStatus.CREATED).body(vagasDeEstacionamentoService.salvar(vagasDeEstacionamentoModel));
    }

    @GetMapping("/todas")
    public ResponseEntity<List<VagasDeEstacionamentoModel>> acharTodos() {
        return ResponseEntity.status(HttpStatus.OK).body(vagasDeEstacionamentoService.findAll());
    }

    @GetMapping("/todos")
    public ResponseEntity<List<VagasDeEstacionamentoModel>> acharTodos2() {
        return ResponseEntity.status(HttpStatus.OK).body(vagasDeEstacionamentoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> acharPeloId(@PathVariable(value = "id") UUID id) {

        Optional<VagasDeEstacionamentoModel> vagasDeEstacionamentoModelOptional = vagasDeEstacionamentoService.findById(id);

        if(!vagasDeEstacionamentoModelOptional.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse id não foi encontrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(vagasDeEstacionamentoService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarVagaDeEstacionamento(@PathVariable(value = "id") UUID id) {

        Optional<VagasDeEstacionamentoModel> vagasDeEstacionamentoModelOptional = vagasDeEstacionamentoService.findById(id);

        if (!vagasDeEstacionamentoModelOptional.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse id não foi encontrado!");
        }

        vagasDeEstacionamentoService.delete(vagasDeEstacionamentoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("A vaga foi deletada com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> uptadeVagaDeEstacionamento(@PathVariable(value = "id") UUID id,
                                                             @RequestBody @Valid VagasDeEstacionamentoDTO vagasDeEstacionamentoDTO) {
        Optional<VagasDeEstacionamentoModel> vagasDeEstacionamentoModelOptional = vagasDeEstacionamentoService.findById(id);

        if (!vagasDeEstacionamentoModelOptional.isPresent()) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Esse id não foi encontrado!");
        }
        var vagasDeEstacionamentoModel = new VagasDeEstacionamentoModel();
        BeanUtils.copyProperties(vagasDeEstacionamentoDTO, vagasDeEstacionamentoModel);
        vagasDeEstacionamentoModel.setId(vagasDeEstacionamentoModelOptional.get().getId());
        vagasDeEstacionamentoModel.setRegistroData(vagasDeEstacionamentoModelOptional.get().getRegistroData());

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(vagasDeEstacionamentoService.salvar(vagasDeEstacionamentoModel));
    }

}
