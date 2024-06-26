package com.niv.nvpag.api.model.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ParcelamentoInput {
    @NotBlank
    @Size(max = 20)
    private String descricao;
    @NotNull
    @Positive
    private BigDecimal valorTotal;
    @Max(12)
    @NotNull
    @Positive
    private Integer quantidadeParcelas;
    @Valid
    @NotNull
    private ClienteIdInput cliente;
}
