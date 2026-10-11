package week7_abstract_interfaces.data_structures;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem 3 – Most Popular Canteen Order
 *
 * Hash-map approach:
 *   Time Complexity : O(n)  – one pass to count, one pass to find max in order
 *   Space Complexity: O(k)  – k = number of distinct items
 *
 * Compare vs. re-scanning the full list for each item: O(n²) time.
 */
public class MostPopularOrder {

    public static Object[] mostPopular(String[] orders) {
        Map<String, Integer> count = new HashMap<>();
        for (String item : orders) {
            count.put(item, count.getOrDefault(item, 0) + 1);
        }

        int maxCount = 0;
        for (int v : count.values()) {
            if (v > maxCount) maxCount = v;
        }

        // Second pass in original order to honour the tie-breaking rule
        for (String item : orders) {
            if (count.get(item) == maxCount) {
                return new Object[]{item, maxCount};
            }
        }
        return new Object[]{"", 0};
    }

    public static void main(String[] args) {
        System.out.println("--- DS Problem 3: Most Popular Canteen Order ---");

        String[] o1 = {"dosa", "idli", "vada", "dosa", "idli", "dosa", "tea"};
        Object[] r1 = mostPopular(o1);
        System.out.println("(" + r1[0] + ", " + r1[1] + ")");  // (dosa, 3)

        String[] o2 = {"tea", "coffee", "coffee", "tea"};
        Object[] r2 = mostPopular(o2);
        System.out.println("(" + r2[0] + ", " + r2[1] + ")");  // (tea, 2)
    }
}
