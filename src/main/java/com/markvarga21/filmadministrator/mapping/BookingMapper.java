package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.BookingDTO;
import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.entity.Booking;
import com.markvarga21.filmadministrator.util.ScreeningDateTimeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingMapper {
    private final ScreeningMapper screeningMapper;
    private final SeatMapper seatMapper;

    public BookingDTO convertBookingEntityToDto(Booking booking) {
        return BookingDTO.builder()
                .userName(booking.getUserName())
                .screeningDTO(this.screeningMapper.mapScreeningToDto(booking.getScreening()))
                .bookedSeat(this.seatMapper.convertSeatEntityToDto(booking.getBookedSeat()))
                .build();
    }
}
