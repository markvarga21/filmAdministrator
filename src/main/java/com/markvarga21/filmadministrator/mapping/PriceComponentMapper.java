package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.PriceComponentDTO;
import com.markvarga21.filmadministrator.entity.PriceComponent;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceComponentMapper {
    private final ModelMapper modelMapper;

    public PriceComponent convertPriceComponentDtoToEntity(PriceComponentDTO priceComponentDTO) {
        return this.modelMapper.map(priceComponentDTO, PriceComponent.class);
    }

    public PriceComponentDTO convertPriceComponentEntityToDto(PriceComponent priceComponent) {
        return this.modelMapper.map(priceComponent, PriceComponentDTO.class);
    }
}
