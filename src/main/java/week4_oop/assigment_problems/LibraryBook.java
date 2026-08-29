package week4_oop.assigment_problems;

public class LibraryBook {
    String title;
    String isbn;
    boolean isCatalogued;

    public LibraryBook(String title, String isbn) {
        this.title = title;
        this.isbn = isbn;
        this.isCatalogued = true;
    }

    public LibraryBook(String title) {
        this(title, "PENDING");
    }

    public void printDetails() {
        System.out.println(title + " | " + isbn + " | Catalogued: " + isCatalogued);
    }

    public static void main(String[] args) {
        String[] titles = {"Clean Code", "Untitled Draft", "1984", "Notes"};
        String[] isbns = {"978-0132350884", "", "9780451524935", ""};

        LibraryBook[] books = new LibraryBook[titles.length];

        for (int i = 0; i < titles.length; i++) {
            if (isbns[i] == null || isbns[i].isEmpty()) {
                books[i] = new LibraryBook(titles[i]);
            } else {
                books[i] = new LibraryBook(titles[i], isbns[i]);
            }
        }

        for (int i = 0; i < books.length; i++) {
            books[i].printDetails();
        }
    }
}
