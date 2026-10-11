package week7_abstract_interfaces.coding_questions;

import java.util.ArrayList;
import java.util.List;

// ─── Q1: Vehicle Rental System ─────────────────────────────────────────────

/**
 * Abstract vehicle — each subclass defines its own daily rate.
 */
abstract class Vehicle {
    private final String vehicleId;
    private boolean available;

    public Vehicle(String vehicleId) {
        this.vehicleId = vehicleId;
        this.available = true;
    }

    public String getVehicleId() { return vehicleId; }
    public boolean isAvailable() { return available; }
    void setAvailable(boolean available) { this.available = available; }

    public abstract double dailyRate();
    public abstract String type();
}

class Sedan extends Vehicle {
    public Sedan(String id) { super(id); }
    @Override public double dailyRate() { return 50.0; }
    @Override public String type() { return "Sedan"; }
}

class SUV extends Vehicle {
    public SUV(String id) { super(id); }
    @Override public double dailyRate() { return 80.0; }
    @Override public String type() { return "SUV"; }
}

class Truck extends Vehicle {
    public Truck(String id) { super(id); }
    @Override public double dailyRate() { return 120.0; }
    @Override public String type() { return "Truck"; }
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
    private boolean active;

    public Rental(Customer customer, Vehicle vehicle, int days) {
        this.customer = customer;
        this.vehicle = vehicle;
        this.days = days;
        this.active = true;
        vehicle.setAvailable(false);
    }

    public double charge() { return vehicle.dailyRate() * days; }
    public Customer getCustomer() { return customer; }
    public Vehicle getVehicle() { return vehicle; }

    public void returnVehicle() {
        active = false;
        vehicle.setAvailable(true);
    }
    public boolean isActive() { return active; }
}

public class VehicleRentalSystem {

    public static void main(String[] args) {
        System.out.println("--- Q1: Vehicle Rental System ---");
        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Sedan sedanA = new Sedan("Sedan A");
        SUV suvB = new SUV("SUV B");

        // C1 rents Sedan A
        if (sedanA.isAvailable()) {
            Rental r1 = new Rental(c1, sedanA, 3);
            System.out.printf("%s rented successfully by %s. Rental charge: $%.1f%n",
                    sedanA.getVehicleId(), c1.getName(), r1.charge());

            // C2 tries to rent Sedan A while active
            if (sedanA.isAvailable()) {
                System.out.println(sedanA.getVehicleId() + " rented by " + c2.getName());
            } else {
                System.out.println(sedanA.getVehicleId() + " is currently unavailable.");
            }

            // C1 returns Sedan A
            r1.returnVehicle();
            System.out.println(sedanA.getVehicleId() + " returned by " + c1.getName() + ".");
        }

        // C3 rents SUV B
        if (suvB.isAvailable()) {
            Rental r3 = new Rental(c3, suvB, 5);
            System.out.printf("%s rented successfully by %s. Rental charge: $%.1f%n",
                    suvB.getVehicleId(), c3.getName(), r3.charge());
        }
    }
}
