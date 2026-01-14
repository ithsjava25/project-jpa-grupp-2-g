package backend.services;

import backend.entities.DiningTable;
import backend.entities.Booking;
import backend.entities.Customer;
import backend.entities.Restaurant;
import backend.factories.BookingFactory;
import backend.repositories.BookingRepo;
import backend.repositories.DiningTableRepo;
import backend.repositories.RestaurantRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Service responsible for handling table bookings.
 *
 * Creates customers if needed, checks table availability,
 * prevents double bookings, and saves the booking.
 */

public class BookingService {
/**
 * Books a table at a restaurant for a specific date and time.
 *
 * Validates input, finds an available table with enough capacity,
 * prevents double bookings, creates the booking, and saves it.
 */

    private final BookingRepo bookingRepo = new BookingRepo();
    private final DiningTableRepo diningTableRepo = new DiningTableRepo();
    private final BookingFactory bookingFactory = new BookingFactory();
    private final CustomerService customerService = new CustomerService();
    private final RestaurantRepo restaurantRepo = new RestaurantRepo();


    public Booking bookTable(
        Restaurant restaurant,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        int numberOfGuests,
        LocalTime startTime,
        LocalTime endTime,
        LocalDate date) {


        if (restaurant == null)
            throw new IllegalArgumentException("Restaurant is required");

        if (numberOfGuests <= 0)
            throw new IllegalArgumentException("Invalid number of guests");

        if (startTime == null || endTime == null || !startTime.isBefore(endTime))
            throw new IllegalArgumentException("Invalid booking time");




        Customer customer = customerService.createOrFetchCustomer(
            firstName,
            lastName,
            phoneNumber,
            email
        );



//-----Våran validering för att inte dubbelboka----

        List<DiningTable> candidateTables =      //Lista över alla "passande" bord som kan hålla antal gäster.
            diningTableRepo.fetchByRestaurantAndCapacity(
                restaurant, numberOfGuests
            );

        if (candidateTables.isEmpty())
            throw new IllegalStateException("No tables with sufficient capacity");


        DiningTable selectedTable = null;

        List<Integer> wantedBookingHours = new ArrayList<>();//Jag gjorde det lite enkelt nu med bara Hours. varje Hour blir en int. 1-24
        for(int i = startTime.getHour(); i < endTime.getHour(); i++) {//tar alla int/Hours från start till end som kund anger. Beroende på hur lång bokningstid vi ska gha.
            wantedBookingHours.add(i);
        }

        for (DiningTable table : candidateTables) {
            List<Integer> bookedHoursPerTable = new ArrayList<>(); //Listan som vi ska använda för att matcha mot vår wantedBookingHours

            List<Booking> bookingsForTable = bookingRepo.fetchAllBookingsByTableIdAndDate(table.getId(), date);

            for(Booking booking : bookingsForTable) {//kollar igenom bord i candidateTables och vilka "hours" som finns i listan.
                int start = booking.getBookingStart().getHour();
                int end = booking.getBookingEnd().getHour();

                for(int i = start; i < end; i++) {//samma logik som föregående och plockar alla hours mellan start och end
                    bookedHoursPerTable.add(i);
                }
            }

            boolean hasConflict =//validerings variable som kollar igenom alla hours
                wantedBookingHours.stream()
                    .anyMatch(bookedHoursPerTable::contains);

            if(!hasConflict){//om Hours i wantedbookingHours inte finns BookedHoursPerTeble så lägg till i vår tableToBook
                selectedTable = table;
                break;
            }
        }

        if(selectedTable == null){//om den inte lyckats hitta någon ledig Table.
            throw new IllegalStateException("No tables available during these hours");
        }


        Booking booking = bookingFactory.createBooking(
            restaurant,
            customer,
            selectedTable.getId(),//här är vår table
            startTime,
            endTime,
            date
        );


        bookingRepo.add(booking);
        return booking;

    }

}
