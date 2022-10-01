package com.markvarga21.filmadministrator.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UserSession {
    @Id
    private Long id;
    private String userName;
}
