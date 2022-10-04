package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.BookingDTO;
import com.markvarga21.filmadministrator.dto.SeatDTO;
import com.markvarga21.filmadministrator.entity.Booking;
import com.markvarga21.filmadministrator.entity.Seat;
import com.markvarga21.filmadministrator.mapping.SeatMapper;
import com.markvarga21.filmadministrator.repository.BookingRepository;
import com.markvarga21.filmadministrator.repository.SeatRepository;
import com.markvarga21.filmadministrator.util.ScreeningDateTimeConverter;
import com.markvarga21.filmadministrator.util.SeatConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BookingService {
    Logger log = Logger.getLogger(BookingService.class.getName());
    private final SeatConverter seatConverter;
    private final SeatMapper seatMapper;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ScreeningDateTimeConverter dateTimeConverter;
    public String saveBookings(String userName, String movieTitle, String roomName, String timeOfScreening, String seatsToBook) {
        List<SeatDTO> seats = this.seatConverter.convertSeatStringToList(seatsToBook, roomName);
        List<Booking> bookingsToSave = new ArrayList<>();
        String seatsString = String.join(", ", seats.stream().map(SeatDTO::toString).toList());
        for (SeatDTO seatDTO : seats) {
            Seat seat = this.seatMapper.convertSeatDtoToEntity(seatDTO);
            this.seatRepository.save(seat);
            LocalDateTime screeningTime = this.dateTimeConverter.convertScreeningTimeString(timeOfScreening);
            Booking bookingEntity = Booking.builder()
                    .bookedSeat(seat)
                    .userName(userName)
                    .movieName(movieTitle)
                    .roomName(roomName)
                    .timeOfScreening(screeningTime)
                    .build();
            bookingsToSave.add(bookingEntity);
        }
        this.bookingRepository.saveAll(bookingsToSave);
        // TODO booking prices
        return String.format("Seats booked: %s; the price for this booking is %d HUF", seatsString, 1500);
    }
}
