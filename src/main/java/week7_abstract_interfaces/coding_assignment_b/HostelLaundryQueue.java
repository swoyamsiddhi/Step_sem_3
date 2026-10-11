package week7_abstract_interfaces.coding_assignment_b;

// ─── Q1: The Hostel Laundry Queue ───────────────────────────────────────────

/**
 * Defines the contract every wash type must fulfil.
 * Adding a new wash type (e.g., Delicate) requires zero changes here.
 */
interface WashType {
    String typeName();
    int durationMinutes();
    double charge();
}

class QuickWash implements WashType {
    @Override public String typeName() { return "Quick"; }
    @Override public int durationMinutes() { return 30; }
    @Override public double charge() { return 20.0; }
}

class NormalWash implements WashType {
    @Override public String typeName() { return "Normal"; }
    @Override public int durationMinutes() { return 45; }
    @Override public double charge() { return 30.0; }
}

class HeavyWash implements WashType {
    @Override public String typeName() { return "Heavy"; }
    @Override public int durationMinutes() { return 60; }
    @Override public double charge() { return 45.0; }
}

class LaundryStudent {
    private final String name;
    public LaundryStudent(String name) { this.name = name; }
    public String getName() { return name; }
}

class WashCycle {
    private final LaundryStudent student;
    private final WashingMachine machine;
    private final WashType washType;

    WashCycle(LaundryStudent student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    void complete() {
        machine.freeMachine();
        System.out.println(machine.getId() + " cycle completed. " + machine.getId() + " is now free.");
    }

    public String summary() {
        return String.format("%s wash started on %s for %s (%d min). Charge: ₹%.2f",
                washType.typeName(), machine.getId(), student.getName(),
                washType.durationMinutes(), washType.charge());
    }
}

class WashingMachine {
    private final String id;
    private boolean free;

    public WashingMachine(String id) {
        this.id = id;
        this.free = true;
    }

    public String getId() { return id; }

    /** Returns a WashCycle if the machine is free, null otherwise. */
    public WashCycle startWash(LaundryStudent student, WashType washType) {
        if (!free) {
            System.out.println("Machine " + id + " is currently busy.");
            return null;
        }
        free = false;
        WashCycle cycle = new WashCycle(student, this, washType);
        System.out.println(cycle.summary());
        return cycle;
    }

    void freeMachine() { this.free = true; }
}

public class HostelLaundryQueue {

    public static void main(String[] args) {
        System.out.println("--- Q1: The Hostel Laundry Queue ---");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        LaundryStudent asha = new LaundryStudent("Asha");
        LaundryStudent ravi = new LaundryStudent("Ravi");
        LaundryStudent neha = new LaundryStudent("Neha");

        // Asha starts Quick on M1
        WashCycle c1 = m1.startWash(asha, new QuickWash());

        // Ravi tries Heavy on M1 (busy)
        m1.startWash(ravi, new HeavyWash());

        // Ravi starts Heavy on M2
        m2.startWash(ravi, new HeavyWash());

        // M1 completes
        if (c1 != null) c1.complete();

        // Neha starts Normal on M1
        m1.startWash(neha, new NormalWash());
    }
}
