package week7_abstract_interfaces.category_b;

/**
 * Problem 5 – Skyline Delivery Fleet
 * Extends Drone but does NOT implement Trackable — intentionally not trackable.
 */
public class ScoutDrone extends Drone {

    private final String id;

    public ScoutDrone(String id) {
        super();
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " flying on scout mission";
    }
}
