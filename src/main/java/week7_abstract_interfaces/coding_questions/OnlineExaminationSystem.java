package week7_abstract_interfaces.coding_questions;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

// ─── Q3: Online Examination System ──────────────────────────────────────────

abstract class Question {
    private final String questionId;
    private final int marks;

    public Question(String questionId, int marks) {
        this.questionId = questionId;
        this.marks = marks;
    }

    public String getQuestionId() { return questionId; }
    public int getMarks() { return marks; }

    /** Returns earned marks for the given answer string. */
    public abstract int evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    private final String correctOption;
    public MultipleChoiceQuestion(String id, int marks, String correctOption) {
        super(id, marks);
        this.correctOption = correctOption;
    }
    @Override
    public int evaluate(String answer) {
        return correctOption.equalsIgnoreCase(answer) ? getMarks() : 0;
    }
}

class TrueFalseQuestion extends Question {
    private final boolean correctAnswer;
    public TrueFalseQuestion(String id, int marks, boolean correctAnswer) {
        super(id, marks);
        this.correctAnswer = correctAnswer;
    }
    @Override
    public int evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer ? getMarks() : 0;
    }
}

class ShortAnswerQuestion extends Question {
    private final String modelAnswer;
    public ShortAnswerQuestion(String id, int marks, String modelAnswer) {
        super(id, marks);
        this.modelAnswer = modelAnswer;
    }
    @Override
    public int evaluate(String answer) {
        return modelAnswer.equalsIgnoreCase(answer.trim()) ? getMarks() : 0;
    }
}

enum AttemptStatus { IN_PROGRESS, SUBMITTED }

class Attempt {
    private final String studentId;
    private final String examId;
    private final Map<String, String> answers = new LinkedHashMap<>();
    private AttemptStatus status = AttemptStatus.IN_PROGRESS;

    public Attempt(String studentId, String examId) {
        this.studentId = studentId;
        this.examId = examId;
        System.out.println(examId + " started by " + studentId + ".");
    }

    public void recordAnswer(String questionId, String answer) {
        if (status == AttemptStatus.SUBMITTED) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }
        answers.put(questionId, answer);
        System.out.println("Answer recorded for " + questionId + ".");
    }

    public void submit(List<Question> questions) {
        if (status == AttemptStatus.SUBMITTED) return;
        status = AttemptStatus.SUBMITTED;
        System.out.println(examId + " submitted by " + studentId + ".");
        int total = 0, maxTotal = 0;
        for (Question q : questions) {
            maxTotal += q.getMarks();
            String ans = answers.getOrDefault(q.getQuestionId(), "");
            int earned = q.evaluate(ans);
            total += earned;
            String verdict = earned > 0 ? "Correct (" + earned + " points)" : "Incorrect (0 points)";
            System.out.println("Result: " + q.getQuestionId() + ": " + verdict);
        }
        System.out.println("Total score: " + total + "/" + maxTotal);
    }
}

public class OnlineExaminationSystem {

    public static void main(String[] args) {
        System.out.println("--- Q3: Online Examination System ---");

        List<Question> questions = new ArrayList<>();
        questions.add(new MultipleChoiceQuestion("Question 1", 5, "C"));
        questions.add(new TrueFalseQuestion("Question 2", 5, false));

        Attempt attempt = new Attempt("Student 1", "Exam A");
        attempt.recordAnswer("Question 1", "C");
        attempt.recordAnswer("Question 2", "true");
        attempt.submit(questions);

        // Attempt to change answer after submission
        attempt.recordAnswer("Question 1", "A");
    }
}
