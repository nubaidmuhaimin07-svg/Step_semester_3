public class GroupClassMember extends GymMember {
    private final String className;

    public GroupClassMember(String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);
        this.className = className;
    }

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);
        this.className = className;
    }

    @Override
    public void displayInfo() {
        System.out.println("Group Class Member | Class: " + className
                + " | Sessions: " + getSessionsAttended());
    }

    public String getClassName() {
        return className;
    }
}
