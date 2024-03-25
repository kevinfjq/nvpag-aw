package com.niv.nvpag.domain.services;

import com.niv.nvpag.domain.exception.BusinessException;
import com.niv.nvpag.domain.model.Cliente;
import com.niv.nvpag.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistryClienteService {
    private final ClienteRepository clienteRepository;

    public Cliente find(Long clienteId) {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
    }
    @Transactional
    public Cliente save(Cliente cliente) {
        boolean isEmailUsed = clienteRepository.findByEmail(cliente.getEmail())
                .filter(c -> !c.equals(cliente))
                .isPresent();
        if(isEmailUsed) throw new BusinessException("Email already in use");
        return  clienteRepository.save(cliente);
    }

    @Transactional
    public void delete(Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
