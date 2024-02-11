package com.cloudbees.trainreservation.service;

import com.cloudbees.trainreservation.model.BookingReceipt;
import com.cloudbees.trainreservation.model.Seat;
import com.cloudbees.trainreservation.model.User;
import com.cloudbees.trainreservation.service.request.ReserveSeatRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


public class TrainReservationServiceTest {

    private TrainReservationService trainReservationService;

    @BeforeEach
    public void setUp() {
        trainReservationService = new TrainReservationService();
    }

    @Test
    public void testReserveSeat_WithAvailableSeat() {
        Seat seat = new Seat("A", 5);
        User user = new User("Raj", "Kumar", "rajk@example.com");
        ReserveSeatRequest request = new ReserveSeatRequest("Source", "Destination", user, seat);

        Optional<BookingReceipt> result = trainReservationService.reserveSeat(request);

        assertTrue(result.isPresent());
        assertEquals(seat, result.get().getSeat());
    }

    @Test
    public void testReserveSeat_WithoutSpecificSeat() {
        User user = new User("Raj", "Kumar", "rajk@example.com");
        ReserveSeatRequest request = new ReserveSeatRequest("Source", "Destination", user, null);
        Optional<BookingReceipt> result = trainReservationService.reserveSeat(request);

        assertTrue(result.isPresent());
        assertEquals(user, result.get().getUser());

    }

    @Test
    public void testGetBookingReceipt() {
        User user = new User("Raj", "Kumar", "rajk@example.com");
        Seat seat = new Seat("A", 50);

        ReserveSeatRequest request = new ReserveSeatRequest("Source", "Destination", user, seat);
        Optional<BookingReceipt> reserveSeat = trainReservationService.reserveSeat(request);
        assertTrue(reserveSeat.isPresent());
        Optional<BookingReceipt> result = trainReservationService.getBookingReceipt(user);
        assertTrue(result.isPresent());
    }

    @Test
    public void testCancelBooking() {
        User user = new User("Raj", "Kumar", "rajk@example.com");
        Seat seat = new Seat("A", 50);

        ReserveSeatRequest request = new ReserveSeatRequest("Source", "Destination", user, seat);
        Optional<BookingReceipt> reserveSeat = trainReservationService.reserveSeat(request);
        assertTrue(reserveSeat.isPresent());
        boolean result = trainReservationService.cancelBooking(user);
        assertTrue(result);
        assertFalse(trainReservationService.getBookingReceipt(user).isPresent());
    }

    @Test
    public void testModifyBooking() {
        User user = new User("Raj", "Kumar", "rajk@example.com");
        Seat seat = new Seat("A", 50);

        ReserveSeatRequest request = new ReserveSeatRequest("Source", "Destination", user, seat);
        Optional<BookingReceipt> bookingReceipt1 = trainReservationService.reserveSeat(request);
        assertTrue(bookingReceipt1.isPresent());
        assertEquals(seat, bookingReceipt1.get().getSeat());

        Seat seat2 = new Seat("B", 50);
        Optional<BookingReceipt> bookingReceipt2 = trainReservationService.modifyBooking(user, seat2);
        assertTrue(bookingReceipt2.isPresent());
        assertEquals(seat2, bookingReceipt2.get().getSeat());
    }

}

