package com.markvarga21.filmadministrator.controller;

import com.markvarga21.filmadministrator.dto.MovieDTO;
import com.markvarga21.filmadministrator.service.MovieService;
import com.markvarga21.filmadministrator.service.SigningService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class CreateCommandController {
    private final SigningService signingService;
    private final MovieService movieService;

    @ShellMethod(value = "Creating movies", key = "create movie")
    @ShellMethodAvailability("checkAdmin")
    public String createMovie(String name, String genre, Long length) {
        MovieDTO movieToSave = new MovieDTO(name, genre, length);
        this.movieService.saveMovie(movieToSave);
        return String.format("Movie '%s' with the length of %d created!", name, length);
    }

    public Availability checkAdmin() {
        boolean isAdminLoggedIn = this.signingService.isAdminLoggedIn();
        return isAdminLoggedIn ?  Availability.available() : Availability.unavailable("Only admin has access for this command!");
    }

    public Availability checkUserLoggedIn() {
        boolean isSomeoneLoggedIn = this.signingService.isSomeoneLoggedIn();
        return isSomeoneLoggedIn ?  Availability.available() : Availability.unavailable("Please login before executing this command!");
    }
}
