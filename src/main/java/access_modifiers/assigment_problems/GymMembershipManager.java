public class GymMembershipManager {
    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        }
        if (member instanceof PremiumMember) {
            return "Second generation (PremiumMember)";
        }
        return "General Member";
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;
        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }
        return total;
    }

    public static String batchPrint(GymMember[] members) {
        StringBuilder report = new StringBuilder();

        for (GymMember member : members) {
            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;
                report.append(member instanceof EliteMember ? "Elite" : "Premium")
                      .append(" | Trainer: ").append(premium.getTrainerName())
                      .append(" | Sessions: ").append(member.getSessionsAttended())
                      .append(" [Trainer via downcast: ")
                      .append(premium.getTrainerName()).append("] | ");
            } else {
                report.append("Standard | Sessions: ")
                      .append(member.getSessionsAttended()).append(" | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        GymMember standard = new GymMember("MEM1", 1000);
        PremiumMember premium = new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite = new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group = new GroupClassMember("MEM4", 1500, "Zumba");

        standard.attendSession();
        premium.attendSession();
        premium.attendSession();
        premium.attendSession();
        elite.attendSession();
        elite.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();
        group.attendSession();

        standard.displayInfo();
        premium.displayInfo();
        elite.displayInfo();
        group.displayInfo();

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));
        System.out.println(getTotalSessionsAttended(
                new GymMember[] {premium, elite, group}));

        System.out.println(batchPrint(new GymMember[] {standard, premium}));
        System.out.println(GymMember.signUpBatch(
                new String[] {"MEM1", "GM1", "MEM2", " ", "MEM3"}, 1000));

        System.out.println(GymMember.isValidReferralCode("G45B"));
        System.out.println(GymMember.processWeeklyCheckIn(
                new GymMember[] {new GroupClassMember(1500, "Zumba"),
                                 null, new GymMember(1000)}));
    }
}
