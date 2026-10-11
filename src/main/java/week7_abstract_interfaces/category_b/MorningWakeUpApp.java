package week7_abstract_interfaces.category_b;

/**
 * Problem 1 – Morning Wake-Up Circuit
 * Runner: demonstrates ringAll() on a mixed array of Ringable devices.
 */
public class MorningWakeUpApp {

    /** Loops through every Ringable and prints its ring() message. */
    static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            System.out.println(device.ring());
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 1: Morning Wake-Up Circuit ---");

        AlarmClock a = new AlarmClock("7:00 AM");
        System.out.println(a.ring());

        Doorbell d = new Doorbell("Front Door");
        System.out.println(d.ring());

        System.out.println("--- ringAll ---");
        ringAll(new Ringable[]{a, d});
    }
}
