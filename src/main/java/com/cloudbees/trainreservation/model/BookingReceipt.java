package com.cloudbees.trainreservation.model;

public class BookingReceipt {
    private final String receiptId;
    private final String source;
    private final String destination;
    private final User user;
    private final int pricePaid;

    private final Seat seat;

    public BookingReceipt(String receiptId, String source, String destination, User user, int pricePaid, Seat seat) {
        this.receiptId = receiptId;
        this.source = source;
        this.destination = destination;
        this.user = user;
        this.pricePaid = pricePaid;
        this.seat = seat;
    }

    public String getReceiptId() {
        return receiptId;
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


    public int getPricePaid() {
        return pricePaid;
    }

    public Seat getSeat() {
        return seat;
    }

}
