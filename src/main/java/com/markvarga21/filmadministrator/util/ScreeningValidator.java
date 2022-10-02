package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.service.MovieService;
import com.markvarga21.filmadministrator.service.RoomService;
import com.markvarga21.filmadministrator.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScreeningValidator {
    private final MovieService movieService;
    private final RoomService roomService;
    private final ScreeningDateTimeConverter converter;

    public boolean isValidReservation(ScreeningDTO screeningDTO) {
        String movieName = screeningDTO.getMovieName();
        String roomName = screeningDTO.getRoomName();
        return this.isValidMovie(movieName)
                && this.isValidRoom(roomName);
    }

    private boolean isValidRoom(String roomName) {
        return this.roomService.roomExists(roomName);
    }

    private boolean isValidMovie(String movieName) {
        return this.movieService.movieExists(movieName);
    }

    public boolean isValidScreenDateTime(String movieName, String dateTime, List<ScreeningDTO> screeningsInTheRoom) {
        var newScreeningStart = this.converter.convertScreeningTimeString(dateTime);
        var newScreeningEnd = this.calculateScreeningEndTime(movieName, newScreeningStart);
        for (var screening : screeningsInTheRoom) {
            var currentlyScreeningStart = this.converter.convertScreeningTimeString(screening.getTimeOfScreening());
            var currentlyScreeningEnd = this.calculateScreeningEndTime(screening.getMovieName(), currentlyScreeningStart);

            if (this.isOverlapping(
                    newScreeningStart,
                    newScreeningEnd,
                    currentlyScreeningStart,
                    currentlyScreeningEnd)) {
                return true;
            }
        }
        return false;
    }

    private LocalDateTime calculateScreeningEndTime(String movieName, LocalDateTime startDateTime) {
        var movieLength = this.movieService.getMovieLengthForTitle(movieName);
        return startDateTime.plusMinutes(movieLength);
    }

    private boolean isOverlapping(LocalDateTime startA, LocalDateTime endA, LocalDateTime startB, LocalDateTime endB) {
        return startA.isBefore(endB) && endA.isAfter(startB);
    }

    public boolean isPausePresent(String movieName, String roomName, String timeOfScreening) {
        return true;
    }
}
