package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.dto.BookingDTO;
import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.dto.SeatDTO;
import com.markvarga21.filmadministrator.entity.Screening;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingFormatter {
    private final SeatConverter seatConverter;

    public String formatBookings(List<BookingDTO> bookings) {
        List<ScreeningDTO> bookedScreenings = bookings.stream().map(BookingDTO::getScreeningDTO).distinct().toList();

//        List<String> bookingSeats = bookings.stream().map(BookingDTO::getBookedSeat).map(SeatDTO::toString).toList();
//        String bookingComponent = String.join(", ", bookingSeats);

        return bookedScreenings.toString();
    }
}
