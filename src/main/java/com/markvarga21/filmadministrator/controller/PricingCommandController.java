package com.markvarga21.filmadministrator.controller;

import com.markvarga21.filmadministrator.service.PricingService;
import com.markvarga21.filmadministrator.service.SigningService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
@RequiredArgsConstructor
public class PricingCommandController {
    private final PricingService pricingService;
    private final SigningService signingService;

    public Availability checkAdmin() {
        boolean isAdminLoggedIn = this.signingService.isAdminLoggedIn();
        return isAdminLoggedIn ?  Availability.available() : Availability.unavailable("Only admin has access for this command!");
    }

    @ShellMethod(value = "Updating base price.", key = "update base price")
    @ShellMethodAvailability("checkAdmin")
    public String updateBasePrice(Long newPrice) {
        return this.pricingService.updateBasePrice(newPrice);
    }

    @ShellMethod(value = "Creating price components.", key = "create price component")
    @ShellMethodAvailability("checkAdmin")
    public String createPriceComponentForRoom(String componentName, Long componentPrice) {
        return this.pricingService.addPricingComponent(componentName, componentPrice);
    }

    @ShellMethod(value = "Attaching price components to rooms.", key = "attach price component to room")
    @ShellMethodAvailability("checkAdmin")
    public String attachPriceComponentToRoom(String componentName, String roomName) {
        return this.pricingService.attachPriceComponentToRoom(componentName, roomName);
    }
}
