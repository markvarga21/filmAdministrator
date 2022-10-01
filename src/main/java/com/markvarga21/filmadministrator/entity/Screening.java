package com.markvarga21.filmadministrator.entity;

import com.markvarga21.filmadministrator.util.CompositeKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(CompositeKey.class)
public class Screening {
    @Id
    private String movieName;
    @Id
    private String roomName;
    @Id
    private LocalDateTime timeOfScreening;
}
