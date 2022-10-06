package com.markvarga21.filmadministrator.service;

import com.markvarga21.filmadministrator.dto.PriceComponentDTO;
import com.markvarga21.filmadministrator.entity.BasePrice;
import com.markvarga21.filmadministrator.entity.MoviePriceAttachment;
import com.markvarga21.filmadministrator.entity.PriceComponent;
import com.markvarga21.filmadministrator.entity.RoomPriceAttachment;
import com.markvarga21.filmadministrator.mapping.BasePriceMapper;
import com.markvarga21.filmadministrator.mapping.PriceComponentMapper;
import com.markvarga21.filmadministrator.repository.BasePriceRepository;
import com.markvarga21.filmadministrator.repository.MoviePriceAttachmentRepository;
import com.markvarga21.filmadministrator.repository.PriceComponentRepository;
import com.markvarga21.filmadministrator.repository.RoomPriceAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class PricingService {
    private final BasePriceRepository basePriceRepository;
    private final PriceComponentRepository priceComponentRepository;
    private final RoomPriceAttachmentRepository roomPriceAttachmentRepository;
    private final MoviePriceAttachmentRepository moviePriceAttachmentRepository;
    private final BasePriceMapper basePriceMapper;
    private final PriceComponentMapper priceComponentMapper;

    @PostConstruct
    private void postConstruct() {
        BasePrice basePrice = new BasePrice();
        basePrice.setBasePrice(1500L);
        this.basePriceRepository.save(basePrice);
    }

    public String updateBasePrice(Long newPrice) {
        var oldBasePriceEntity = this.basePriceRepository.findAll().get(0);
        var oldBasePriceDto = this.basePriceMapper.convertBasePriceEntityToDto(oldBasePriceEntity);
        oldBasePriceEntity.setBasePrice(newPrice);
        this.basePriceRepository.save(oldBasePriceEntity);
        return String.format("Old base price %d update to %d", oldBasePriceDto.getBasePrice(), newPrice);
    }

    public Long getBasePrice() {
        return this.basePriceRepository
                .findAll()
                .get(0)
                .getBasePrice();
    }

    public Long getAttachmentForRoom(String roomName) {
        var componentNameOptional = this.roomPriceAttachmentRepository
                .findAll()
                .stream()
                .filter(roomPriceAttachment -> roomPriceAttachment.getRoomName().equals(roomName))
                .map(RoomPriceAttachment::getComponentName)
                .findFirst();
        if (componentNameOptional.isEmpty()) {
            return 0L;
        }
        String componentName = componentNameOptional.get();
        return this.getPriceForComponent(componentName);
    }

    public Long getAttachmentForMovie(String movieName) {
        var componentNameOptional = this.moviePriceAttachmentRepository
                .findAll()
                .stream()
                .filter(moviePriceAttachment -> moviePriceAttachment.getMovieName().equals(movieName))
                .map(MoviePriceAttachment::getComponentName)
                .findFirst();
        if (componentNameOptional.isEmpty()) {
            return 0L;
        }
        String componentName = componentNameOptional.get();
        return this.getPriceForComponent(componentName);
    }

    private Long getPriceForComponent(String componentName) {
        var componentPriceOptional = this.priceComponentRepository
                .findAll()
                .stream()
                .filter(priceComponent -> priceComponent.getComponentName().equals(componentName))
                .map(PriceComponent::getComponentPrice)
                .findFirst();
        if (componentPriceOptional.isEmpty()) {
            return 0L;
        }
        return componentPriceOptional.get();
    }

    public String addPricingComponent(String componentName, Long componentPrice) {
        PriceComponent priceComponentToSave = new PriceComponent();
        priceComponentToSave.setComponentName(componentName);
        priceComponentToSave.setComponentPrice(componentPrice);
        this.priceComponentRepository.save(priceComponentToSave);
        return String.format("Price component with name %s and price %d saved successfully!", componentName, componentPrice);
    }

    public String attachPriceComponentToRoom(String componentName, String roomName) {
        RoomPriceAttachment roomPriceAttachment = new RoomPriceAttachment();
        roomPriceAttachment.setComponentName(componentName);
        roomPriceAttachment.setRoomName(roomName);
        this.roomPriceAttachmentRepository.save(roomPriceAttachment);
        return String.format("Component with name %s attached to room %s", componentName, roomName);
    }

    public String attachPriceComponentToMovie(String componentName, String movieName) {
        MoviePriceAttachment moviePriceAttachment = new MoviePriceAttachment();
        moviePriceAttachment.setComponentName(componentName);
        moviePriceAttachment.setMovieName(movieName);
        this.moviePriceAttachmentRepository.save(moviePriceAttachment);
        return String.format("Component with name %s attached to movie %s", componentName, movieName);
    }
}
