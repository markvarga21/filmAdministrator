package com.markvarga21.filmadministrator.util;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CompositeKey implements Serializable {
    private String movieName;
    private String roomName;
    private LocalDateTime timeOfScreening;
}
