import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

abstract class Seat {
    private final String seatId;
    public Seat(String seatId) { this.seatId = seatId; }
    public String getSeatId() { return seatId; }
    public abstract double getPrice();
}

class RegularSeat extends Seat {
    public RegularSeat(String id) { super(id); }
    public double getPrice() { return 150.0; }
}

class PremiumSeat extends Seat {
    public PremiumSeat(String id) { super(id); }
    public double getPrice() { return 250.0; }
}

class ReclinerSeat extends Seat {
    public ReclinerSeat(String id) { super(id); }
    public double getPrice() { return 400.0; }
}

class Customer {
    private final String name;
    public Customer(String name) { this.name = name; }
    public String getName() { return name; }
}

class Show {
    private final String name;
    private final LocalDateTime startTime;
    private final Set<String> bookedSeatIds = new HashSet<>();

    public Show(String name, LocalDateTime startTime) {
        this.name = name;
        this.startTime = startTime;
    }

    public String getName() { return name; }
    public LocalDateTime getStartTime() { return startTime; }
    public boolean isAvailable(Seat seat) { return !bookedSeatIds.contains(seat.getSeatId()); }
    public boolean reserveSeat(Seat seat) { return bookedSeatIds.add(seat.getSeatId()); }
    public void releaseSeat(Seat seat) { bookedSeatIds.remove(seat.getSeatId()); }
}

class Booking {
    private final Customer customer;
    private final Show show;
    private final List<Seat> seats;
    private boolean active = true;

    public Booking(Customer customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
    }

    public double getTotal() {
        double total = 0;
        for (Seat seat : seats) total += seat.getPrice();
        return total;
    }

    public void cancel(LocalDateTime now) {
        if (!active) {
            System.out.println("Booking is already cancelled.");
            return;
        }
        if (!now.isBefore(show.getStartTime())) {
            System.out.println("Cannot cancel: the show has already started.");
            return;
        }
        for (Seat seat : seats) show.releaseSeat(seat);
        active = false;
        StringJoiner ids = new StringJoiner(", ");
        for (Seat seat : seats) ids.add(seat.getSeatId());
        System.out.println(customer.getName() + "'s booking cancelled. Seats " + ids + " released.");
    }
}

class TicketBookingService {
    public Booking book(Customer customer, Show show, List<Seat> seats) {
        if (seats == null || seats.isEmpty() || seats.size() > 6) {
            System.out.println("A booking must contain between 1 and 6 seats.");
            return null;
        }
        Set<String> requested = new HashSet<>();
        for (Seat seat : seats) {
            if (!requested.add(seat.getSeatId()) || !show.isAvailable(seat)) {
                System.out.println("Seat " + seat.getSeatId() + " is already booked for this show.");
                return null;
            }
        }
        for (Seat seat : seats) show.reserveSeat(seat);
        Booking booking = new Booking(customer, show, seats);
        StringJoiner ids = new StringJoiner(", ");
        for (Seat seat : seats) ids.add(seat.getSeatId());
        System.out.printf(Locale.US, "Booking confirmed for %s: %s. Total: ₹%.2f%n",
                customer.getName(), ids, booking.getTotal());
        return booking;
    }
}

public class Q3CampusPremiereTicketCounter {
    public static void main(String[] args) {
        LocalDateTime showTime = LocalDateTime.of(2026, 10, 11, 19, 0);
        Show show = new Show("7 PM show", showTime);
        TicketBookingService service = new TicketBookingService();
        Customer asha = new Customer("Asha");
        Customer ravi = new Customer("Ravi");
        Customer neha = new Customer("Neha");

        Booking ashaBooking = service.book(asha, show, Arrays.asList(
                new RegularSeat("A1"), new RegularSeat("A2"), new PremiumSeat("F5")));
        service.book(ravi, show, Arrays.asList(new RegularSeat("A2")));
        service.book(ravi, show, Arrays.asList(new ReclinerSeat("R1")));
        if (ashaBooking != null) ashaBooking.cancel(LocalDateTime.of(2026, 10, 11, 18, 0));
        service.book(neha, show, Arrays.asList(new RegularSeat("A2")));
    }
}
