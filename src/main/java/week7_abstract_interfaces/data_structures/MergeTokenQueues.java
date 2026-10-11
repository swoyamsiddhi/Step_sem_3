package week7_abstract_interfaces.data_structures;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 2 – Merging Two Token Queues
 *
 * Approach : Two-pointer merge (exploits the fact that both lists are already sorted).
 * Time Complexity : O(m + n)  – each element is visited exactly once
 * Space Complexity: O(m + n)  – output list
 *
 * Compare vs. join-then-sort: O((m+n) log(m+n)) time, same space.
 */
public class MergeTokenQueues {

    public static List<Integer> mergeTokens(int[] counterA, int[] counterB) {
        List<Integer> result = new ArrayList<>(counterA.length + counterB.length);
        int i = 0, j = 0;

        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                result.add(counterA[i++]);
            } else {
                result.add(counterB[j++]);
            }
        }
        while (i < counterA.length) result.add(counterA[i++]);
        while (j < counterB.length) result.add(counterB[j++]);

        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- DS Problem 2: Merging Two Token Queues ---");

        int[] cA = {3, 8, 15, 20};
        int[] cB = {5, 8, 12};
        System.out.println(mergeTokens(cA, cB));  // [3, 5, 8, 8, 12, 15, 20]

        int[] empty = {};
        int[] cC = {4, 9};
        System.out.println(mergeTokens(empty, cC)); // [4, 9]
    }
}
