package com.niv.nvpag.api.controller;

import com.niv.nvpag.api.assembler.ParcelamentoAssembler;
import com.niv.nvpag.api.model.ParcelamentoModel;
import com.niv.nvpag.api.model.input.ParcelamentoInput;
import com.niv.nvpag.domain.model.Parcelamento;
import com.niv.nvpag.domain.repository.ParcelamentosRepository;
import com.niv.nvpag.domain.services.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/parcelamentos")
public class ParcelamentoController {
    private final ParcelamentosRepository parcelamentosRepository;
    private final ParcelamentoService parcelamentoService;
    private final ParcelamentoAssembler parcelamentoAssembler;
    @GetMapping
    public List<ParcelamentoModel> list() {
        return  parcelamentoAssembler.toModelList(parcelamentosRepository.findAll());
    }

    @GetMapping("{parcelamentoId}")
    public ResponseEntity<ParcelamentoModel> find(@PathVariable Long parcelamentoId) {
        return parcelamentosRepository.findById(parcelamentoId)
                .map(parcelamentoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ParcelamentoModel create(@Valid @RequestBody ParcelamentoInput parcelamentoInput) {
        Parcelamento mappedParcelamento = parcelamentoAssembler.toEntity(parcelamentoInput);
        Parcelamento createdParcelamento = parcelamentoService.save(mappedParcelamento);
        return parcelamentoAssembler.toModel(createdParcelamento);
    }

}
