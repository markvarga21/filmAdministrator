package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.BasePriceDTO;
import com.markvarga21.filmadministrator.entity.BasePrice;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BasePriceMapper {
    private final ModelMapper modelMapper;

    public BasePrice convertBasePriceDtoToEntity(BasePriceDTO basePriceDTO) {
        return this.modelMapper.map(basePriceDTO, BasePrice.class);
    }

    public BasePriceDTO convertBasePriceEntityToDto(BasePrice basePrice) {
        return this.modelMapper.map(basePrice, BasePriceDTO.class);
    }
}
