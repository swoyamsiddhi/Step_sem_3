package week7_abstract_interfaces.category_b;

/**
 * Problem 5 – Skyline Delivery Fleet
 * Extends Drone AND implements Trackable.
 */
public class DeliveryDrone extends Drone implements Trackable {

    private final String id;

    public DeliveryDrone(String id) {
        super();
        this.id = id;
    }

    @Override
    public String fly() {
        return id + " flying on delivery route";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}
