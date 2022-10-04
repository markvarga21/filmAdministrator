package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.dto.BookingDTO;
import com.markvarga21.filmadministrator.dto.SeatDTO;
import com.markvarga21.filmadministrator.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookingValidator {
    private final RoomService roomService;

    public boolean isValidSeatForRoom(String roomName, SeatDTO seatToCheck) {
        return this.roomService
                .getRooms()
                .stream()
                .filter(roomDTO -> roomDTO.getName().equals(roomName))
                .anyMatch(roomDTO -> seatToCheck.getSeatColumn() <= roomDTO.getChairColumnsCount()
                            && seatToCheck.getSeatRow() <= roomDTO.getChairRowsCount());
    }

    public boolean isSeatFree(List<BookingDTO> allBookings, SeatDTO seatDTO) {
        return true;
    }
}
