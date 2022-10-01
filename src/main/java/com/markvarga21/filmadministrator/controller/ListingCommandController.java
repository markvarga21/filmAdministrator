package com.markvarga21.filmadministrator.controller;

import com.markvarga21.filmadministrator.service.MovieService;
import com.markvarga21.filmadministrator.util.Formatter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class ListingCommandController {
    private final Formatter formatter;
    private final MovieService movieService;

    public Availability anyone() {
        return Availability.available();
    }

    @ShellMethod(value = "Listing movies.", key = "list movies")
    @ShellMethodAvailability("anyone")
    public String listMovies() {
        var movies = this.movieService.getMovies();
        return this.formatter.formatMovieList(movies);
    }
}
