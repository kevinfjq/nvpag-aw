package com.niv.nvpag.api.assembler;

import com.niv.nvpag.api.model.ParcelamentoModel;
import com.niv.nvpag.api.model.input.ParcelamentoInput;
import com.niv.nvpag.domain.model.Parcelamento;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ParcelamentoAssembler {
    private final ModelMapper modelMapper;

    public ParcelamentoModel toModel(Parcelamento parcelamento) {
        return modelMapper.map(parcelamento, ParcelamentoModel.class);
    }

    public List<ParcelamentoModel> toModelList(List<Parcelamento> parcelamentoList) {
        return parcelamentoList.stream()
                .map(this::toModel).toList();
    }

    public Parcelamento toEntity(ParcelamentoInput parcelamentoInput) {
        return modelMapper.map(parcelamentoInput, Parcelamento.class);
    }
}
