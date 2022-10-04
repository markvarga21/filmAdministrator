package com.markvarga21.filmadministrator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private String userName;
    private String movieName;
    private String roomName;
    private String timeOfScreening;
    private SeatDTO bookedSeat;
}
