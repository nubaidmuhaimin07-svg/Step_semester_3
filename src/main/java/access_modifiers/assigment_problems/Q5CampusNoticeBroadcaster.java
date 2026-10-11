import java.util.*;

interface NotificationChannel {
    String getName();
    void send(String studentName, String title);
}

class EmailChannel implements NotificationChannel {
    public String getName() { return "Email"; }
    public void send(String studentName, String title) {
        System.out.println("[Email → " + studentName + "] " + title);
    }
}

class SmsChannel implements NotificationChannel {
    public String getName() { return "SMS"; }
    public void send(String studentName, String title) {
        System.out.println("[SMS → " + studentName + "] " + title);
    }
}

class AppChannel implements NotificationChannel {
    public String getName() { return "App"; }
    public void send(String studentName, String title) {
        System.out.println("[App → " + studentName + "] " + title);
    }
}

class Student {
    private final String name;
    private final String department;
    private final List<NotificationChannel> preferredChannels = new ArrayList<>();

    public Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }

    public void addPreferredChannel(NotificationChannel channel) {
        if (channel != null && !preferredChannels.contains(channel)) preferredChannels.add(channel);
    }

    public List<NotificationChannel> getPreferredChannels() {
        return Collections.unmodifiableList(preferredChannels);
    }
}

class Notice {
    private final String title;
    private final Set<String> departments;

    public Notice(String title, Set<String> departments) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Notice title is required.");
        }
        if (departments == null || departments.isEmpty()) {
            throw new IllegalArgumentException("At least one target department is required.");
        }
        this.title = title;
        this.departments = new LinkedHashSet<>(departments);
    }

    public String getTitle() { return title; }
    public Set<String> getDepartments() { return Collections.unmodifiableSet(departments); }
}

class NoticeBoard {
    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) { students.add(student); }

    public void postNotice(String title, Set<String> departments) {
        Notice notice;
        try {
            notice = new Notice(title, departments);
        } catch (IllegalArgumentException e) {
            System.out.println("Cannot post notice: " + e.getMessage());
            return;
        }

        System.out.println("Notice '" + notice.getTitle() + "' posted to " +
                String.join(", ", notice.getDepartments()) + ".");
        for (Student student : students) {
            if (notice.getDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getPreferredChannels()) {
                    channel.send(student.getName(), notice.getTitle());
                }
            }
        }
    }
}

public class Q5CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();
        Student asha = new Student("Asha", "CSE");
        asha.addPreferredChannel(new EmailChannel());
        asha.addPreferredChannel(new AppChannel());

        Student ravi = new Student("Ravi", "ECE");
        ravi.addPreferredChannel(new SmsChannel());

        board.addStudent(asha);
        board.addStudent(ravi);

        board.postNotice("Lab Closed Tomorrow", new LinkedHashSet<>(Arrays.asList("CSE")));
        board.postNotice("Fee Deadline Extended", new LinkedHashSet<>(Arrays.asList("CSE", "ECE")));
        board.postNotice("Sports Day", new LinkedHashSet<>());
    }
}
