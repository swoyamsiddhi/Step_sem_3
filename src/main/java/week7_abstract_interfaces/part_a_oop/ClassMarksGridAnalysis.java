package week7_abstract_interfaces.part_a_oop;

// ─── Part A – Problem 4: Class Marks Grid Analysis ───────────────────────────

public class ClassMarksGridAnalysis {

    public static void main(String[] args) {
        System.out.println("--- Part A Q4: Class Marks Grid Analysis ---");

        // 3 students × 3 subjects
        String[] names   = {"Asha", "Ravi", "Neha"};
        int[][]  marks   = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        int numStudents = marks.length;
        int numSubjects = marks[0].length;
        int[] totals = new int[numStudents];

        // Row sums
        for (int i = 0; i < numStudents; i++) {
            for (int j = 0; j < numSubjects; j++) {
                totals[i] += marks[i][j];
            }
        }

        // Print totals
        StringBuilder totalsLine = new StringBuilder("Totals ");
        for (int i = 0; i < numStudents; i++) {
            totalsLine.append(names[i]).append(" ").append(totals[i]);
            if (i < numStudents - 1) totalsLine.append(", ");
        }
        System.out.println(totalsLine);

        // Column averages
        StringBuilder avgLine = new StringBuilder("averages ");
        for (int j = 0; j < numSubjects; j++) {
            int colSum = 0;
            for (int i = 0; i < numStudents; i++) colSum += marks[i][j];
            avgLine.append(String.format("%.2f", (double) colSum / numStudents));
            if (j < numSubjects - 1) avgLine.append(", ");
        }
        System.out.println(avgLine);

        // Topper
        int topperIdx = 0;
        for (int i = 1; i < numStudents; i++) {
            if (totals[i] > totals[topperIdx]) topperIdx = i;
        }
        System.out.println("topper " + names[topperIdx] + " (" + totals[topperIdx] + ")");
    }
}
