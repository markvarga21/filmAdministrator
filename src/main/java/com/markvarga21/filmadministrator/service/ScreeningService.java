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

@Service
@RequiredArgsConstructor
public class ScreeningService {
    private final ScreeningMapper screeningMapper;
    private final ScreeningRepository screeningRepository;
    private final ScreeningValidator screeningValidator;
    private final ScreeningDateTimeConverter converter;

    @Transactional
    public String saveScreening(ScreeningDTO screeningToSave) {
        if (!this.screeningValidator.isValidReservation(screeningToSave)) {
            return "Screening invalid!";
        }

        Screening screening = this.screeningMapper.mapScreeningDtoToEntity(screeningToSave);
        this.screeningRepository.save(screening);
        return String.format("Screening '%s' in room '%s' on '%s' saved!",
                screeningToSave.getMovieName(),
                screeningToSave.getRoomName(),
                screeningToSave.getTimeOfScreening()
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
}
