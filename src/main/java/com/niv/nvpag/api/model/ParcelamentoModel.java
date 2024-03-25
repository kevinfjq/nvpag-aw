package com.niv.nvpag.api.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ParcelamentoModel {
    private Long id;
    private ClienteResumeModel cliente;
    private String descricao;
    private BigDecimal valorTotal;
    private Integer parcelas;
    private OffsetDateTime dataCriacao;

}
