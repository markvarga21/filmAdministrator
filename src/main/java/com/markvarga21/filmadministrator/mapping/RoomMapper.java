package com.markvarga21.filmadministrator.mapping;

import com.markvarga21.filmadministrator.dto.RoomDTO;
import com.markvarga21.filmadministrator.entity.Room;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RoomMapper {
    private final ModelMapper modelMapper;

    public RoomDTO mapRoomToDto(Room room) {
        return this.modelMapper.map(room, RoomDTO.class);
    }

    public Room mapRoomDtoToEntity(RoomDTO roomDTO) {
        return this.modelMapper.map(roomDTO, Room.class);
    }
}
