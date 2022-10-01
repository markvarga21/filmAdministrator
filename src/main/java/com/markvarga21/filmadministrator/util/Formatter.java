package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.dto.MovieDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Formatter {
    public String formatMovieList(List<MovieDTO> movies) {
        if (movies.isEmpty()) {
            return "There are no movies at the moment";
        }
        return movies.stream()
                .map(MovieDTO::toString)
                .collect(Collectors.joining("\n", "", ""));
    }
}
