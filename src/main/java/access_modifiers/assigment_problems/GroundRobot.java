public class GroundRobot implements Trackable {
    private final String id;

    public GroundRobot(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be blank");
        }
        this.id = id;
    }

    @Override
    public String getLocation() {
        return id + " at Sector 4";
    }
}
