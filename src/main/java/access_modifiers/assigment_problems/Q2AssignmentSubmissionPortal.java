import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Assignment {
    private final String title;
    private final double maxMarks;
    private final LocalDate dueDate;

    public Assignment(String title, double maxMarks, LocalDate dueDate) {
        if (title == null || title.trim().isEmpty() || maxMarks <= 0 || dueDate == null) {
            throw new IllegalArgumentException("Invalid assignment details");
        }
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public double getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }
    public abstract double getDailyPenaltyRate();

    public double applyLatePenalty(double awardedMarks, long lateDays) {
        double penalty = getDailyPenaltyRate() * lateDays;
        return Math.max(0, awardedMarks * (1.0 - penalty));
    }
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    public double getDailyPenaltyRate() { return 0.10; }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, double maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    public double getDailyPenaltyRate() { return 0.20; }
}

class Student {
    private final String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}

enum SubmissionStatus { SUBMITTED, GRADED }

class Submission {
    private final Student student;
    private final Assignment assignment;
    private final LocalDate submittedDate;
    private SubmissionStatus status = SubmissionStatus.SUBMITTED;
    private double finalMarks = -1;

    public Submission(Student student, Assignment assignment, LocalDate submittedDate) {
        this.student = student;
        this.assignment = assignment;
        this.submittedDate = submittedDate;
        long lateDays = Math.max(0, ChronoUnit.DAYS.between(assignment.getDueDate(), submittedDate));
        System.out.println(student.getName() + "'s submission for '" + assignment.getTitle()
                + "' received (" + (lateDays == 0 ? "on time" : lateDays + " days late") + "). Status: Submitted.");
    }

    public void grade(double awardedMarks) {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Submission has already been graded.");
            return;
        }
        if (awardedMarks < 0 || awardedMarks > assignment.getMaxMarks()) {
            System.out.println("Marks must be between 0 and " + assignment.getMaxMarks() + ".");
            return;
        }
        long lateDays = Math.max(0, ChronoUnit.DAYS.between(assignment.getDueDate(), submittedDate));
        finalMarks = assignment.applyLatePenalty(awardedMarks, lateDays);
        status = SubmissionStatus.GRADED;
        double penaltyPercent = lateDays * assignment.getDailyPenaltyRate() * 100;
        System.out.printf(java.util.Locale.US, "%s graded: %.2f/%.0f", student.getName(), finalMarks, assignment.getMaxMarks());
        if (lateDays > 0) System.out.printf(java.util.Locale.US, " after %.0f%% late penalty", penaltyPercent);
        System.out.println(". Status: Graded.");
    }

    public void resubmit() {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
        } else {
            System.out.println("Resubmission received for '" + assignment.getTitle() + "'.");
        }
    }
}

public class Q2AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Assignment coding = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        Assignment written = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission(asha, coding, LocalDate.of(2026, 3, 10));
        Submission s2 = new Submission(ravi, written, LocalDate.of(2026, 3, 14));
        s1.grade(45);
        s2.grade(40);
        s1.resubmit();
    }
}
