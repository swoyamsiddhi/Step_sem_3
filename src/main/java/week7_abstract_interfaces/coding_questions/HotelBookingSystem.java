package week7_abstract_interfaces.coding_questions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

// ─── Q4: Hotel Booking System ────────────────────────────────────────────────

abstract class Room {
    private final String roomNumber;
    private final List<HotelReservation> reservations = new ArrayList<>();

    public Room(String roomNumber) { this.roomNumber = roomNumber; }
    public String getRoomNumber() { return roomNumber; }

    public abstract double pricePerNight();
    public abstract String category();

    public boolean isAvailable(LocalDate from, LocalDate to) {
        for (HotelReservation r : reservations) {
            if (r.isActive() && r.overlaps(from, to)) return false;
        }
        return true;
    }

    void addReservation(HotelReservation r) { reservations.add(r); }
    void removeReservation(HotelReservation r) { reservations.remove(r); }
}

class StandardRoom extends Room {
    public StandardRoom(String number) { super(number); }
    @Override public double pricePerNight() { return 100.0; }
    @Override public String category() { return "Standard"; }
}

class DeluxeRoom extends Room {
    public DeluxeRoom(String number) { super(number); }
    @Override public double pricePerNight() { return 180.0; }
    @Override public String category() { return "Deluxe"; }
}

class Suite extends Room {
    public Suite(String number) { super(number); }
    @Override public double pricePerNight() { return 300.0; }
    @Override public String category() { return "Suite"; }
}

class HotelCustomer {
    private final String name;
    public HotelCustomer(String name) { this.name = name; }
    public String getName() { return name; }
}

class HotelReservation {
    private final HotelCustomer customer;
    private final Room room;
    private final LocalDate from;
    private final LocalDate to;
    private boolean active;

    public HotelReservation(HotelCustomer customer, Room room, LocalDate from, LocalDate to) {
        this.customer = customer;
        this.room = room;
        this.from = from;
        this.to = to;
        this.active = true;
        room.addReservation(this);
    }

    public boolean overlaps(LocalDate f, LocalDate t) {
        return !t.isBefore(from) && !f.isAfter(to);
    }

    public double totalPrice() {
        long nights = ChronoUnit.DAYS.between(from, to);
        return nights * room.pricePerNight();
    }

    public boolean isActive() { return active; }

    /** Cancel before show-start (here: before from date). */
    public boolean cancel(LocalDate today) {
        if (!today.isBefore(from)) {
            System.out.println("Cancellation deadline passed for " + room.getRoomNumber() + ".");
            return false;
        }
        active = false;
        room.removeReservation(this);
        System.out.printf("Reservation for %s, %s (%s-%s) cancelled successfully.%n",
                customer.getName(), room.getRoomNumber(), from, to);
        return true;
    }
}

public class HotelBookingSystem {

    public static void main(String[] args) {
        System.out.println("--- Q4: Hotel Booking System ---");

        StandardRoom r101 = new StandardRoom("101");
        DeluxeRoom r201 = new DeluxeRoom("201");

        HotelCustomer cA = new HotelCustomer("Customer A");
        HotelCustomer cB = new HotelCustomer("Customer B");
        HotelCustomer cC = new HotelCustomer("Customer C");

        LocalDate jan1 = LocalDate.of(2026, 1, 1);
        LocalDate jan5 = LocalDate.of(2026, 1, 5);
        LocalDate jan3 = LocalDate.of(2026, 1, 3);
        LocalDate jan7 = LocalDate.of(2026, 1, 7);
        LocalDate feb10 = LocalDate.of(2026, 2, 10);
        LocalDate feb12 = LocalDate.of(2026, 2, 12);

        // A checks and books
        if (r101.isAvailable(jan1, jan5)) {
            System.out.println("Standard Room 101 is available from Jan 1 to Jan 5.");
            HotelReservation resA = new HotelReservation(cA, r101, jan1, jan5);
            System.out.printf("Reservation confirmed for %s, Standard Room 101 (Jan 1-5). Price: $%.1f%n",
                    cA.getName(), resA.totalPrice());

            // B tries overlapping
            if (!r101.isAvailable(jan3, jan7)) {
                System.out.println("Standard Room 101 is not available from Jan 3 to Jan 7.");
            }

            // A cancels before from date (today = Dec 31)
            resA.cancel(LocalDate.of(2025, 12, 31));
        }

        // C books Deluxe
        if (r201.isAvailable(feb10, feb12)) {
            HotelReservation resC = new HotelReservation(cC, r201, feb10, feb12);
            System.out.printf("Reservation confirmed for %s, Deluxe Room 201 (Feb 10-12). Price: $%.1f%n",
                    cC.getName(), resC.totalPrice());
        }
    }
}
