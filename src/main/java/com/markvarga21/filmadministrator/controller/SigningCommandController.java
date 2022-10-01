package com.markvarga21.filmadministrator.controller;

import com.markvarga21.filmadministrator.service.SigningService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class SigningCommandController {
    private final SigningService signingService;

    @ShellMethod(value = "Sign in option for admin.", key = "sign in privileged")
    public String signInAdmin(String userName, String password) {
        return this.signingService.logInAsAdmin(userName, password);
    }

    @ShellMethod(value = "Describing signed in account.", key = "describe")
    public String describeAccount() {
        return this.signingService.describeAccount();
    }
}
