package org.example.repositories;

import org.example.entities.Booking;

public class BookingRepo extends BaseRepo<Booking> {

    public BookingRepo(){
        super(Booking.class);
    }
}
