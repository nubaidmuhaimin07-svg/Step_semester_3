import java.util.Locale;

public class Q4ClassMarksGridAnalysis {
    static class Student {
        String name;
        int[] marks;

        Student(String name, int[] marks) {
            this.name = name;
            this.marks = marks;
        }
    }

    public static void main(String[] args) {
        Student[] students = {
            new Student("Asha", new int[]{78, 85, 90}),
            new Student("Ravi", new int[]{88, 92, 79}),
            new Student("Neha", new int[]{65, 70, 95})
        };

        int[] subjectTotals = new int[3];
        int topperIndex = 0;
        int highestTotal = -1;

        System.out.print("Totals ");
        for (Student student : students) {
            int total = 0;
            for (int j = 0; j < 3; j++) {
                total += student.marks[j];
                subjectTotals[j] += student.marks[j];
            }
            System.out.print(student.name + " " + total);
            if (student != students[students.length - 1]) System.out.print(", ");

            if (total > highestTotal) {
                highestTotal = total;
                topperIndex = java.util.Arrays.asList(students).indexOf(student);
            }
        }

        System.out.printf("%naverages %.2f, %.2f, %.2f%n",
            subjectTotals[0] / (double) students.length,
            subjectTotals[1] / (double) students.length,
            subjectTotals[2] / (double) students.length);

        System.out.println("topper " + students[topperIndex].name + " (" + highestTotal + ")");
    }
}
