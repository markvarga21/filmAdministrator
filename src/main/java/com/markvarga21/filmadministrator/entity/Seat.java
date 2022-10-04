package com.markvarga21.filmadministrator.entity;

import com.markvarga21.filmadministrator.util.SeatCompositeKey;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(SeatCompositeKey.class)
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    private String roomName;
    @Id
    private Long seatRow;
    @Id
    private Long seatColumn;
}
