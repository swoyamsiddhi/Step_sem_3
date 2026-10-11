package week7_abstract_interfaces.coding_assignment_b;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// ─── Q3: The Campus Premiere Ticket Counter ──────────────────────────────────

abstract class CinemaSeat {
    private final String seatId;
    public CinemaSeat(String seatId) { this.seatId = seatId; }
    public String getSeatId() { return seatId; }
    public abstract double getPrice();
    public abstract String category();
}

class RegularSeat extends CinemaSeat {
    public RegularSeat(String id) { super(id); }
    @Override public double getPrice() { return 150.0; }
    @Override public String category() { return "Regular"; }
}

class PremiumSeat extends CinemaSeat {
    public PremiumSeat(String id) { super(id); }
    @Override public double getPrice() { return 250.0; }
    @Override public String category() { return "Premium"; }
}

class ReclineRSeat extends CinemaSeat {
    public ReclineRSeat(String id) { super(id); }
    @Override public double getPrice() { return 400.0; }
    @Override public String category() { return "Recliner"; }
}

class ShowCustomer {
    private final String name;
    public ShowCustomer(String name) { this.name = name; }
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

    public boolean isSeatAvailable(String seatId) { return !bookedSeatIds.contains(seatId); }

    public void bookSeat(String seatId) { bookedSeatIds.add(seatId); }
    public void releaseSeat(String seatId) { bookedSeatIds.remove(seatId); }

    public boolean hasStarted() { return LocalDateTime.now().isAfter(startTime); }
    public String getName() { return name; }
    public LocalDateTime getStartTime() { return startTime; }
}

class CinemaBooking {
    private static final int MAX_SEATS = 6;

    private final ShowCustomer customer;
    private final Show show;
    private final List<CinemaSeat> seats;
    private boolean cancelled;

    public CinemaBooking(ShowCustomer customer, Show show, List<CinemaSeat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = new ArrayList<>(seats);
        this.cancelled = false;
    }

    public double total() {
        return seats.stream().mapToDouble(CinemaSeat::getPrice).sum();
    }

    public boolean cancel() {
        if (show.hasStarted()) {
            System.out.println("Cannot cancel after show has started.");
            return false;
        }
        for (CinemaSeat s : seats) show.releaseSeat(s.getSeatId());
        cancelled = true;
        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < seats.size(); i++) {
            ids.append(seats.get(i).getSeatId());
            if (i < seats.size() - 1) ids.append(", ");
        }
        System.out.println(customer.getName() + "'s booking cancelled. Seats " + ids + " released.");
        return true;
    }
}

public class CampusPremierTicketCounter {

    /** Attempts to book the given seats for a customer at a show. */
    static void book(ShowCustomer customer, Show show, List<CinemaSeat> requestedSeats) {
        // Validate seat count
        if (requestedSeats.size() > 6) {
            System.out.println("Cannot book more than 6 seats per booking.");
            return;
        }
        // Check availability
        List<CinemaSeat> available = new ArrayList<>();
        for (CinemaSeat s : requestedSeats) {
            if (!show.isSeatAvailable(s.getSeatId())) {
                System.out.println("Seat " + s.getSeatId() + " is already booked for this show.");
                return;
            }
            available.add(s);
        }
        // Reserve
        for (CinemaSeat s : available) show.bookSeat(s.getSeatId());
        CinemaBooking booking = new CinemaBooking(customer, show, available);

        StringBuilder ids = new StringBuilder();
        for (int i = 0; i < available.size(); i++) {
            ids.append(available.get(i).getSeatId());
            if (i < available.size() - 1) ids.append(", ");
        }
        System.out.printf("Booking confirmed for %s: %s. Total: ₹%.2f%n",
                customer.getName(), ids, booking.total());
    }

    public static void main(String[] args) {
        System.out.println("--- Q3: The Campus Premiere Ticket Counter ---");

        // Show starts far in the future so cancellations are allowed
        Show show7pm = new Show("7 PM Show", LocalDateTime.now().plusDays(1));

        ShowCustomer asha = new ShowCustomer("Asha");
        ShowCustomer ravi = new ShowCustomer("Ravi");
        ShowCustomer neha = new ShowCustomer("Neha");

        RegularSeat a1 = new RegularSeat("A1");
        RegularSeat a2 = new RegularSeat("A2");
        PremiumSeat f5 = new PremiumSeat("F5");
        ReclineRSeat r1 = new ReclineRSeat("R1");

        // Asha books A1, A2, F5
        List<CinemaSeat> ashaSeats = new ArrayList<>();
        ashaSeats.add(a1); ashaSeats.add(a2); ashaSeats.add(f5);
        CinemaBooking ashaBooking = null;
        if (ashaSeats.stream().allMatch(s -> show7pm.isSeatAvailable(s.getSeatId()))) {
            for (CinemaSeat s : ashaSeats) show7pm.bookSeat(s.getSeatId());
            ashaBooking = new CinemaBooking(asha, show7pm, ashaSeats);
            System.out.printf("Booking confirmed for %s: A1, A2, F5. Total: ₹%.2f%n",
                    asha.getName(), ashaBooking.total());
        }

        // Ravi tries A2 (already booked)
        if (!show7pm.isSeatAvailable("A2")) {
            System.out.println("Seat A2 is already booked for this show.");
        }

        // Ravi books R1
        List<CinemaSeat> raviSeats = new ArrayList<>();
        raviSeats.add(r1);
        book(ravi, show7pm, raviSeats);

        // Asha cancels
        if (ashaBooking != null) ashaBooking.cancel();

        // Neha books A2
        List<CinemaSeat> nehaSeats = new ArrayList<>();
        nehaSeats.add(new RegularSeat("A2"));
        book(neha, show7pm, nehaSeats);
    }
}
