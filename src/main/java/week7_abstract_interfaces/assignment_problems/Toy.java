package week7_abstract_interfaces.assignment_problems;

public abstract class Toy {

    private static int counter = 1001;
    private final int toyId;

    public Toy() {
        toyId = counter++;
    }

    public String getToyId() {
        return "TOY-" + toyId;
    }

    public abstract String makeSound();
}
