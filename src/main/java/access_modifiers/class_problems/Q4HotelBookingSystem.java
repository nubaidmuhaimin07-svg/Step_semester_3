import java.time.LocalDate;
import java.util.*;

abstract class Room {
    private final String roomNumber;
    public Room(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomNumber() { return roomNumber; }
    public abstract double calculatePrice(long nights);
}

class StandardRoom extends Room {
    public StandardRoom(String roomNumber) { super(roomNumber); }
    public double calculatePrice(long nights) { return nights * 100.0; }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) { super(roomNumber); }
    public double calculatePrice(long nights) { return nights * 160.0; }
}

class Suite extends Room {
    public Suite(String roomNumber) { super(roomNumber); }
    public double calculatePrice(long nights) { return nights * 250.0; }
}

class Customer {
    private final String name;
    public Customer(String name) { this.name = name; }
    public String getName() { return name; }
}

class Reservation {
    private final Customer customer;
    private final Room room;
    private final LocalDate checkIn;
    private final LocalDate checkOut;
    private final LocalDate cancellationDeadline;
    private boolean active = true;

    public Reservation(Customer customer, Room room, LocalDate checkIn, LocalDate checkOut, LocalDate cancellationDeadline) {
        if (checkIn == null || checkOut == null || !checkOut.isAfter(checkIn)) {
            throw new IllegalArgumentException("Check-out must be after check-in");
        }
        this.customer = customer;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.cancellationDeadline = cancellationDeadline;
    }

    public Room getRoom() { return room; }
    public LocalDate getCheckIn() { return checkIn; }
    public LocalDate getCheckOut() { return checkOut; }
    public boolean isActive() { return active; }
    public double getPrice() { return room.calculatePrice(java.time.temporal.ChronoUnit.DAYS.between(checkIn, checkOut)); }

    public boolean overlaps(LocalDate start, LocalDate end) {
        return active && start.isBefore(checkOut) && end.isAfter(checkIn);
    }

    public void cancel(LocalDate today) {
        if (!active) {
            System.out.println("Reservation is already inactive.");
        } else if (today.isAfter(cancellationDeadline)) {
            System.out.println("Cancellation deadline has passed.");
        } else {
            active = false;
            System.out.println("Reservation for " + customer.getName() + ", " + room.getRoomNumber()
                    + " cancelled successfully.");
        }
    }
}

class HotelBookingService {
    private final List<Reservation> reservations = new ArrayList<>();

    public boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.getRoom() == room && r.overlaps(start, end)) return false;
        }
        return true;
    }

    public void reserve(Customer customer, Room room, LocalDate start, LocalDate end, LocalDate deadline) {
        if (!isAvailable(room, start, end)) {
            System.out.println(room.getRoomNumber() + " is not available from " + start + " to " + end + ".");
            return;
        }
        Reservation r = new Reservation(customer, room, start, end, deadline);
        reservations.add(r);
        System.out.println("Reservation confirmed for " + customer.getName() + ", " + room.getRoomNumber()
                + " (" + start + " to " + end + "). Price: $" + String.format(Locale.US, "%.2f", r.getPrice()));
    }
}

public class Q4HotelBookingSystem {
    public static void main(String[] args) {
        HotelBookingService service = new HotelBookingService();
        Room standard = new StandardRoom("Standard Room 101");
        Room deluxe = new DeluxeRoom("Deluxe Room 201");
        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");
        LocalDate jan1 = LocalDate.of(2027, 1, 1);
        LocalDate jan5 = LocalDate.of(2027, 1, 5);
        System.out.println(standard.getRoomNumber() + (service.isAvailable(standard, jan1, jan5) ? " is available." : " is not available."));
        service.reserve(a, standard, jan1, jan5, LocalDate.of(2026, 12, 25));
        service.reserve(b, standard, LocalDate.of(2027, 1, 3), LocalDate.of(2027, 1, 7), LocalDate.of(2026, 12, 25));
        service.reserve(c, deluxe, LocalDate.of(2027, 2, 10), LocalDate.of(2027, 2, 12), LocalDate.of(2027, 2, 1));
    }
}
