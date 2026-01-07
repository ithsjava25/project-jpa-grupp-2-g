package backend.repositories;

import backend.entities.Booking;

public class BookingRepo extends BaseRepo<Booking> {

    public BookingRepo(){
        super(Booking.class);
    }
}
