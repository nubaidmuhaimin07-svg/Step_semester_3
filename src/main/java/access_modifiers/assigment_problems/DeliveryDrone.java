public class DeliveryDrone extends Drone implements Trackable {
    public DeliveryDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Delivery drone " + id + " is flying";
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}
