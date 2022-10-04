package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.SeatDTO;
import com.markvarga21.filmadministrator.entity.Seat;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SeatMapper {
    private final ModelMapper modelMapper;

    public Seat convertSeatDtoToEntity(SeatDTO seatDTO) {
        return new Seat(seatDTO.getRoomName(), seatDTO.getSeatRow(), seatDTO.getSeatColumn());
    }

    public SeatDTO convertSeatEntityToDto(Seat seat) {
        // TODO might not be working
        return this.modelMapper.map(seat, SeatDTO.class);
    }
}
