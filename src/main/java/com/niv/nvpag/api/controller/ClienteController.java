package com.niv.nvpag.api.controller;

import com.niv.nvpag.domain.exception.BusinessException;
import com.niv.nvpag.domain.model.Cliente;
import com.niv.nvpag.domain.repository.ClienteRepository;
import com.niv.nvpag.domain.services.RegistryClienteService;
import jakarta.validation.Valid;
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
    private final RegistryClienteService registryClienteService;
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
    public Cliente create(@Valid @RequestBody Cliente cliente) {
        return registryClienteService.save(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> update(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if(!clienteRepository.existsById(clienteId)) return ResponseEntity.notFound().build();

       cliente.setId(clienteId);
       cliente = registryClienteService.save(cliente);

       return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> delete(@PathVariable Long clienteId) {
        if(!clienteRepository.existsById(clienteId)) return ResponseEntity.notFound().build();
        registryClienteService.delete(clienteId);

        return ResponseEntity.noContent().build();
    }
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> capture(BusinessException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
