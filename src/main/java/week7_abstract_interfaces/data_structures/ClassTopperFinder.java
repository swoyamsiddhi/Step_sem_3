package week7_abstract_interfaces.data_structures;

/**
 * Problem 1 – Class Topper Finder
 *
 * Time Complexity : O(m × n)  – one pass through every cell of the grid
 * Space Complexity: O(1)      – only a few scalar variables, no extra array
 */
public class ClassTopperFinder {

    /**
     * Returns (rowIndex, total) for the student with the highest total.
     * Ties are broken by smallest row index.
     */
    public static int[] findTopper(int[][] marks) {
        int bestRow = 0;
        int bestTotal = 0;

        for (int i = 0; i < marks.length; i++) {
            int rowTotal = 0;
            for (int j = 0; j < marks[i].length; j++) {
                rowTotal += marks[i][j];
            }
            if (rowTotal > bestTotal) {   // strictly greater – keeps first tie
                bestTotal = rowTotal;
                bestRow = i;
            }
        }
        return new int[]{bestRow, bestTotal};
    }

    public static void main(String[] args) {
        System.out.println("--- DS Problem 1: Class Topper Finder ---");

        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        int[] result = findTopper(marks);
        System.out.println("(" + result[0] + ", " + result[1] + ")");
        // Expected: (1, 259)
    }
}
