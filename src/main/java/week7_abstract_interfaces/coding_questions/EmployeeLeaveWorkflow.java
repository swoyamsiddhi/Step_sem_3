package week7_abstract_interfaces.coding_questions;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

// ─── Q2: Employee Leave Request Workflow ────────────────────────────────────

enum LeaveStatus { PENDING, APPROVED, REJECTED }

class LeaveRequest {
    private final String employeeName;
    private final LocalDate start;
    private final LocalDate end;
    private LeaveStatus status;

    public LeaveRequest(String employeeName, LocalDate start, LocalDate end) {
        this.employeeName = employeeName;
        this.start = start;
        this.end = end;
        this.status = LeaveStatus.PENDING;
        System.out.printf("Leave request submitted for %s (%s – %s). Status: PENDING%n",
                employeeName, start, end);
    }

    public void approve() {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status + " to PENDING.");
            return;
        }
        status = LeaveStatus.APPROVED;
        System.out.printf("%s's leave request (%s – %s) approved. Status: APPROVED%n",
                employeeName, start, end);
    }

    public void reject() {
        if (status != LeaveStatus.PENDING) {
            System.out.println("Cannot change leave request status from " + status + " to PENDING.");
            return;
        }
        status = LeaveStatus.REJECTED;
        System.out.printf("%s's leave request (%s – %s) rejected. Status: REJECTED%n",
                employeeName, start, end);
    }

    /** Guard: revert-to-PENDING is never allowed once decided. */
    public void attemptRevertToPending() {
        System.out.println("Cannot change leave request status from " + status + " to PENDING.");
    }

    public LeaveStatus getStatus() { return status; }
}

abstract class EmployeeLeave {
    protected final String name;
    public EmployeeLeave(String name) { this.name = name; }
    public String getName() { return name; }
    public abstract LeaveRequest submitLeave(LocalDate start, LocalDate end);
}

class FullTimeEmployee extends EmployeeLeave {
    public FullTimeEmployee(String name) { super(name); }
    @Override
    public LeaveRequest submitLeave(LocalDate start, LocalDate end) {
        return new LeaveRequest(name, start, end);
    }
}

class PartTimeEmployee extends EmployeeLeave {
    public PartTimeEmployee(String name) { super(name); }
    @Override
    public LeaveRequest submitLeave(LocalDate start, LocalDate end) {
        long days = ChronoUnit.DAYS.between(start, end) + 1;
        if (days > 3) {
            System.out.println(name + " (part-time) can only request up to 3 days at once.");
            return null;
        }
        return new LeaveRequest(name, start, end);
    }
}

public class EmployeeLeaveWorkflow {

    public static void main(String[] args) {
        System.out.println("--- Q2: Employee Leave Request Workflow ---");

        FullTimeEmployee john = new FullTimeEmployee("John");
        PartTimeEmployee jane = new PartTimeEmployee("Jane");

        LeaveRequest r1 = john.submitLeave(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 5));
        r1.approve();

        LeaveRequest r2 = jane.submitLeave(LocalDate.of(2026, 2, 10), LocalDate.of(2026, 2, 11));
        r2.reject();

        // John tries to revert approved leave
        r1.attemptRevertToPending();
    }
}
