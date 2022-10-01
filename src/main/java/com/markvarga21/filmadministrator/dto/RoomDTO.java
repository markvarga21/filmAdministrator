package com.markvarga21.filmadministrator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    @NotEmpty
    private String name;
    @Positive
    private Long chairRowsCount;
    @Positive
    private Long chairColumnsCount;

    @Override
    public String toString() {
        return String.format("Room %s with %d seats, %d rows and %d columns",
                this.name,
                this.chairRowsCount * this.chairColumnsCount,
                this.chairRowsCount,
                this.chairColumnsCount);
    }
}
