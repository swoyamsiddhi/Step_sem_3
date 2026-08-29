package week3_oop.assigment_problems;

/**
 * M4 — Reference Copies and a Shared Exam Hall Ticket
 *
 * Demonstrates that assigning one reference variable to another does
 * NOT create a new object — both variables point to the same object
 * in memory. Also contrasts == (identity) with content equality.
 */
public class HallTicket {

    // ── Fields ──
    String studentName;
    int seatNumber;

    // ── Constructor ──
    HallTicket(String studentName, int seatNumber) {
        this.studentName = studentName;
        this.seatNumber = seatNumber;
    }

    // ── Driver ──
    public static void main(String[] args) {

        // 1. Create one object
        HallTicket priya = new HallTicket("Priya", 0);

        // 2. Assign a second variable to the SAME object (reference copy)
        HallTicket copy = priya;

        // 3. Mutate through the second variable
        copy.seatNumber = 45;

        // 4. Read through the FIRST variable — proves they share the same object
        System.out.println("Priya's seatNumber (via first variable): " + priya.seatNumber);

        // 5. Identity check — same object in memory
        System.out.println("copy == priya: " + (copy == priya));

        // 6. Create a SEPARATE object with identical field values
        HallTicket separate = new HallTicket("Priya", 45);

        // 7. Identity check — different object even though content matches
        System.out.println("separate == priya: " + (separate == priya));
    }
}
