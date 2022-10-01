package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.entity.Screening;
import com.markvarga21.filmadministrator.util.ScreeningDateTimeConverter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScreeningMapper {
    private final ScreeningDateTimeConverter converter;

    public ScreeningDTO mapScreeningToDto(Screening screening) {
        return new ScreeningDTO(
                screening.getMovieName(),
                screening.getRoomName(),
                this.converter.convertScreeningDateToString(screening.getTimeOfScreening())
        );
    }

    public Screening mapScreeningDtoToEntity(ScreeningDTO screeningDTO) {
        return new Screening(
                screeningDTO.getMovieName(),
                screeningDTO.getRoomName(),
                this.converter.convertScreeningTimeString(screeningDTO.getTimeOfScreening())
        );
    }
}
