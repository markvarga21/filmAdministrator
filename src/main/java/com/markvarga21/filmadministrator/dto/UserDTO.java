package com.markvarga21.filmadministrator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserDTO {
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
}
