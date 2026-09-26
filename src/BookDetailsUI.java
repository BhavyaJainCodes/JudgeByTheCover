import java.awt.*;
import javax.swing.*;

public class BookDetailsUI extends JFrame {
    private final JFrame parent;
    private final BookManager bookManager;
    private final Book book;

    public BookDetailsUI(JFrame parent, BookManager bookManager, Book book) {
        this.parent = parent;
        this.bookManager = bookManager;
        this.book = book;
        setTitle("JudgeByTheCover - " + book.getTitle());
        setSize(650, 600);
        setLocationRelativeTo(parent);
        build();
        setVisible(true);
    }

    private void build() {
        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(25, 30, 25, 30));

        JLabel title = new JLabel("📖 " + book.getTitle());
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel genre = new JLabel("📚 Genre: " + book.getGenre());
        genre.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        genre.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel rating = new JLabel(ratingText());
        rating.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        rating.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton favorite = new JButton(book.isFavorite() ? "💔 Remove from Favorites" : "❤️ Add to Favorites");
        favorite.setAlignmentX(Component.CENTER_ALIGNMENT);
        favorite.addActionListener(e -> {
            bookManager.toggleFavorite(book);
            favorite.setText(book.isFavorite() ? "💔 Remove from Favorites" : "❤️ Add to Favorites");
        });

        JLabel statusTitle = new JLabel("📌 Reading Status");
        statusTitle.setFont(new Font("Segoe UI Emoji", Font.BOLD, 14));
        statusTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] statuses = {"To Be Read", "Currently Reading", "Finished"};
        JComboBox<String> status = new JComboBox<>(statuses);
        status.setSelectedItem(book.getReadingStatus());
        status.setMaximumSize(new Dimension(250, 35));
        status.addActionListener(e -> book.setReadingStatus((String) status.getSelectedItem()));
        status.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel reviewsTitle = new JLabel("💬 Reviews");
        reviewsTitle.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        reviewsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea reviews = new JTextArea(reviewText());
        reviews.setEditable(false);
        reviews.setLineWrap(true);
        reviews.setWrapStyleWord(true);
        reviews.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane reviewScroll = new JScrollPane(reviews);
        reviewScroll.setPreferredSize(new Dimension(550, 150));

        JButton addReview = new JButton("➕ Add Review");
        addReview.setAlignmentX(Component.CENTER_ALIGNMENT);
        addReview.addActionListener(e -> addReview());

        main.add(title);
        main.add(Box.createVerticalStrut(10));
        main.add(genre);
        main.add(Box.createVerticalStrut(10));
        main.add(rating);
        main.add(Box.createVerticalStrut(20));
        main.add(favorite);
        main.add(Box.createVerticalStrut(15));
        main.add(statusTitle);
        main.add(Box.createVerticalStrut(5));
        main.add(status);
        main.add(Box.createVerticalStrut(20));
        main.add(reviewsTitle);
        main.add(Box.createVerticalStrut(10));
        main.add(reviewScroll);
        main.add(Box.createVerticalStrut(15));
        main.add(addReview);

        setContentPane(new JScrollPane(main));
    }

    private String ratingText() {
        if (book.getReviewCount() == 0) return "⭐ No ratings yet";
        return String.format("⭐ Average Rating: %.1f / 5.0 (%d reviews)",
                book.getAverageRating(), book.getReviewCount());
    }

    private String reviewText() {
        if (book.getReviews().isEmpty()) return "No reviews yet. Be the first to review this book! ✨";
        StringBuilder text = new StringBuilder();
        for (Review review : book.getReviews()) {
            text.append("👤 ").append(review.getUsername()).append("\n");
            text.append("⭐ ").append(review.getRating()).append("/5\n");
            text.append(review.getText()).append("\n\n--------------------\n\n");
        }
        return text.toString();
    }

    private void addReview() {
        String username = JOptionPane.showInputDialog(this, "Enter your name:");
        if (username == null || username.trim().isEmpty()) return;

        String text = JOptionPane.showInputDialog(this, "Write your review:");
        if (text == null || text.trim().isEmpty()) return;

        String ratingInput = JOptionPane.showInputDialog(this, "Give a rating from 1 to 5:");
        if (ratingInput == null) return;

        try {
            int rating = Integer.parseInt(ratingInput.trim());
            if (rating < 1 || rating > 5) {
                JOptionPane.showMessageDialog(this, "Rating must be between 1 and 5.");
                return;
            }
            book.addReview(username, text, rating);
            JOptionPane.showMessageDialog(this, "Review added successfully! ⭐");
            dispose();
            new BookDetailsUI(parent, bookManager, book);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }
}
