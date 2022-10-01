package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.MovieDTO;
import com.markvarga21.filmadministrator.dto.RoomDTO;
import com.markvarga21.filmadministrator.entity.Movie;
import com.markvarga21.filmadministrator.entity.Room;
import com.markvarga21.filmadministrator.mapping.RoomMapper;
import com.markvarga21.filmadministrator.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    @Transactional
    public String saveRoom(@Valid RoomDTO roomToSave) {
        Room roomEntity = this.roomMapper.mapRoomDtoToEntity(roomToSave);
        this.roomRepository.save(roomEntity);
        return String.format("Room '%s' created!", roomToSave.getName());
    }

    public List<RoomDTO> getRooms() {
        return this.roomRepository
                .findAll()
                .stream()
                .map(this.roomMapper::mapRoomToDto)
                .toList();
    }

    @Transactional
    public int deleteRoom(String name) {
        return this.roomRepository.deleteByName(name);
    }

    @Transactional
    public String updateRoom(RoomDTO roomToUpdate) {
        var roomOptional = this.roomRepository.findById(roomToUpdate.getName());

        if (roomOptional.isEmpty()) {
            return String.format("Cannot update, because room '%s' not found!", roomToUpdate.getName());
        }

        Room roomEntityToUpdate = roomOptional.get();
        roomEntityToUpdate.setChairRowsCount(roomToUpdate.getChairRowsCount());
        roomEntityToUpdate.setChairColumnsCount(roomToUpdate.getChairColumnsCount());
        this.roomRepository.save(roomEntityToUpdate);

        return String.format("Room '%s' updated successfully!", roomToUpdate.getName());
    }

    public boolean roomExists(String roomName) {
        return this.roomRepository.existsById(roomName);
    }
}
