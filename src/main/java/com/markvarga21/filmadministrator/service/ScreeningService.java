package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.RoomDTO;
import com.markvarga21.filmadministrator.dto.ScreeningDTO;
import com.markvarga21.filmadministrator.entity.Room;
import com.markvarga21.filmadministrator.entity.Screening;
import com.markvarga21.filmadministrator.mapping.ScreeningMapper;
import com.markvarga21.filmadministrator.repository.ScreeningRepository;
import com.markvarga21.filmadministrator.util.CompositeKey;
import com.markvarga21.filmadministrator.util.ScreeningDateTimeConverter;
import com.markvarga21.filmadministrator.util.ScreeningValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class ScreeningService {
    Logger log = Logger.getLogger(ScreeningService.class.getName());
    private final ScreeningMapper screeningMapper;
    private final ScreeningRepository screeningRepository;
    private final ScreeningValidator screeningValidator;
    private final ScreeningDateTimeConverter converter;

    @Transactional
    public String saveScreening(ScreeningDTO screeningToSave) {
        if (!this.screeningValidator.isValidReservation(screeningToSave)) {
            return "Screening invalid, because film or room does not exist!";
        }

        String movieName = screeningToSave.getMovieName();
        String roomName = screeningToSave.getRoomName();
        String timeOfScreening = screeningToSave.getTimeOfScreening();

        var screeningsForRoom = this.getScreeningsForRoom(roomName);
        if (!this.getScreenings().isEmpty() &&
                !this.screeningValidator.isValidScreenDateTime(movieName, timeOfScreening, screeningsForRoom)) {
            return "There is an overlapping screening";
        }

        if (!this.getScreenings().isEmpty() &&
                !this.screeningValidator.isPausePresent(movieName, roomName, timeOfScreening, screeningsForRoom)) {
            return "This would start in the break period after another screening in this room";
        }

        Screening screening = this.screeningMapper.mapScreeningDtoToEntity(screeningToSave);
        this.screeningRepository.save(screening);

        return String.format("Screening '%s' in room '%s' on '%s' saved!",
                movieName,
                roomName,
                timeOfScreening
        );
    }

    public List<ScreeningDTO> getScreenings() {
        return this.screeningRepository
            .findAll()
            .stream()
            .map(this.screeningMapper::mapScreeningToDto)
            .toList();
    }

    @Transactional
    public void deleteScreening(String movieName, String roomName, String timeOfScreening) {
        CompositeKey compositeKey = new CompositeKey(movieName, roomName, this.converter.convertScreeningTimeString(timeOfScreening));
        this.screeningRepository.deleteById(compositeKey);
    }

    public List<ScreeningDTO> getScreeningsForRoom(String roomName) {
        return this.getScreenings()
                .stream()
                .filter(screeningDTO -> screeningDTO.getRoomName().equals(roomName))
                .toList();
    }
}
