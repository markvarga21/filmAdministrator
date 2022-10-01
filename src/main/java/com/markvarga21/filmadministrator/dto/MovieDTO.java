package com.markvarga21.filmadministrator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    @NotEmpty
    private String title;
    @NotEmpty
    private String genre;
    @Positive
    private Long length;

    @Override
    public String toString() {
        return String.format("%s (%s, %d minutes)", this.title, this.genre, this.length);
    }
}
