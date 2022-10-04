package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.BookingDTO;
import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.dto.SeatDTO;
import com.markvarga21.filmadministrator.entity.Booking;
import com.markvarga21.filmadministrator.entity.Screening;
import com.markvarga21.filmadministrator.entity.Seat;
import com.markvarga21.filmadministrator.mapping.BookingMapper;
import com.markvarga21.filmadministrator.mapping.ScreeningMapper;
import com.markvarga21.filmadministrator.mapping.SeatMapper;
import com.markvarga21.filmadministrator.repository.BookingRepository;
import com.markvarga21.filmadministrator.repository.SeatRepository;
import com.markvarga21.filmadministrator.util.BookingValidator;
import com.markvarga21.filmadministrator.util.ScreeningDateTimeConverter;
import com.markvarga21.filmadministrator.util.SeatConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {
    private final SeatConverter seatConverter;
    private final SeatMapper seatMapper;
    private final BookingRepository bookingRepository;
    private final SeatRepository seatRepository;
    private final ScreeningDateTimeConverter dateTimeConverter;
    private final BookingMapper bookingMapper;
    private final ScreeningMapper screeningMapper;
    private final BookingValidator bookingValidator;

    public String saveBookings(String userName, String movieTitle, String roomName, String timeOfScreening, String seatsToBook) {
        List<SeatDTO> seats = this.seatConverter.convertSeatStringToList(seatsToBook, roomName);
        List<BookingDTO> allBookings = this.getAllBookings();
        List<Booking> bookingsToSave = new ArrayList<>();
        String seatsString = String.join(", ", seats.stream().map(SeatDTO::toString).toList());
        for (SeatDTO seatDTO : seats) {
            Seat seat = this.seatMapper.convertSeatDtoToEntity(seatDTO);
            if (!this.bookingValidator.isValidSeatForRoom(roomName, seatDTO)) {
                return String.format("Seat %s does not exist in this room", seatDTO);
            }
            if (!this.bookingValidator.isSeatFree(allBookings, seatDTO)) {
                return String.format("Seat %s is already taken", seat);
            }
            this.seatRepository.save(seat);
            LocalDateTime screeningTime = this.dateTimeConverter.convertScreeningTimeString(timeOfScreening);
            Booking bookingEntity = Booking.builder()
                    .bookedSeat(seat)
                    .userName(userName)
                    .screening(new Screening(movieTitle, roomName, screeningTime))
                    .build();
            bookingsToSave.add(bookingEntity);
        }
        this.bookingRepository.saveAll(bookingsToSave);
        // TODO booking prices
        return String.format("Seats booked: %s; the price for this booking is %d HUF", seatsString, 1500);
    }

    public List<BookingDTO> getAllBookings() {
        return this.bookingRepository
                .findAll()
                .stream()
                .map(this.bookingMapper::convertBookingEntityToDto)
                .toList();
    }

    public String getBookingsForUser(String userName) {
        var bookingEntitiesOptional = this.bookingRepository.getBookingsByUserName(userName);
        if (bookingEntitiesOptional.get().isEmpty()) {
            return "You have not booked any tickets yet";
        }
        var bookingEntities = bookingEntitiesOptional.get();
        var bookingDtoList = bookingEntities.stream()
                .map(this.bookingMapper::convertBookingEntityToDto)
                .toList();
        List<ScreeningDTO> bookedScreenings = bookingDtoList.stream().map(BookingDTO::getScreeningDTO).distinct().toList();

        return this.formatBookingListForScreenings(bookedScreenings);
    }

    private String formatBookingListForScreenings(List<ScreeningDTO> screenings) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Your previous bookings are").append("\n");
        for (int i = 0; i < screenings.size(); i++) {
            var screeningEntity = this.screeningMapper.mapScreeningDtoToEntity(screenings.get(i));
            var bookingsForScreening = this.bookingRepository.getBookingsByScreening(screeningEntity).get();
            var bookedSeats = bookingsForScreening.stream()
                    .map(this.bookingMapper::convertBookingEntityToDto)
                    .map(BookingDTO::getBookedSeat)
                    .map(SeatDTO::toString)
                    .toList();
            var bookedSeatsString = String.join(", ", bookedSeats);
            String bookRecord = String.format("Seats %s on %s in room %s starting at %s for %d HUF",
                    bookedSeatsString,
                    screenings.get(i).getMovieName(),
                    screenings.get(i).getRoomName(),
                    screenings.get(i).getTimeOfScreening(),
                    1300
                    );
            stringBuilder.append(bookRecord);
            if (i < screenings.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
