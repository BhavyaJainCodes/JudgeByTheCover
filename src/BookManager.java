import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookManager {
    private List<Book> books;

    public BookManager() {
        books = new ArrayList<>();
        loadBooks();
    }

    private void loadBooks() {

    // =========================
    // HISTORY
    // =========================
    addBook("Sapiens", "History");
    addBook("The Diary of a Young Girl", "History");
    addBook("Guns, Germs, and Steel", "History");
    addBook("The Silk Roads", "History");
    addBook("A Little History of the World", "History");
    addBook("India After Gandhi", "History");
    addBook("The Discovery of India", "History");
    addBook("The Wright Brothers", "History");

    // =========================
    // SELF-HELP
    // =========================
    addBook("Atomic Habits", "Self-Help");
    addBook("The 7 Habits of Highly Effective People", "Self-Help");
    addBook("How to Win Friends and Influence People", "Self-Help");
    addBook("The Power of Now", "Self-Help");
    addBook("Think and Grow Rich", "Self-Help");
    addBook("Deep Work", "Self-Help");
    addBook("The Psychology of Money", "Self-Help");
    addBook("Ikigai", "Self-Help");

    // =========================
    // MYSTERY
    // =========================
    addBook("The Silent Patient", "Mystery");
    addBook("Gone Girl", "Mystery");
    addBook("The Girl with the Dragon Tattoo", "Mystery");
    addBook("And Then There Were None", "Mystery");
    addBook("The Murder of Roger Ackroyd", "Mystery");
    addBook("The Da Vinci Code", "Mystery");
    addBook("Big Little Lies", "Mystery");
    addBook("The Woman in the Window", "Mystery");

    // =========================
    // SCI-FI
    // =========================
    addBook("Dune", "Sci-Fi");
    addBook("The Martian", "Sci-Fi");
    addBook("Project Hail Mary", "Sci-Fi");
    addBook("Ender's Game", "Sci-Fi");
    addBook("Ready Player One", "Sci-Fi");
    addBook("The Time Machine", "Sci-Fi");
    addBook("Foundation", "Sci-Fi");
    addBook("The Hunger Games", "Sci-Fi");

    // =========================
    // FANTASY
    // =========================
    addBook("Harry Potter", "Fantasy");
    addBook("The Hobbit", "Fantasy");
    addBook("The Lord of the Rings", "Fantasy");
    addBook("The Chronicles of Narnia", "Fantasy");
    addBook("A Game of Thrones", "Fantasy");
    addBook("The Name of the Wind", "Fantasy");
    addBook("Percy Jackson", "Fantasy");
    addBook("The Golden Compass", "Fantasy");

    // =========================
    // HORROR
    // =========================
    addBook("It", "Horror");
    addBook("The Shining", "Horror");
    addBook("Dracula", "Horror");
    addBook("Frankenstein", "Horror");
    addBook("The Exorcist", "Horror");
    addBook("Pet Sematary", "Horror");
    addBook("The Haunting of Hill House", "Horror");
    addBook("Bird Box", "Horror");

    // =========================
    // ROMANCE
    // =========================
    addBook("Pride and Prejudice", "Romance");
    addBook("The Fault in Our Stars", "Romance");
    addBook("Me Before You", "Romance");
    addBook("The Notebook", "Romance");
    addBook("The Love Hypothesis", "Romance");
    addBook("It Ends with Us", "Romance");
    addBook("Beach Read", "Romance");
    addBook("Red, White & Royal Blue", "Romance");

    // =========================
    // FUTURE
    // =========================
    addBook("1984", "Future");
    addBook("Brave New World", "Future");
    addBook("Fahrenheit 451", "Future");
    addBook("The Handmaid's Tale", "Future");
    addBook("The Giver", "Future");
    addBook("Animal Farm", "Future");
    addBook("Do Androids Dream of Electric Sheep?", "Future");
    addBook("Station Eleven", "Future");
}

    public void addBook(String title, String genre) { books.add(new Book(title, genre)); }
    public List<Book> getAllBooks() { return books; }

    public List<Book> searchBooks(String query) {
        List<Book> result = new ArrayList<>();
        if (query == null || query.trim().isEmpty()) return result;
        query = query.toLowerCase().trim();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query)) result.add(book);
        }
        return result;
    }

    public List<Book> getBooksByGenre(String genre) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getGenre().equalsIgnoreCase(genre)) result.add(book);
        }
        return result;
    }

    public Book getRandomBook() {
        if (books.isEmpty()) return null;
        return books.get(new Random().nextInt(books.size()));
    }

    public List<Book> getFavoriteBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : books) if (book.isFavorite()) result.add(book);
        return result;
    }

    public List<Book> getBooksByStatus(String status) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getReadingStatus().equalsIgnoreCase(status)) result.add(book);
        }
        return result;
    }

    public void toggleFavorite(Book book) {
        if (book != null) book.setFavorite(!book.isFavorite());
    }
}
