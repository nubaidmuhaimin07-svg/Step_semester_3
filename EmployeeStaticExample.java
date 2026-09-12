public class EmployeeStaticExample {
    String empName;
    double salary;
    static String companyName =
            "Bright Horizon Technologies";
    static int employeeCount = 0
    EmployeeStaticExample(String empName, double salary) {
        this.empName = empName;
        this.salary = salary;
        employeeCount++;
    }
    static void printCompanyInfo() {
        System.out.println(companyName);
        System.out.println(
                "Employees on record: " + employeeCount
        );
    }
    public static void main(String[] args) {
        new EmployeeStaticExample("Divya", 65000);
        new EmployeeStaticExample("Arjun", 50000);
        new EmployeeStaticExample("Priya", 55000);
        EmployeeStaticExample.printCompanyInfo();
    }
}
