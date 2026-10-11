package week7_abstract_interfaces.part_a_oop;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

// ─── Part A – Problem 3: Student Club Registration Management ────────────────

class ClubStudent {
    private final String rollNumber;
    private final String name;

    public ClubStudent(String rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public String getRollNumber() { return rollNumber; }

    /**
     * Equality is based solely on rollNumber, as required.
     * This ensures duplicate roll numbers are rejected even when the
     * object instance is different.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClubStudent)) return false;
        return rollNumber.equals(((ClubStudent) o).rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}

public class StudentClubRegistration {

    public static void main(String[] args) {
        System.out.println("--- Part A Q3: Student Club Registration Management ---");

        Set<ClubStudent> members = new HashSet<>();

        String[] input = {
            "ADD 21CS01 Asha",
            "ADD 21CS01 Asha",
            "ADD 21CS02 Ravi",
            "CONTAINS 21CS02 Ravi",
            "CONTAINS 21CS03 Priya"
        };

        for (String line : input) {
            String[] p = line.trim().split("\\s+");
            if (p[0].equals("ADD")) {
                ClubStudent s = new ClubStudent(p[1], p[2]);
                if (members.add(s)) {
                    System.out.println("Added");
                } else {
                    System.out.println("duplicate rejected");
                }
            } else if (p[0].equals("CONTAINS")) {
                ClubStudent query = new ClubStudent(p[1], p[2]);
                System.out.println("contains: " + members.contains(query));
            }
        }

        System.out.println("member count " + members.size());
    }
}
