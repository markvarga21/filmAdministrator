package com.markvarga21.filmadministrator.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@Slf4j
public class CreateCommandController {
    @ShellMethod(value = "Creating movies", key = "create movie")
    public String createMovie(String name, Long length) {
        return String.format("Movie '%s' with the length of %d created!", name, length);
    }
}
