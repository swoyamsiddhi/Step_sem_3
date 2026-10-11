package week7_abstract_interfaces.category_b;

/**
 * Problem 2 – Gallery Description Cards
 */
public class Sculpture extends ArtPiece {

    private final String title;

    public Sculpture(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}
