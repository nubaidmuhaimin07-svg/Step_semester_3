import java.util.Locale;

interface WashType {
    String getName();
    int getDurationMinutes();
    double getCharge();
}

class QuickWash implements WashType {
    public String getName() { return "Quick"; }
    public int getDurationMinutes() { return 30; }
    public double getCharge() { return 20.0; }
}

class NormalWash implements WashType {
    public String getName() { return "Normal"; }
    public int getDurationMinutes() { return 45; }
    public double getCharge() { return 30.0; }
}

class HeavyWash implements WashType {
    public String getName() { return "Heavy"; }
    public int getDurationMinutes() { return 60; }
    public double getCharge() { return 45.0; }
}

class Student {
    private final String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}

class WashCycle {
    private final Student student;
    private final WashingMachine machine;
    private final WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() { return student; }
    public WashingMachine getMachine() { return machine; }
    public WashType getWashType() { return washType; }
}

class WashingMachine {
    private final String id;
    private WashCycle currentCycle;

    public WashingMachine(String id) { this.id = id; }
    public String getId() { return id; }
    public boolean isFree() { return currentCycle == null; }

    public boolean startWash(Student student, WashType type) {
        if (!isFree()) {
            System.out.println("Machine " + id + " is currently busy.");
            return false;
        }
        currentCycle = new WashCycle(student, this, type);
        System.out.println(type.getName() + " wash started on " + id + " for " + student.getName()
                + " (" + type.getDurationMinutes() + " min).");
        System.out.printf(Locale.US, "Charge: ₹%.2f%n", type.getCharge());
        return true;
    }

    public void completeCycle() {
        if (currentCycle == null) {
            System.out.println(id + " has no active wash cycle.");
            return;
        }
        System.out.println(id + " cycle completed.");
        currentCycle = null;
        System.out.println(id + " is now free.");
    }
}

public class Q1HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());
        m1.completeCycle();
        m1.startWash(neha, new NormalWash());
    }
}
