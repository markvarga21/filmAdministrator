package com.markvarga21.filmadministrator.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Movie {
    @Id
    private String title;
    private String genre;
    private Long length;
}
