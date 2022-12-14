package com.markvarga21.filmadministrator.entity;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PriceComponent {
    @Id
    private String componentName;
    private Long componentPrice;
}
