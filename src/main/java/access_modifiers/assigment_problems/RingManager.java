public class RingManager {
    public static void ringAll(Ringable[] devices) {
        for (Ringable device : devices) {
            if (device != null) {
                System.out.println(device.ring());
            }
        }
    }

    public static void main(String[] args) {
        Ringable[] devices = {
            new AlarmClock("7:00 AM"),
            new Doorbell("Front Door")
        };
        ringAll(devices);
    }
}
