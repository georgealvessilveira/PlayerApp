package com.georgeasilveira.playerapp.model;

import org.joda.time.LocalTime;

import java.io.Serializable;

public class Video implements Serializable {
    private final String name;
    private final LocalTime time;
    private final String videoLocation;

    public Video(String name, LocalTime time, String videoLocation) {
        this.name = name;
        this.time = time;
        this.videoLocation = videoLocation;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getVideoLocation() {
        return videoLocation;
    }
}
