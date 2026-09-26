import java.awt.*;
import java.util.List;
import javax.swing.*;

public class MainUI extends JFrame {
    private final BookManager bookManager;
    private JTextField searchField;

    private final Color BACKGROUND = new Color(248, 246, 242);
    private final Color DARK = new Color(45, 35, 55);
    private final Color BORDER = new Color(220, 215, 220);

    public MainUI() {
        bookManager = new BookManager();
        setTitle("JudgeByTheCover");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        createHomePage();
        setVisible(true);
    }

    private void createHomePage() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(BACKGROUND);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(DARK);
        header.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25));

        JLabel logo = new JLabel("📚 JudgeByTheCover");
        logo.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        logo.setForeground(Color.WHITE);
        header.add(logo, BorderLayout.WEST);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.setOpaque(false);

        JButton library = new JButton("❤️ My Library");
        JButton dashboard = new JButton("📊 Dashboard");
        JButton logout = new JButton("🚪 Logout");

        library.addActionListener(e -> new LibraryUI(this, bookManager));
        dashboard.addActionListener(e -> new DashboardUI(this, bookManager));
        logout.addActionListener(e -> logout());

        buttons.add(library);
        buttons.add(dashboard);
        buttons.add(logout);
        header.add(buttons, BorderLayout.EAST);
        main.add(header, BorderLayout.NORTH);

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(BACKGROUND);
        content.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel welcome = new JLabel("Find Your Next Great Read 📖");
        welcome.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Discover books based on your interests.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        content.add(welcome);
        content.add(Box.createVerticalStrut(8));
        content.add(subtitle);
        content.add(Box.createVerticalStrut(30));

        JPanel search = new JPanel(new BorderLayout(10, 0));
        search.setOpaque(false);
        search.setMaximumSize(new Dimension(750, 45));
        searchField = new JTextField();
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JButton searchButton = new JButton("🔎 Search");
        searchButton.addActionListener(e -> searchBooks());
        searchField.addActionListener(e -> searchBooks());
        search.add(searchField, BorderLayout.CENTER);
        search.add(searchButton, BorderLayout.EAST);
        content.add(search);

        content.add(Box.createVerticalStrut(20));
        JButton random = new JButton("🎲 Discover a Random Book");
        random.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        random.setAlignmentX(Component.CENTER_ALIGNMENT);
        random.addActionListener(e -> showRandomBook());
        content.add(random);

        content.add(Box.createVerticalStrut(35));
        JLabel genreTitle = new JLabel("📚 Browse by Genre");
        genreTitle.setFont(new Font("Segoe UI Emoji", Font.BOLD, 22));
        genreTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(genreTitle);
        content.add(Box.createVerticalStrut(20));

        JPanel genrePanel = new JPanel(new GridLayout(4, 2, 15, 15));
        genrePanel.setOpaque(false);
        genrePanel.setMaximumSize(new Dimension(750, 230));

        String[][] genres = {
                {"📜 History", "History"}, {"💡 Self-Help", "Self-Help"},
                {"🔍 Mystery", "Mystery"}, {"🚀 Sci-Fi", "Sci-Fi"},
                {"🧙 Fantasy", "Fantasy"}, {"👻 Horror", "Horror"},
                {"💕 Romance", "Romance"}, {"🔮 Future", "Future"}
        };

        for (String[] genre : genres) {
            JButton button = new JButton(genre[0]);
            button.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
            button.addActionListener(e -> showBooksByGenre(genre[1]));
            genrePanel.add(button);
        }
        content.add(genrePanel);

        main.add(new JScrollPane(content), BorderLayout.CENTER);
        setContentPane(main);
        revalidate();
        repaint();
    }

    private void searchBooks() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a book name.", "Search", JOptionPane.WARNING_MESSAGE);
            return;
        }
        List<Book> results = bookManager.searchBooks(query);
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No books found for: " + query, "Search", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        showBookList("🔎 Search Results", results);
    }

    private void showRandomBook() {
        Book book = bookManager.getRandomBook();
        if (book != null) new BookDetailsUI(this, bookManager, book);
    }

    private void showBooksByGenre(String genre) {
        showBookList("📚 " + genre + " Books", bookManager.getBooksByGenre(genre));
    }

    private void showBookList(String title, List<Book> books) {
        JFrame frame = new JFrame(title);
        frame.setSize(800, 550);
        frame.setLocationRelativeTo(this);

        JPanel panel = new JPanel(new GridLayout(0, 2, 15, 15));
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Book book : books) panel.add(createBookCard(frame, book));

        frame.add(new JScrollPane(panel));
        frame.setVisible(true);
    }

    private JPanel createBookCard(JFrame parent, Book book) {
        JPanel card = new JPanel(new BorderLayout(10, 5));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)));

        JLabel icon = new JLabel("📖");
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 35));
        card.add(icon, BorderLayout.WEST);

        JPanel info = new JPanel();
        info.setOpaque(false);
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("<html><b>" + book.getTitle() + "</b></html>");
        JLabel genre = new JLabel("📚 " + book.getGenre());
        JLabel status = new JLabel("📌 " + book.getReadingStatus());
        String rating = book.getReviewCount() == 0 ? "⭐ No ratings yet" :
                String.format("⭐ %.1f / 5.0", book.getAverageRating());
        JLabel ratingLabel = new JLabel(rating);

        info.add(title);
        info.add(Box.createVerticalStrut(5));
        info.add(genre);
        info.add(status);
        info.add(ratingLabel);
        card.add(info, BorderLayout.CENTER);

        JButton view = new JButton("View");
        view.addActionListener(e -> new BookDetailsUI(this, bookManager, book));
        card.add(view, BorderLayout.EAST);

        return card;
    }

    private void logout() {
        int choice = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new LoginUI();
        }
    }
}
