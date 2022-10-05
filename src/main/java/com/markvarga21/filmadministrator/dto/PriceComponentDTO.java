package com.markvarga21.filmadministrator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PriceComponentDTO {
    private String componentName;
    private Long componentPrice;
}
