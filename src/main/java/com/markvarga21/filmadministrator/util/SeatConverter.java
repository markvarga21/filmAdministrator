package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.dto.SeatDTO;
import com.markvarga21.filmadministrator.entity.Seat;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class SeatConverter {
    public List<SeatDTO> convertSeatStringToList(String seats, String roomName) {
        return Arrays.stream(seats.split(" "))
                .map(s -> new SeatDTO(roomName, Long.valueOf(s.split(",")[0]), Long.valueOf(s.split(",")[1])))
                .toList();
    }
}
