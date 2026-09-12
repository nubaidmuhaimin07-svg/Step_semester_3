class ParkingTicket {
    String vehicleNo;
    double ratePerMinute;
    ParkingTicket(String vehicleNo, double ratePerMinute) {
        this.vehicleNo = vehicleNo;
        this.ratePerMinute = ratePerMinute;
    }
    final double calculateFine(int minutes) {
        return minutes * ratePerMinute;
    }
    final void printReceipt(int minutes) {
        double fine = calculateFine(minutes);
        System.out.println(
                vehicleNo + " - Fine: Rs " + fine
        );
    }
    public static void main(String[] args) {

        String[] vehicles = {
                "TN09AB1234",
                "TN22CD5678",
                "TN09EF9012",
                "TN10GH3456"
        };
        double[] rates = {2, 2, 3, 2};
        int[] minutes = {15, 0, -5, 8};
        for (int i = 0; i < vehicles.length; i++) {
            ParkingTicket p =
                    new ParkingTicket(vehicles[i], rates[i]);
            if (minutes[i] > 0) {
                p.printReceipt(minutes[i]);
            } else {
                System.out.println(
                        vehicles[i]
                        + " - No fine, within allotted time"
                );
            }
        }
    }
}
