package week7_abstract_interfaces.coding_assignment_b;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// ─── Q2: The Assignment Submission Portal ────────────────────────────────────

interface LatePolicy {
    /** Returns the penalty rate per late day (0.0 – 1.0). */
    double penaltyRatePerDay();
}

abstract class CollegeAssignment {
    private final String title;
    private final int maxMarks;
    private final LocalDate dueDate;

    public CollegeAssignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    public String getTitle() { return title; }
    public int getMaxMarks() { return maxMarks; }
    public LocalDate getDueDate() { return dueDate; }

    /** Calculates final marks after applying the type-specific late penalty. */
    public int applyPenalty(int awarded, long lateDays) {
        if (lateDays <= 0) return awarded;
        double penalty = penaltyRatePerDay() * lateDays;
        penalty = Math.min(penalty, 1.0);   // cap at 100%
        return (int) Math.round(awarded * (1 - penalty));
    }

    public abstract double penaltyRatePerDay();
    public abstract String typeName();
}

class CodingAssignment extends CollegeAssignment {
    public CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    @Override public double penaltyRatePerDay() { return 0.10; }
    @Override public String typeName() { return "Coding"; }
}

class WrittenAssignment extends CollegeAssignment {
    public WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }
    @Override public double penaltyRatePerDay() { return 0.20; }
    @Override public String typeName() { return "Written"; }
}

enum SubmissionStatus { SUBMITTED, GRADED }

class Submission {
    private final String studentName;
    private final CollegeAssignment assignment;
    private final LocalDate submittedOn;
    private SubmissionStatus status;
    private int finalMarks = -1;

    public Submission(String studentName, CollegeAssignment assignment, LocalDate submittedOn) {
        this.studentName = studentName;
        this.assignment = assignment;
        this.submittedOn = submittedOn;
        this.status = SubmissionStatus.SUBMITTED;

        long late = ChronoUnit.DAYS.between(assignment.getDueDate(), submittedOn);
        if (late > 0) {
            System.out.printf("%s's submission for '%s' received (%d day%s late). Status: Submitted%n",
                    studentName, assignment.getTitle(), late, late == 1 ? "" : "s");
        } else {
            System.out.printf("%s's submission for '%s' received (on time). Status: Submitted%n",
                    studentName, assignment.getTitle());
        }
    }

    public void grade(int awarded) {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return;
        }
        long late = ChronoUnit.DAYS.between(assignment.getDueDate(), submittedOn);
        finalMarks = assignment.applyPenalty(awarded, late);
        status = SubmissionStatus.GRADED;

        if (late > 0) {
            int penalty = (int) Math.round(assignment.penaltyRatePerDay() * late * 100);
            System.out.printf("%s graded: %d/%d after %d%% late penalty. Status: Graded%n",
                    studentName, finalMarks, assignment.getMaxMarks(), penalty);
        } else {
            System.out.printf("%s graded: %d/%d. Status: Graded%n",
                    studentName, finalMarks, assignment.getMaxMarks());
        }
    }

    public void attemptResubmit() {
        if (status == SubmissionStatus.GRADED) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
        }
    }
}

public class AssignmentSubmissionPortal {

    public static void main(String[] args) {
        System.out.println("--- Q2: The Assignment Submission Portal ---");

        CollegeAssignment coding = new CodingAssignment("Linked List Lab", 50, LocalDate.of(2026, 3, 10));
        CollegeAssignment written = new WrittenAssignment("Design Essay", 50, LocalDate.of(2026, 3, 12));

        Submission ashaSubmission = new Submission("Asha", coding, LocalDate.of(2026, 3, 10));
        Submission raviSubmission = new Submission("Ravi", written, LocalDate.of(2026, 3, 14));

        ashaSubmission.grade(45);
        raviSubmission.grade(40);

        // Asha attempts resubmission
        ashaSubmission.attemptResubmit();
    }
}
