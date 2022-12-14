package com.markvarga21.filmadministrator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingDTO {
    private String userName;
    private ScreeningDTO screeningDTO;
    private SeatDTO bookedSeat;
}
