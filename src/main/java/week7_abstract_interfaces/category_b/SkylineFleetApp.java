package week7_abstract_interfaces.category_b;

/**
 * Problem 5 – Skyline Delivery Fleet
 * Runner: demonstrates instanceof check and safe cast to Trackable.
 */
public class SkylineFleetApp {

    /**
     * Accepts any Object; if it implements Trackable, calls getLocation().
     * Otherwise reports that tracking is unavailable.
     */
    static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            return ((Trackable) o).getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        System.out.println("--- Problem 5: Skyline Delivery Fleet ---");

        DeliveryDrone d = new DeliveryDrone("DR-1");
        System.out.println(getLocationIfTrackable(d));   // trackable

        ScoutDrone s = new ScoutDrone("SC-1");
        System.out.println(getLocationIfTrackable(s));   // NOT trackable

        GroundRobot g = new GroundRobot("GR-1");
        System.out.println(getLocationIfTrackable(g));   // trackable, no Drone ancestry
    }
}
