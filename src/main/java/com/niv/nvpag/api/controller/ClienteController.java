package com.niv.nvpag.api.controller;

import com.niv.nvpag.domain.model.Cliente;
import com.niv.nvpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    //    @Autowired
    private final ClienteRepository clienteRepository;

    @GetMapping
    public List<Cliente> list() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{clienteId}")
    public ResponseEntity<Cliente> findCliente(@PathVariable Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);

        if(cliente.isPresent()) return ResponseEntity.ok(cliente.get());

        return ResponseEntity.notFound().build();
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente create(@RequestBody Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> update(@PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if(!clienteRepository.existsById(clienteId)) return ResponseEntity.notFound().build();

       cliente.setId(clienteId);
       cliente = clienteRepository.save(cliente);

       return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable Long clienteId) {
        if(!clienteRepository.existsById(clienteId)) return ResponseEntity.notFound().build();
        clienteRepository.deleteById(clienteId);

        return ResponseEntity.noContent().build();
    }
}
