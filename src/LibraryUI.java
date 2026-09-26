import java.awt.*;
import java.util.List;
import javax.swing.*;

public class LibraryUI extends JFrame {
    private final JFrame parent;
    private final BookManager bookManager;
    private final Color BACKGROUND = new Color(248, 246, 242);
    private final Color BORDER = new Color(220, 215, 220);

    public LibraryUI(JFrame parent, BookManager bookManager) {
        this.parent = parent;
        this.bookManager = bookManager;
        setTitle("❤️ My Library");
        setSize(850, 600);
        setLocationRelativeTo(parent);
        build();
        setVisible(true);
    }

    private void build() {
        JPanel main = new JPanel(new BorderLayout(10, 10));
        main.setBackground(BACKGROUND);
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        JLabel title = new JLabel("❤️ My Library");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
        JButton refresh = new JButton("🔄 Refresh");
        refresh.addActionListener(e -> { dispose(); new LibraryUI(parent, bookManager); });
        header.add(title, BorderLayout.WEST);
        header.add(refresh, BorderLayout.EAST);
        main.add(header, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("❤️ Favorites", createPanel(bookManager.getFavoriteBooks()));
        tabs.addTab("📖 To Be Read", createPanel(bookManager.getBooksByStatus("To Be Read")));
        tabs.addTab("📚 Currently Reading", createPanel(bookManager.getBooksByStatus("Currently Reading")));
        tabs.addTab("✅ Finished", createPanel(bookManager.getBooksByStatus("Finished")));
        main.add(tabs, BorderLayout.CENTER);
        setContentPane(main);
    }

    private JPanel createPanel(List<Book> books) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        if (books.isEmpty()) {
            JLabel empty = new JLabel("No books in this section yet. 📚");
            empty.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createVerticalStrut(30));
            panel.add(empty);
            return panel;
        }

        for (Book book : books) {
            JPanel card = new JPanel(new BorderLayout(10, 5));
            card.setBackground(Color.WHITE);
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 75));
            card.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(BORDER),
                    BorderFactory.createEmptyBorder(10, 15, 10, 15)));

            JLabel title = new JLabel("📖 " + book.getTitle());
            title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
            JLabel info = new JLabel("📚 " + book.getGenre() + "    |    📌 " + book.getReadingStatus());

            JPanel text = new JPanel();
            text.setOpaque(false);
            text.setLayout(new BoxLayout(text, BoxLayout.Y_AXIS));
            text.add(title);
            text.add(Box.createVerticalStrut(5));
            text.add(info);

            JButton view = new JButton("View");
            view.addActionListener(e -> new BookDetailsUI(this, bookManager, book));

            card.add(text, BorderLayout.CENTER);
            card.add(view, BorderLayout.EAST);
            panel.add(card);
            panel.add(Box.createVerticalStrut(10));
        }
        return panel;
    }
}
