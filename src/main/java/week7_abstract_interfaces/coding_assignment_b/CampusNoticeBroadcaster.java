package week7_abstract_interfaces.coding_assignment_b;

import java.util.ArrayList;
import java.util.List;

// ─── Q5: The Campus Notice Broadcaster ───────────────────────────────────────

/** Strategy contract for delivering a notice — open to extension. */
interface NotificationChannel {
    void send(String recipientName, String noticeTitle);
}

class EmailChannel implements NotificationChannel {
    @Override
    public void send(String recipientName, String noticeTitle) {
        System.out.println("[Email → " + recipientName + "] " + noticeTitle);
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public void send(String recipientName, String noticeTitle) {
        System.out.println("[SMS → " + recipientName + "] " + noticeTitle);
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public void send(String recipientName, String noticeTitle) {
        System.out.println("[App → " + recipientName + "] " + noticeTitle);
    }
}

class CollegeStudent {
    private final String name;
    private final String department;
    private final List<NotificationChannel> channels;

    public CollegeStudent(String name, String department, List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = new ArrayList<>(channels);
    }

    public String getName() { return name; }
    public String getDepartment() { return department; }
    public List<NotificationChannel> getChannels() { return channels; }
}

class Notice {
    private final String title;
    private final List<String> targetDepartments;

    public Notice(String title, List<String> targetDepartments) {
        this.title = title;
        this.targetDepartments = new ArrayList<>(targetDepartments);
    }

    public String getTitle() { return title; }
    public List<String> getTargetDepartments() { return targetDepartments; }
}

class NoticeBoard {
    private final List<CollegeStudent> students = new ArrayList<>();

    public void addStudent(CollegeStudent s) { students.add(s); }

    public void post(Notice notice) {
        // Validate
        if (notice.getTargetDepartments() == null || notice.getTargetDepartments().isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        StringBuilder depts = new StringBuilder();
        List<String> targets = notice.getTargetDepartments();
        for (int i = 0; i < targets.size(); i++) {
            depts.append(targets.get(i));
            if (i < targets.size() - 1) depts.append(", ");
        }
        System.out.println("Notice '" + notice.getTitle() + "' posted to " + depts + ".");

        // Deliver to each student in target departments via each preferred channel
        for (CollegeStudent student : students) {
            if (notice.getTargetDepartments().contains(student.getDepartment())) {
                for (NotificationChannel channel : student.getChannels()) {
                    channel.send(student.getName(), notice.getTitle());
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {

    public static void main(String[] args) {
        System.out.println("--- Q5: The Campus Notice Broadcaster ---");

        // Asha (CSE) → Email + App
        List<NotificationChannel> ashaChannels = new ArrayList<>();
        ashaChannels.add(new EmailChannel());
        ashaChannels.add(new AppChannel());
        CollegeStudent asha = new CollegeStudent("Asha", "CSE", ashaChannels);

        // Ravi (ECE) → SMS
        List<NotificationChannel> raviChannels = new ArrayList<>();
        raviChannels.add(new SmsChannel());
        CollegeStudent ravi = new CollegeStudent("Ravi", "ECE", raviChannels);

        NoticeBoard board = new NoticeBoard();
        board.addStudent(asha);
        board.addStudent(ravi);

        // Notice 1: CSE only
        List<String> d1 = new ArrayList<>();
        d1.add("CSE");
        board.post(new Notice("Lab Closed Tomorrow", d1));

        // Notice 2: CSE + ECE
        List<String> d2 = new ArrayList<>();
        d2.add("CSE"); d2.add("ECE");
        board.post(new Notice("Fee Deadline Extended", d2));

        // Notice 3: no departments – validation fails
        board.post(new Notice("Sports Day", new ArrayList<>()));
    }
}
