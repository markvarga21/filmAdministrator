package com.markvarga21.filmadministrator.controller;

import com.markvarga21.filmadministrator.dto.MovieDTO;
import com.markvarga21.filmadministrator.service.MovieService;
import com.markvarga21.filmadministrator.service.SigningService;
import com.markvarga21.filmadministrator.util.Formatter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class MovieCommandController {
    private final SigningService signingService;
    private final MovieService movieService;
    private final Formatter formatter;
    public Availability checkAdmin() {
        boolean isAdminLoggedIn = this.signingService.isAdminLoggedIn();
        return isAdminLoggedIn ?  Availability.available() : Availability.unavailable("Only admin has access for this command!");
    }

    public Availability checkUserLoggedIn() {
        boolean isSomeoneLoggedIn = this.signingService.isSomeoneLoggedIn();
        return isSomeoneLoggedIn ?  Availability.available() : Availability.unavailable("Please login before executing this command!");
    }

    public Availability anyone() {
        return Availability.available();
    }

    @ShellMethod(value = "Creating movies", key = "create movie")
    @ShellMethodAvailability("checkAdmin")
    public String createMovie(String name, String genre, Long length) {
        MovieDTO movieToSave = new MovieDTO(name, genre, length);
        this.movieService.saveMovie(movieToSave);
        return String.format("Movie '%s' with the length of %d created!", name, length);
    }

    @ShellMethod(value = "Listing movies.", key = "list movies")
    @ShellMethodAvailability("anyone")
    public String listMovies() {
        var movies = this.movieService.getMovies();
        return this.formatter.formatMovieList(movies);
    }


}
