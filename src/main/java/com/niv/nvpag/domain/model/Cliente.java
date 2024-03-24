package com.niv.nvpag.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
