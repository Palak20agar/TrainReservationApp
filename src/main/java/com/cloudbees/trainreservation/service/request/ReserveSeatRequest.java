package com.cloudbees.trainreservation.service.request;

import com.cloudbees.trainreservation.model.Seat;
import com.cloudbees.trainreservation.model.User;

import java.util.Optional;

public class ReserveSeatRequest {
    private final String source;
    private final String destination;
    private final User user;

    private final Optional<Seat> seat;

    public ReserveSeatRequest(String source, String destination, User user, Seat seat) {
        this.source = source;
        this.destination = destination;
        this.user = user;
        this.seat = Optional.ofNullable(seat);
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public User getUser() {
        return user;
    }

    public Optional<Seat> getSeat() {
        return seat;
    }
}
