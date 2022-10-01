package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.service.MovieService;
import com.markvarga21.filmadministrator.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScreeningValidator {
    private final MovieService movieService;
    private final RoomService roomService;

    public boolean isValidReservation(ScreeningDTO screeningDTO) {
        String movieName = screeningDTO.getMovieName();
        String roomName = screeningDTO.getRoomName();
        String date = screeningDTO.getTimeOfScreening();
        return this.isValidMovie(movieName)
                && this.isValidRoom(roomName)
                && this.isValidScreenDateTime(date);
    }

    private boolean isValidRoom(String roomName) {
        return this.roomService.roomExists(roomName);
    }

    private boolean isValidMovie(String movieName) {
        return this.movieService.movieExists(movieName);
    }

    private boolean isValidScreenDateTime(String dateTime) {
        return true;
    }
}
