package com.markvarga21.filmadministrator.controller;

import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.service.ScreeningService;
import com.markvarga21.filmadministrator.service.SigningService;
import com.markvarga21.filmadministrator.util.Formatter;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class ScreeningCommandController {
    private final SigningService signingService;
    private final ScreeningService screeningService;
    private final Formatter formatter;

    public Availability checkAdmin() {
        boolean isAdminLoggedIn = this.signingService.isAdminLoggedIn();
        return isAdminLoggedIn ? Availability.available() : Availability.unavailable("Only admin has access for this command!");
    }
    public Availability anyone() {
        return Availability.available();
    }


    @ShellMethod(value = "Creating screenings.", key = "create screening")
    @ShellMethodAvailability("checkAdmin")
    public String createScreening(String movieName, String roomName, String timeOfScreening) {
        ScreeningDTO screeningToSave = new ScreeningDTO(movieName, roomName, timeOfScreening);
        return this.screeningService.saveScreening(screeningToSave);
    }

    @ShellMethod(value = "Listing screenings.", key = "list screenings")
    @ShellMethodAvailability("anyone")
    public String listScreenings() {
        var screenings = this.screeningService.getScreenings();
        return this.formatter.formatScreenings(screenings);
    }

    @ShellMethod(value = "Deleting screenings.", key = "delete screening")
    @ShellMethodAvailability("checkAdmin")
    public String deleteScreening(String movieName, String roomName, String timeOfScreening) {
        this.screeningService.deleteScreening(movieName, roomName, timeOfScreening);
        return String.format("Screening '%s' deleted successfully!", movieName);
    }
}
