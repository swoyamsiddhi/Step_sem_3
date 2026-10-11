package week7_abstract_interfaces.data_structures;

/**
 * Problem 5 – Ticket Price Slot Finder
 *
 * Binary search:
 *   Time Complexity : O(log n)  – halves the search space each iteration
 *   Space Complexity: O(1)
 *
 * Compare vs. linear scan: O(n) time, O(1) space.
 * When the loop ends, 'lo' equals the correct insertion (or found) index.
 */
public class TicketPriceSlotFinder {

    public static int findSlot(int[] prices, int newPrice) {
        int lo = 0, hi = prices.length;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (prices[mid] == newPrice) return mid;
            if (prices[mid] < newPrice) lo = mid + 1;
            else hi = mid;
        }
        return lo;  // insertion point when not found
    }

    public static void main(String[] args) {
        System.out.println("--- DS Problem 5: Ticket Price Slot Finder ---");

        int[] prices = {120, 150, 200, 260};
        System.out.println(findSlot(prices, 150));  // 1 – already present
        System.out.println(findSlot(prices, 210));  // 3 – insert before 260
        System.out.println(findSlot(prices, 300));  // 4 – append at end
    }
}
