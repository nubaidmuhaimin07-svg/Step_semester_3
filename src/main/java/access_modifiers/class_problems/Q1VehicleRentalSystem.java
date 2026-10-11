import java.util.*;

abstract class Vehicle {
    private final String id;
    private boolean available = true;

    public Vehicle(String id) {
        this.id = id;
    }

    public String getId() { return id; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    public Sedan(String id) { super(id); }
    public double calculateCharge(int days) { return days * 50.0; }
}

class SUV extends Vehicle {
    public SUV(String id) { super(id); }
    public double calculateCharge(int days) { return days * 80.0; }
}

class Truck extends Vehicle {
    public Truck(String id) { super(id); }
    public double calculateCharge(int days) { return days * 120.0; }
}

class Customer {
    private final String name;
    public Customer(String name) { this.name = name; }
    public String getName() { return name; }
}

class Rental {
    private final Customer customer;
    private final Vehicle vehicle;
    private final int days;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
    }

    public Vehicle getVehicle() { return vehicle; }
    public Customer getCustomer() { return customer; }
    public double getCharge() { return vehicle.calculateCharge(days); }
}

class RentalService {
    private final List<Rental> activeRentals = new ArrayList<>();

    public void rent(Customer customer, Vehicle vehicle, int days) {
        if (days <= 0) {
            System.out.println("Rental duration must be positive.");
            return;
        }
        if (!vehicle.isAvailable()) {
            System.out.println(vehicle.getId() + " is currently unavailable.");
            return;
        }
        Rental rental = new Rental(customer, vehicle, days);
        activeRentals.add(rental);
        vehicle.setAvailable(false);
        System.out.println(vehicle.getId() + " rented successfully by " + customer.getName() + ".");
        System.out.printf(Locale.US, "Rental charge: $%.2f%n", rental.getCharge());
    }

    public void returnVehicle(Customer customer, Vehicle vehicle) {
        for (int i = 0; i < activeRentals.size(); i++) {
            Rental rental = activeRentals.get(i);
            if (rental.getVehicle() == vehicle && rental.getCustomer() == customer) {
                activeRentals.remove(i);
                vehicle.setAvailable(true);
                System.out.println(vehicle.getId() + " returned by " + customer.getName() + ".");
                return;
            }
        }
        System.out.println("No active rental found for " + vehicle.getId() + " by " + customer.getName() + ".");
    }
}

public class Q1VehicleRentalSystem {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");
        RentalService service = new RentalService();

        service.rent(c1, sedan, 3);
        service.rent(c2, sedan, 2);
        service.returnVehicle(c1, sedan);
        service.rent(c3, suv, 5);
    }
}
