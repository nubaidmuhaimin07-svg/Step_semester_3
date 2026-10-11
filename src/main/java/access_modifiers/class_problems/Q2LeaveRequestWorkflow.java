import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Employee {
    private final String name;
    public Employee(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract boolean isLeaveDurationAllowed(long days);
}

class FullTimeEmployee extends Employee {
    public FullTimeEmployee(String name) { super(name); }
    public boolean isLeaveDurationAllowed(long days) { return days > 0 && days <= 30; }
}

class PartTimeEmployee extends Employee {
    public PartTimeEmployee(String name) { super(name); }
    public boolean isLeaveDurationAllowed(long days) { return days > 0 && days <= 10; }
}

class Contractor extends Employee {
    public Contractor(String name) { super(name); }
    public boolean isLeaveDurationAllowed(long days) { return days > 0 && days <= 5; }
}

enum LeaveStatus { PENDING, APPROVED, REJECTED }

class LeaveRequest {
    private final Employee employee;
    private final LocalDate start;
    private final LocalDate end;
    private LeaveStatus status = LeaveStatus.PENDING;

    public LeaveRequest(Employee employee, LocalDate start, LocalDate end) {
        if (start == null || end == null || end.isBefore(start)) {
            throw new IllegalArgumentException("Invalid leave dates");
        }
        long days = ChronoUnit.DAYS.between(start, end) + 1;
        if (!employee.isLeaveDurationAllowed(days)) {
            throw new IllegalArgumentException("Leave duration not allowed for this employee type");
        }
        this.employee = employee;
        this.start = start;
        this.end = end;
    }

    public LeaveStatus getStatus() { return status; }

    public void review(boolean approve, String reviewer) {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Request has already been reviewed.");
            return;
        }
        status = approve ? LeaveStatus.APPROVED : LeaveStatus.REJECTED;
        String action = approve ? "approved" : "rejected";
        System.out.println(employee.getName() + "'s leave request (" + start + " to " + end + ") " + action + " by " + reviewer + ".");
        System.out.println("Status: " + status);
    }

    public void attemptStatusChange(LeaveStatus newStatus) {
        if (status != LeaveStatus.PENDING && newStatus == LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status + " to Pending.");
            return;
        }
        status = newStatus;
    }
}

public class Q2LeaveRequestWorkflow {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        LeaveRequest r1 = new LeaveRequest(john, LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        System.out.println("Leave request submitted for John (Jan 1-5). Status: " + r1.getStatus());
        r1.review(true, "Alice");
        r1.attemptStatusChange(LeaveStatus.PENDING);

        Employee jane = new PartTimeEmployee("Jane");
        LeaveRequest r2 = new LeaveRequest(jane, LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));
        System.out.println("Leave request submitted for Jane (Feb 10-11). Status: " + r2.getStatus());
        r2.review(false, "Bob");
    }
}
