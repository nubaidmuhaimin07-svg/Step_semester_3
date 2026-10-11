enum MembershipStatus { ACTIVE, FROZEN, EXPIRED }

interface MembershipPlan {
    String getName();
    int getMonths();
    double calculateFee(double monthlyBaseRate);
}

class MonthlyPlan implements MembershipPlan {
    public String getName() { return "Monthly"; }
    public int getMonths() { return 1; }
    public double calculateFee(double rate) { return rate; }
}

class QuarterlyPlan implements MembershipPlan {
    public String getName() { return "Quarterly"; }
    public int getMonths() { return 3; }
    public double calculateFee(double rate) { return rate * 3 * 0.90; }
}

class AnnualPlan implements MembershipPlan {
    public String getName() { return "Annual"; }
    public int getMonths() { return 12; }
    public double calculateFee(double rate) { return rate * 12 * 0.75; }
}

class Member {
    private final String name;
    private final Membership membership;

    public Member(String name, MembershipPlan plan) {
        this.name = name;
        membership = new Membership(name, plan);
    }

    public String getName() { return name; }
    public Membership getMembership() { return membership; }
}

class Membership {
    private static final double BASE_RATE = 1000.0;
    private final String memberName;
    private final MembershipPlan plan;
    private MembershipStatus status = MembershipStatus.ACTIVE;

    public Membership(String memberName, MembershipPlan plan) {
        this.memberName = memberName;
        this.plan = plan;
        System.out.printf(java.util.Locale.US, "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                plan.getName(), memberName, plan.calculateFee(BASE_RATE));
    }

    public MembershipStatus getStatus() { return status; }

    public void checkIn() {
        if (status == MembershipStatus.ACTIVE) {
            System.out.println(memberName + " checked in successfully.");
        } else {
            System.out.println("Check-in denied: " + memberName + "'s membership is " + titleCase(status) + ".");
        }
    }

    public void freeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
        } else if (status == MembershipStatus.FROZEN) {
            System.out.println(memberName + "'s membership is already Frozen.");
        } else {
            status = MembershipStatus.FROZEN;
            System.out.println(memberName + "'s membership frozen. Status: Frozen.");
        }
    }

    public void unfreeze() {
        if (status == MembershipStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
        } else if (status == MembershipStatus.FROZEN) {
            status = MembershipStatus.ACTIVE;
            System.out.println(memberName + "'s membership unfrozen. Status: Active.");
        }
    }

    public void expire() {
        status = MembershipStatus.EXPIRED;
        System.out.println(memberName + "'s membership expired. Status: Expired.");
    }

    private String titleCase(MembershipStatus s) {
        String value = s.toString().toLowerCase();
        return Character.toUpperCase(value.charAt(0)) + value.substring(1);
    }
}

public class Q4FitZoneMembershipDesk {
    public static void main(String[] args) {
        Member asha = new Member("Asha", new QuarterlyPlan());
        Member ravi = new Member("Ravi", new MonthlyPlan());
        asha.getMembership().checkIn();
        asha.getMembership().freeze();
        asha.getMembership().checkIn();
        ravi.getMembership().expire();
        ravi.getMembership().freeze();
    }
}
