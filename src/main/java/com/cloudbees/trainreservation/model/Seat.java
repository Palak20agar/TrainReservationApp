package com.cloudbees.trainreservation.model;

public class Seat {

    private final String section;
    private final int number;

    public Seat(String section, int number) {
        this.section = section;
        this.number = number;
    }


    public String getSection() {
        return section;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Seat)) {
            return false;
        }
        final Seat otherSeat = (Seat) other;
        return otherSeat.getSection().equals(this.section) && otherSeat.getNumber() == this.number;
    }

    @Override
    public int hashCode() {
        return section.hashCode() + 31 * number;
    }
}
