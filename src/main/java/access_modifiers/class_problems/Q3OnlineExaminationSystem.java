import java.util.*;

abstract class Question {
    private final String text;
    private final int points;
    public Question(String text, int points) {
        this.text = text;
        this.points = points;
    }
    public String getText() { return text; }
    public int getPoints() { return points; }
    public abstract boolean isCorrect(String answer);
}

class MultipleChoiceQuestion extends Question {
    private final String correctOption;
    public MultipleChoiceQuestion(String text, int points, String correctOption) {
        super(text, points);
        this.correctOption = correctOption;
    }
    public boolean isCorrect(String answer) {
        return answer != null && correctOption.equalsIgnoreCase(answer.trim());
    }
}

class TrueFalseQuestion extends Question {
    private final boolean correctAnswer;
    public TrueFalseQuestion(String text, int points, boolean correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }
    public boolean isCorrect(String answer) {
        return answer != null && Boolean.toString(correctAnswer).equalsIgnoreCase(answer.trim());
    }
}

class ShortAnswerQuestion extends Question {
    private final String correctAnswer;
    public ShortAnswerQuestion(String text, int points, String correctAnswer) {
        super(text, points);
        this.correctAnswer = correctAnswer;
    }
    public boolean isCorrect(String answer) {
        return answer != null && correctAnswer.equalsIgnoreCase(answer.trim());
    }
}

class Student {
    private final String name;
    public Student(String name) { this.name = name; }
    public String getName() { return name; }
}

class Examination {
    private final String title;
    private final List<Question> questions;
    public Examination(String title, List<Question> questions) {
        this.title = title;
        this.questions = new ArrayList<>(questions);
    }
    public String getTitle() { return title; }
    public List<Question> getQuestions() { return Collections.unmodifiableList(questions); }
}

class Attempt {
    private final Student student;
    private final Examination examination;
    private final Map<Question, String> answers = new LinkedHashMap<>();
    private boolean submitted = false;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
    }

    public void answer(Question question, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        if (!examination.getQuestions().contains(question)) {
            System.out.println("Question does not belong to this examination.");
            return;
        }
        answers.put(question, answer);
        System.out.println("Answer recorded for " + question.getText() + ".");
    }

    public void submit() {
        if (submitted) {
            System.out.println("This examination has already been submitted.");
            return;
        }
        submitted = true;
        int score = 0;
        int total = 0;
        System.out.println(examination.getTitle() + " submitted by " + student.getName() + ".");
        for (Question q : examination.getQuestions()) {
            total += q.getPoints();
            boolean correct = q.isCorrect(answers.get(q));
            if (correct) score += q.getPoints();
            System.out.println(q.getText() + ": " + (correct ? "Correct" : "Incorrect")
                    + " (" + (correct ? q.getPoints() : 0) + " points)");
        }
        System.out.println("Total score: " + score + "/" + total);
    }
}

public class Q3OnlineExaminationSystem {
    public static void main(String[] args) {
        Question q1 = new MultipleChoiceQuestion("Question 1", 5, "C");
        Question q2 = new TrueFalseQuestion("Question 2", 5, false);
        Examination exam = new Examination("Exam A", Arrays.asList(q1, q2));
        Student student = new Student("Student 1");
        Attempt attempt = new Attempt(student, exam);
        System.out.println("Exam A started by Student 1.");
        attempt.answer(q1, "C");
        attempt.answer(q2, "True");
        attempt.submit();
        attempt.answer(q1, "A");
    }
}
