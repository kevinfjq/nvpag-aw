package com.niv.nvpag.api.controller;

import com.niv.nvpag.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ClienteController {
    @GetMapping("/clientes")
    public List<Cliente> list() {

        return Arrays.asList(new Cliente(), new Cliente());
    }
}
