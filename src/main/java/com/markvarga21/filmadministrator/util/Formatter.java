package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.dto.MovieDTO;
import com.markvarga21.filmadministrator.dto.RoomDTO;
import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.entity.Movie;
import com.markvarga21.filmadministrator.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Formatter {
    private final MovieService movieService;
    public String formatMovieList(List<MovieDTO> movies) {
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }
        return movies.stream()
                .map(MovieDTO::toString)
                .collect(Collectors.joining("\n", "", ""));
    }

    public String formatRoomList(List<RoomDTO> rooms) {
        if (rooms.isEmpty()) {
            return "There are no rooms at the moment";
        }
        return rooms.stream()
                .map(RoomDTO::toString)
                .collect(Collectors.joining("\n", "", ""));
    }

    public String formatScreenings(List<ScreeningDTO> screenings) {
        if (screenings.isEmpty()) {
            return "There are no screenings";
        }
        StringBuilder stringBuilder = new StringBuilder();
        var movies = this.movieService.getMovies();
        for (int i = 0; i < screenings.size(); i++) {
            String movieTitle = screenings.get(i).getMovieName();
            String roomName = screenings.get(i).getRoomName();
            String date = screenings.get(i).getTimeOfScreening();

            MovieDTO movie = movies.stream()
                    .filter(movieDTO -> movieDTO.getTitle().equals(movieTitle))
                    .findFirst()
                    .get();


            String record = String.format("%s, screened in room %s, at %s", movie, roomName, date);
            stringBuilder
                    .append(record);
            if (i < screenings.size() - 1) {
                stringBuilder.append("\n");
            }
        }
        return stringBuilder.toString();
    }
}
