package com.niv.nvpag.domain.services;

import com.niv.nvpag.domain.exception.BusinessException;
import com.niv.nvpag.domain.model.Cliente;
import com.niv.nvpag.domain.model.Parcelamento;
import com.niv.nvpag.domain.repository.ClienteRepository;
import com.niv.nvpag.domain.repository.ParcelamentosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class ParcelamentoService {
    private final ParcelamentosRepository parcelamentosRepository;
    private final RegistryClienteService clienteService;
    @Transactional
    public Parcelamento save(Parcelamento parcelamento) {
        if(parcelamento.getId() != null) {
            throw new BusinessException("Parcelamento a ser criado não deve possuir código de identificação");
        }
        Cliente cliente = clienteService.find(parcelamento.getCliente().getId());

        parcelamento.setCliente(cliente);
        parcelamento.setDataCriacao(LocalDateTime.now());

        return parcelamentosRepository.save(parcelamento);
    }
}
