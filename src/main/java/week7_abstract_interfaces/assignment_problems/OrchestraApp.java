package week7_abstract_interfaces.assignment_problems;

public class OrchestraApp {

    public static void main(String[] args) {
        System.out.println("--- Problem 3: Orchestra Warm-Up ---");
        StringInstrument s = new StringInstrument();
        System.out.println(s.play());

        Violin v = new Violin();
        System.out.println(v.play());
    }
}
