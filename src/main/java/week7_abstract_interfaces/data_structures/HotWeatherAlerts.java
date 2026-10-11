package week7_abstract_interfaces.data_structures;

/**
 * Problem 4 – Hot Weather Alert Windows
 *
 * Sliding-window approach:
 *   Time Complexity : O(n)  – one pass; compare sum >= k×threshold to avoid division
 *   Space Complexity: O(1)  – only a running sum scalar
 *
 * Compare vs. recomputing every block from scratch: O(n × k) time.
 */
public class HotWeatherAlerts {

    public static int countAlerts(int[] readings, int k, int threshold) {
        int alerts = 0;
        int windowSum = 0;

        // Build the first window
        for (int i = 0; i < k; i++) {
            windowSum += readings[i];
        }
        if (windowSum >= (long) k * threshold) alerts++;

        // Slide the window
        for (int i = k; i < readings.length; i++) {
            windowSum += readings[i] - readings[i - k];
            if (windowSum >= (long) k * threshold) alerts++;
        }
        return alerts;
    }

    public static void main(String[] args) {
        System.out.println("--- DS Problem 4: Hot Weather Alert Windows ---");

        int[] readings = {2, 2, 2, 2, 5, 5, 5, 8};
        System.out.println(countAlerts(readings, 3, 4));  // 3
    }
}
