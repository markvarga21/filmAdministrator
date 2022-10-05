package com.markvarga21.filmadministrator.util;

import com.markvarga21.filmadministrator.service.PricingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PriceCalculator {
    private final PricingService pricingService;

    public Long calculatePricing(int numberOfSeatsToBook, String roomName) {
        return this.calculateAttachmentPricePerSeat(roomName) * numberOfSeatsToBook;
    }

    private Long calculateAttachmentPricePerSeat(String roomName) {
        return this.pricingService.getBasePrice() + this.priceAttachmentForRoom(roomName);
    }

    private Long priceAttachmentForRoom(String roomName) {
        return this.pricingService.getAttachmentForRoom(roomName);
    }
}
