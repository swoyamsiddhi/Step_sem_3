package week7_abstract_interfaces.category_b;

/**
 * Problem 2 – Gallery Description Cards
 * Abstract base: auto-assigns a unique pieceId via a shared static counter.
 */
public abstract class ArtPiece {

    private static int counter = 0;

    private final String pieceId;

    public ArtPiece() {
        counter++;
        this.pieceId = "P" + counter;
    }

    public String getPieceId() {
        return pieceId;
    }

    public abstract String describe();
}
