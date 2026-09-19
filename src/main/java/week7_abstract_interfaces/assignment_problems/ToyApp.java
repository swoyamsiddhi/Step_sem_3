package week7_abstract_interfaces.assignment_problems;

public class ToyApp {

    public static void main(String[] args) {
        System.out.println("--- Problem 1: Toy Box ---");
        ToyCar c = new ToyCar("Speedster");
        ToyRobot r = new ToyRobot("Bolt");
        System.out.println(c.makeSound());
        System.out.println(r.makeSound());
        System.out.println(c.getToyId());
        System.out.println(r.getToyId());
    }
}
