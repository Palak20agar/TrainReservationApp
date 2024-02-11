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

    /**
     * API to reserve a Seat .
     * Takes reserveSeatRequest as parameter and returns the booking receipt .
     * If seat mentioned in the Request is available then reserves it else
     * fetches the next avaible seat and returns the receipt .
     *
     * @param reserveSeatRequest
     * @return BookingReceipt
     */
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

    /**
     * API to get the booking Receipt for a user .
     *
     * @param user
     * @return BookingReceipt
     */
    public Optional<BookingReceipt> getBookingReceipt(final User user) {

        return registry.getBookingReceipt(user);
    }

    /**
     * API to get all the User seat mapping for a section.
     *
     * @param section
     * @return Map<User, Seat>
     */
    public Map<User, Seat> getUserSeatMapping(final String section) {
        return registry.getUserSeatMapping(section);
    }

    /**
     * API to cancel the booking for a  user.
     *
     * @param user
     * @return
     */
    public boolean cancelBooking(final User user) {
        return registry.cancelBooking(user);
    }

    /**
     * API to modify Booking(update seat) for a user .
     * If seat is not available then returs empty receipt .
     *
     * @param user
     * @param newSeat
     * @return Optional<BookingReceipt>
     */
    public Optional<BookingReceipt> modifyBooking(final User user, Seat newSeat) {

        if (registry.isSeatAvailable(newSeat)) {
            return Optional.of(registry.modifyBooking(user, newSeat));
        }
        return Optional.empty();
    }

}


