package com.markvarga21.filmadministrator.controller;

import com.markvarga21.filmadministrator.service.BookingService;
import com.markvarga21.filmadministrator.service.SigningService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class SigningCommandController {
    private final SigningService signingService;
    private final BookingService bookingService;

    @ShellMethod(value = "Sign in option for admin.", key = "sign in privileged")
    public String signInAdmin(String userName, String password) {
        return this.signingService.logInAsAdmin(userName, password);
    }

    @ShellMethod(value = "Sign in option for user.", key = "sign in")
    public String signInAsUser(String userName, String password) {
        return this.signingService.logInUser(userName, password);
    }

    @ShellMethod(value = "Sign up for users.", key = "sign up")
    public String signUpUser(String userName, String password) {
        return this.signingService.signUpUser(userName, password);
    }

    @ShellMethod(value = "Describing signed in account.", key = "describe account")
    public String describeAccount() {
        String userName = this.signingService.getLoggedInUser();
        String accountInfo = this.signingService.describeAccount();
        String bookingInfo = this.bookingService.getBookingsForUser(userName);
        return String.format("%s%n%s", accountInfo, bookingInfo);
    }

    @ShellMethod(value = "Command for signing out.", key = "sign out")
    public String signOut() {
        return this.signingService.signOutUser();
    }
}
