package week7_abstract_interfaces.category_b;

/**
 * Problem 2 – Gallery Description Cards
 */
public class Painting extends ArtPiece {

    private final String title;

    public Painting(String title) {
        super();
        this.title = title;
    }

    @Override
    public String describe() {
        return "Painting: " + title + ", framed on canvas";
    }
}
