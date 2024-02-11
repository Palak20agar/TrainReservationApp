package com.cloudbees.trainreservation.service;

import com.cloudbees.trainreservation.model.BookingReceipt;
import com.cloudbees.trainreservation.model.Seat;
import com.cloudbees.trainreservation.model.User;
import com.cloudbees.trainreservation.registry.BookingRegistry;
import com.cloudbees.trainreservation.service.request.ReserveSeatRequest;

import java.util.Map;
import java.util.Optional;

public class TrainReservationService {
    private final BookingRegistry registry = new BookingRegistry();

    public Optional<BookingReceipt> reserveSeat(final ReserveSeatRequest reserveSeatRequest) {
        if (reserveSeatRequest.getSeat().isPresent()) {
            if (registry.isSeatAvailable(reserveSeatRequest.getSeat().get())) {
                return Optional.of(registry.reserveSeat(reserveSeatRequest));
            }
        } else {
            final Optional<Seat> seat = registry.getNextAvailableSeat(reserveSeatRequest.getSource(),
                    reserveSeatRequest.getDestination());
            if (seat.isPresent()) {
                return Optional.of(registry.reserveSeat(new ReserveSeatRequest(reserveSeatRequest.getSource(),
                        reserveSeatRequest.getDestination(), reserveSeatRequest.getUser(), seat.get())));
            }
        }

        return Optional.empty();
    }

    public Optional<BookingReceipt> getBookingReceipt(final User user) {

        return registry.getBookingReceipt(user);
    }

    public Map<User, Seat> getUserSeatMapping(final String section) {
        return registry.getUserSeatMapping(section);
    }

    public boolean cancelBooking(final User user) {
        return registry.cancelBooking(user);
    }

    public Optional<BookingReceipt> modifyBooking(final User user, Seat newSeat) {

        if (registry.isSeatAvailable(newSeat)) {
            return Optional.of(registry.modifyBooking(user, newSeat));
        }
        return Optional.empty();
    }

}


