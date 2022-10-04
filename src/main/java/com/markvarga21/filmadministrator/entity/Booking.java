package com.markvarga21.filmadministrator.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookingId;
    private String userName;
    private String movieName;
    private String roomName;
    private LocalDateTime timeOfScreening;
    @ManyToOne
    private Seat bookedSeat;
}
