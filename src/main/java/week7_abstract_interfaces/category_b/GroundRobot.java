package week7_abstract_interfaces.category_b;

/**
 * Problem 5 – Skyline Delivery Fleet
 * NO relationship to Drone at all; implements only Trackable.
 */
public class GroundRobot implements Trackable {

    private final String id;

    public GroundRobot(String id) {
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}
