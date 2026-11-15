import java.util.HashMap;
import java.util.Map;

public class Q2CampusVehiclePassSystem {
    interface Chargeable {
        boolean bookChargingBay();
    }

    static class Vehicle {
        String passNumber;
        String owner;
        String type;

        Vehicle(String passNumber, String owner, String type) {
            this.passNumber = passNumber;
            this.owner = owner;
            this.type = type;
        }

        int passFee() {
            if (type.equals("Bike") || type.equals("EBike")) return 300;
            return 1000;
        }

        String charge() {
            return passNumber + " rejected: charging unsupported";
        }
    }

    static class ElectricVehicle extends Vehicle implements Chargeable {
        ElectricVehicle(String passNumber, String owner, String type) {
            super(passNumber, owner, type);
        }

        public boolean bookChargingBay() {
            return true;
        }

        String charge() {
            return passNumber + " charging bay allotted";
        }
    }

    public static void main(String[] args) {
        Map<String, Vehicle> vehicles = new HashMap<>();

        Vehicle bike = new Vehicle("KA01", "Asha", "Bike");
        vehicles.put("KA01", bike);
        System.out.println("KA01 (Bike) pass fee " + bike.passFee());

        Vehicle car = new ElectricVehicle("KA02", "Ravi", "ECar");
        vehicles.put("KA02", car);
        System.out.println("KA02 (ECar) pass fee " + car.passFee());

        System.out.println(vehicles.get("KA02").charge());
        System.out.println(vehicles.get("KA01").charge());
    }
}
