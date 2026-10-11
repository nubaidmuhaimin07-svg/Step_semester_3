public class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    protected void chargeFine(int amount) {
        super.chargeFine(amount / 2);
    }

    @Override
    public void displayInfo() {
        System.out.println("Student Member | Course: " + course
                + " | Books Borrowed: " + getBooksBorrowed());
    }
}
