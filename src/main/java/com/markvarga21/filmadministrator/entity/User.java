package com.markvarga21.filmadministrator.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String userName;
    private String password;
}
