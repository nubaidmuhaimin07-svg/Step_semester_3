public class MembershipManager {
    public static String classifyGeneration(LibraryMember member) {
        if (member instanceof HonorsStudentMember) {
            return "Multilevel descendant (3 generations deep)";
        }
        if (member instanceof FacultyMember) {
            return "Hierarchical sibling (independent branch)";
        }
        if (member instanceof StudentMember) {
            return "Direct subclass (2 generations)";
        }
        return "Base member";
    }

    public static int getTotalBooksBorrowed(LibraryMember[] members) {
        int total = 0;
        for (LibraryMember member : members) {
            total += member.getBooksBorrowed();
        }
        return total;
    }

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {
            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                report.append("Student | Course: ")
                        .append(student.getCourse())
                        .append(" | Books: ")
                        .append(student.getBooksBorrowed())
                        .append(" [Course via downcast: ")
                        .append(student.getCourse())
                        .append("] | ");
            } else {
                report.append("General | Books: ")
                        .append(member.getBooksBorrowed())
                        .append(" | ");
            }
        }

        return report.toString();
    }

    public static String processNightlyAudit(LibraryMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int faculty = 0;
        int regular = 0;

        for (LibraryMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;
            if (member instanceof FacultyMember) {
                faculty++;
            } else {
                regular++;
            }
        }

        return processed + " processed | " + nullSkipped
                + " null skipped | " + faculty + " faculty | "
                + regular + " regular";
    }

    public static void main(String[] args) {
        System.out.println(LibraryMember.enrollBatch(
                new String[] {"STU1", "LB1", "STU2", " ", "STU3"}, 3));

        LibraryMember general = new LibraryMember("LIB5", 3);
        StudentMember student = new StudentMember("STU6", 3, "ECE");
        HonorsStudentMember honors =
                new HonorsStudentMember("STU7", 3, "CSE", 2);
        FacultyMember faculty = new FacultyMember("FAC1", 5, "Physics");

        student.borrowBook();
        student.borrowBook();
        honors.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();
        faculty.borrowBook();

        System.out.println(classifyGeneration(honors));
        System.out.println(classifyGeneration(faculty));
        System.out.println(getTotalBooksBorrowed(
                new LibraryMember[] {student, honors, faculty}));

        System.out.println(batchPrint(new LibraryMember[] {general, student}));

        student.chargeFine(100);
        System.out.println(student.getTotalFine());

        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(processNightlyAudit(
                new LibraryMember[] {faculty, null, general}));

        LibraryMember numbered = new LibraryMember(3);
        System.out.println(numbered.getMemberNumber());
        System.out.println(LibraryMember.getMembersEnrolled());
    }
}
