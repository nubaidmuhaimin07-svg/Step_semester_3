public abstract class Drone {
    protected final String id;

    public Drone(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be blank");
        }
        this.id = id;
    }

    public abstract String fly();
}
