public class Q5PayrollRegisterSystem {
    static class Payslip {
        String name;
        String type;
        int pay;

        Payslip(String name, String type, int pay) {
            this.name = name;
            this.type = type;
            this.pay = pay;
        }

        public String toString() {
            return "Payslip[name=" + name + ", type=" + type + ", pay=" + pay + "]";
        }
    }

    public static void main(String[] args) {
        Payslip[] payslips = {
            new Payslip("Asha", "FullTime", 50000),
            new Payslip("Ravi", "PartTime", 80 * 300),
            new Payslip("Neha", "Intern", 15000)
        };

        int total = 0;
        Payslip topEarner = payslips[0];

        for (Payslip slip : payslips) {
            System.out.println(slip);
            total += slip.pay;
            if (slip.pay > topEarner.pay) topEarner = slip;
        }

        System.out.println("Total " + total);
        System.out.println("top earner " + topEarner.name);
    }
}
