package week7_abstract_interfaces.category_b;

/**
 * Problem 4 – Digital Classroom Setup
 * Extends ClassroomDevice AND implements Chargeable (both charge() overloads).
 */
public class Tablet extends ClassroomDevice implements Chargeable {

    private final String assetTag;

    public Tablet(String assetTag) {
        super();
        this.assetTag = assetTag;
    }

    @Override
    public String operate() {
        return "Tablet " + assetTag + " displaying lesson";
    }

    @Override
    public String charge() {
        return assetTag + " charging";
    }

    @Override
    public String charge(int minutes) {
        return assetTag + " charging for " + minutes + " minutes";
    }
}
