public class FleetTracker {
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            return ((Trackable) o).getLocation();
        }
        return "Tracking not available";
    }

    public static void main(String[] args) {
        System.out.println(getLocationIfTrackable(new DeliveryDrone("DR-1")));
        System.out.println(getLocationIfTrackable(new ScoutDrone("SC-1")));
        System.out.println(getLocationIfTrackable(new GroundRobot("GR-1")));
    }
}
