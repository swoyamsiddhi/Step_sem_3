package week2_strings.assignment_problems;

public class ReverseEachWord {

    String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversed = new StringBuilder(words[i]).reverse();
            result.append(reversed);
            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        ReverseEachWord encoder = new ReverseEachWord();
        System.out.println(encoder.reverseEachWord("hello club"));
    }
}
