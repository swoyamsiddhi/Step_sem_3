package week7_abstract_interfaces.category_b;

/**
 * Problem 4 – Digital Classroom Setup
 * Runner: demonstrates operate() and overloaded charge().
 */
public class ClassroomApp {

    public static void main(String[] args) {
        System.out.println("--- Problem 4: Digital Classroom Setup ---");

        Tablet t = new Tablet("TAB-5");
        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}
