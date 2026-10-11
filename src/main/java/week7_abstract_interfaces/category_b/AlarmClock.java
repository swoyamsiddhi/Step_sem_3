package week7_abstract_interfaces.category_b;

/**
 * Problem 1 – Morning Wake-Up Circuit
 * No parent class other than Object; implements Ringable directly.
 */
public class AlarmClock implements Ringable {

    private final String time;

    public AlarmClock(String time) {
        this.time = time;
    }

    @Override
    public String ring() {
        return "Alarm ringing for " + time;
    }
}
