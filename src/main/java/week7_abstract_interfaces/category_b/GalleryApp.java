package week7_abstract_interfaces.category_b;

/**
 * Problem 2 – Gallery Description Cards
 * Runner: demonstrates auto-ID and distinct describe() messages.
 */
public class GalleryApp {

    public static void main(String[] args) {
        System.out.println("--- Problem 2: Gallery Description Cards ---");

        Painting p = new Painting("Sunset Fields");
        System.out.println("[" + p.getPieceId() + "] " + p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println("[" + s.getPieceId() + "] " + s.describe());
    }
}
