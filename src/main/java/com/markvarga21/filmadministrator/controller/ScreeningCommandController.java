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
//
//    @ShellMethod(value = "Deleting rooms.", key = "delete room")
//    @ShellMethodAvailability("checkAdmin")
//    public String deleteRoom(String name) {
//        if (this.roomService.deleteRoom(name) == 1) {
//            return String.format("Room '%s' deleted successfully!", name);
//        }
//        return String.format("Something went wrong when deleting room '%s'", name);
//    }
//
//    @ShellMethod(value = "Updating rooms.", key = "update room")
//    @ShellMethodAvailability("checkAdmin")
//    public String updateRoom(String name, Long chairRowsCount, Long chairColumnsCount) {
//        RoomDTO roomToUpdate = new RoomDTO(name, chairRowsCount, chairColumnsCount);
//        return this.roomService.updateRoom(roomToUpdate);
//    }
}
