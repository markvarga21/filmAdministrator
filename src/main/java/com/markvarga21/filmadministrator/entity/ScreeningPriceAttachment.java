package com.markvarga21.filmadministrator.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class ScreeningPriceAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String componentName;
    private String movieName;
    private String roomName;
    private String screeningDate;
}
