package week7_abstract_interfaces.category_b;

/**
 * Problem 4 – Digital Classroom Setup
 * Compile-time polymorphism: two charge() methods with different parameter lists.
 */
public interface Chargeable {
    String charge();
    String charge(int minutes);
}
