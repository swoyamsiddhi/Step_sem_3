package week2_strings.assignment_problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class FilteredWordFrequency {

    void printFilteredWordFrequency(String feedback) {
        String[] stopWords = {"the", "was", "and", "a", "is", "of", "in"};

        String cleaned = feedback.toLowerCase().replace(",", "").replace(".", "");
        String[] words = cleaned.split("\\s+");

        HashMap<String, Integer> freq = new HashMap<>();

        for (String word : words) {
            boolean isStopWord = false;
            for (String stop : stopWords) {
                if (word.equals(stop)) {
                    isStopWord = true;
                    break;
                }
            }
            if (!isStopWord) {
                freq.put(word, freq.getOrDefault(word, 0) + 1);
            }
        }

        ArrayList<String> keys = new ArrayList<>(freq.keySet());
        Collections.sort(keys, (a, b) -> freq.get(b) - freq.get(a));

        for (String key : keys) {
            System.out.println(key + ": " + freq.get(key));
        }
    }

    public static void main(String[] args) {
        FilteredWordFrequency fwf = new FilteredWordFrequency();
        fwf.printFilteredWordFrequency("The mentor was great, the session was great and clear.");
    }
}
