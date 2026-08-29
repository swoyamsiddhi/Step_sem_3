package week3_oop.assigment_problems;

/**
 * M1 — From Parallel Arrays to a Class: Library Inventory
 *
 * Demonstrates replacing three parallel arrays (titles, authors,
 * copiesAvailable) with a single class that bundles all related
 * data and behaviour together.
 */
public class BookInventory {

    // ── Fields ──
    String title;
    String author;
    int copiesAvailable;

    // ── Constructor ──
    BookInventory(String title, String author, int copiesAvailable) {
        this.title = title;
        this.author = author;
        this.copiesAvailable = copiesAvailable;
    }

    // ── Instance method ──
    void printEntry() {
        System.out.println(title + " by " + author + " - " + copiesAvailable + " copies available");
    }

    // ── Driver ──
    public static void main(String[] args) {

        BookInventory[] books = new BookInventory[4];

        books[0] = new BookInventory("Clean Code", "Robert C. Martin", 3);
        books[1] = new BookInventory("Effective Java", "Joshua Bloch", 5);
        books[2] = new BookInventory("Refactoring", "Martin Fowler", 0);
        books[3] = new BookInventory("Design Patterns", "GoF", 2);

        for (int i = 0; i < books.length; i++) {
            books[i].printEntry();
        }
    }
}
