package com.cloudbees.trainreservation;

import com.cloudbees.trainreservation.model.BookingReceipt;
import com.cloudbees.trainreservation.model.Seat;
import com.cloudbees.trainreservation.model.User;
import com.cloudbees.trainreservation.service.TrainReservationService;
import com.cloudbees.trainreservation.service.request.ReserveSeatRequest;

import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        TrainReservationService trainReservationService = new TrainReservationService();
        User user = new User("Raj", "Kumar", "rajk@example.com");
        Seat seat = new Seat("A", 50);
        ReserveSeatRequest reserveSeatRequest = new ReserveSeatRequest("London", "France", user, seat);
        Optional<BookingReceipt> bookingReceipt = trainReservationService.reserveSeat(reserveSeatRequest);
        if (!bookingReceipt.isPresent()) {
            throw new IllegalStateException();
        }
        System.out.printf("Seat reserved with booking id : %s , Price : %d%n", bookingReceipt.get().getReceiptId(),
                bookingReceipt.get().getPricePaid());

    }
}