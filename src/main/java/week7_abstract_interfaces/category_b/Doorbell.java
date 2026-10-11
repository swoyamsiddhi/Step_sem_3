package week7_abstract_interfaces.category_b;

/**
 * Problem 1 – Morning Wake-Up Circuit
 * No parent class other than Object; implements Ringable directly.
 */
public class Doorbell implements Ringable {

    private final String location;

    public Doorbell(String location) {
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}
