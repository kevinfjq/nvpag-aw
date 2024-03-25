package com.niv.nvpag.api.controller;

import com.niv.nvpag.domain.model.Parcelamento;
import com.niv.nvpag.domain.repository.ParcelamentosRepository;
import com.niv.nvpag.domain.services.ParcelamentoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    @GetMapping
    public List<Parcelamento> list() {
        return  parcelamentosRepository.findAll();
    }

    @GetMapping("{parcelamentoId}")
    public ResponseEntity<Parcelamento> find(@PathVariable Long parcelamentoId) {
        Optional<Parcelamento> parcelamento = parcelamentosRepository.findById(parcelamentoId);
        return parcelamento.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Parcelamento create(@Valid @RequestBody Parcelamento parcelamento) {
        return parcelamentoService.save(parcelamento);
    }

}
