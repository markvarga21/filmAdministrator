package com.markvarga21.filmadministrator.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
}
