package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceCalculator {
    private final PricingService pricingService;

    public Long calculatePricing(int numberOfSeatsToBook, String roomName, String movieName) {
        return this.calculateAttachmentPricePerSeat(roomName, movieName) * numberOfSeatsToBook;
    }

    private Long calculateAttachmentPricePerSeat(String roomName, String movieName) {
        return this.pricingService.getBasePrice() +
                this.priceAttachmentForRoom(roomName) +
                this.priceAttachmentForMovie(movieName);
    }

    private Long priceAttachmentForRoom(String roomName) {
        return this.pricingService.getAttachmentForRoom(roomName);
    }

    private Long priceAttachmentForMovie(String movieName) {
        return this.pricingService.getAttachmentForMovie(movieName);
    }
}
