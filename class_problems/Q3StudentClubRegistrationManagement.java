import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Q3StudentClubRegistrationManagement {
    static class Student {
        String rollNumber;
        String name;

        Student(String rollNumber, String name) {
            this.rollNumber = rollNumber;
            this.name = name;
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Student)) return false;
            Student other = (Student) obj;
            return rollNumber.equals(other.rollNumber);
        }

        public int hashCode() {
            return Objects.hash(rollNumber);
        }
    }

    public static void main(String[] args) {
        Set<Student> members = new HashSet<>();

        System.out.println(members.add(new Student("21CS01", "Asha")) ? "Added" : "duplicate rejected");
        System.out.println(members.add(new Student("21CS01", "Asha")) ? "Added" : "duplicate rejected");
        System.out.println(members.add(new Student("21CS02", "Ravi")) ? "Added" : "duplicate rejected");

        System.out.println("member count " + members.size());
        System.out.println("contains: " + members.contains(new Student("21CS02", "Ravi")));
        System.out.println("contains: " + members.contains(new Student("21CS03", "Priya")));
    }
}
