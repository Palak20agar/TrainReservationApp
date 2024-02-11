package com.cloudbees.trainreservation.registry;

import com.cloudbees.trainreservation.model.BookingReceipt;
import com.cloudbees.trainreservation.model.Seat;
import com.cloudbees.trainreservation.model.User;
import com.cloudbees.trainreservation.service.request.ReserveSeatRequest;

import java.util.*;

public class BookingRegistry {

    // user<->seat
    // Set of available seat
    private final Map<Seat,User> seat2UserMap;

    private final Map<User,BookingReceipt> userBookingReceiptMap;

    private final Set<Seat> availableSeats;

    private static final int MAX_CAPACITY_PER_SECTION =50;

    private static final Set<String> SECTIONS = new HashSet<>(Arrays.asList("A","B"));

    private static final int TICKET_PRICE = 20;

    public BookingRegistry(){
        this.seat2UserMap = new HashMap<>();
        this.availableSeats = new HashSet<>();
        for (String section: SECTIONS) {
            for (int i = 1; i <= MAX_CAPACITY_PER_SECTION ; i++) {
                availableSeats.add(new Seat(section,i));
            }
        }
        this.userBookingReceiptMap=new HashMap<>();
    }
    public boolean isSeatAvailable(final Seat seat){
        //check in available seats
        return !availableSeats.isEmpty() && availableSeats.contains(seat);
    }

    public Optional<Seat> getNextAvailableSeat(String source, String destination){
        Optional<Seat> nextSeat = availableSeats.stream().findAny();
        return nextSeat;
    }

    public BookingReceipt reserveSeat(ReserveSeatRequest request){
        //remove from available seats
        availableSeats.remove(request.getSeat());
        seat2UserMap.put(request.getSeat().get(), request.getUser());
        BookingReceipt bookingReceipt = new BookingReceipt(UUID.randomUUID().toString(),request.getSource(),
                request.getDestination(),request.getUser(),TICKET_PRICE,request.getSeat().get());
        userBookingReceiptMap.put(request.getUser(),bookingReceipt);
        return bookingReceipt;
    }

    public Optional<BookingReceipt> getBookingReceipt(User user){
        return Optional.ofNullable(userBookingReceiptMap.get(user));
    }

    public boolean cancelBooking(User user){
        availableSeats.add(userBookingReceiptMap.get(user).getSeat());
        return userBookingReceiptMap.remove(user) != null;
    }

    public BookingReceipt modifyBooking(User user, Seat newSeat){
        BookingReceipt bookingReceipt = userBookingReceiptMap.get(user);
        availableSeats.add(bookingReceipt.getSeat());
        BookingReceipt newBookingReceipt = new BookingReceipt(UUID.randomUUID().toString(),bookingReceipt.getSource()
                ,bookingReceipt.getDestination(),bookingReceipt.getUser(),TICKET_PRICE,newSeat);
        userBookingReceiptMap.put(user,newBookingReceipt);
        availableSeats.remove(newSeat);
        return newBookingReceipt;
    }

    public Map<User, Seat> getUserSeatMapping(final String section) {
        Map<User,Seat> sectionWiseUserSeatMap = new HashMap<>();
        for(Map.Entry<User,BookingReceipt> hm : userBookingReceiptMap.entrySet()){
            if(Objects.equals(hm.getValue().getSeat().getSection(), section)){
               sectionWiseUserSeatMap.put(hm.getKey(),hm.getValue().getSeat());
            }
        }
        return sectionWiseUserSeatMap;
    }
}

