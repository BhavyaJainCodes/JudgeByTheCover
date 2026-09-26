import java.awt.*;
import javax.swing.*;

public class DashboardUI extends JFrame {
    private final BookManager bookManager;
    private final Color BACKGROUND = new Color(248, 246, 242);
    private final Color BORDER = new Color(220, 215, 220);

    public DashboardUI(JFrame parent, BookManager bookManager) {
        this.bookManager = bookManager;
        setTitle("📊 Reading Dashboard");
        setSize(800, 550);
        setLocationRelativeTo(parent);
        build();
        setVisible(true);
    }

    private void build() {
        int total = bookManager.getAllBooks().size();
        int favorites = bookManager.getFavoriteBooks().size();
        int toRead = bookManager.getBooksByStatus("To Be Read").size();
        int reading = bookManager.getBooksByStatus("Currently Reading").size();
        int finished = bookManager.getBooksByStatus("Finished").size();

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(BACKGROUND);
        main.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        JLabel title = new JLabel("📊 Reading Dashboard");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(title);
        main.add(Box.createVerticalStrut(25));

        JPanel stats = new JPanel(new GridLayout(2, 3, 15, 15));
        stats.setOpaque(false);
        stats.setMaximumSize(new Dimension(700, 220));
        stats.add(card("📚", "Total Books", String.valueOf(total)));
        stats.add(card("❤️", "Favorites", String.valueOf(favorites)));
        stats.add(card("📖", "To Be Read", String.valueOf(toRead)));
        stats.add(card("📕", "Currently Reading", String.valueOf(reading)));
        stats.add(card("✅", "Finished", String.valueOf(finished)));
        double completion = total == 0 ? 0 : finished * 100.0 / total;
        stats.add(card("📈", "Completion", String.format("%.1f%%", completion)));
        main.add(stats);

        main.add(Box.createVerticalStrut(30));
        JLabel progressTitle = new JLabel("📈 Reading Progress");
        progressTitle.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
        progressTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(progressTitle);
        main.add(Box.createVerticalStrut(10));

        JProgressBar progress = new JProgressBar(0, Math.max(total, 1));
        progress.setValue(finished);
        progress.setStringPainted(true);
        progress.setString(finished + " of " + total + " books finished");
        progress.setMaximumSize(new Dimension(650, 35));
        progress.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(progress);

        main.add(Box.createVerticalStrut(25));
        JLabel message = new JLabel(finished == 0 ? "🌱 Start your reading journey!" :
                finished < total / 2 ? "📖 Keep going! You're making progress." :
                        "🎉 Great job! You're a dedicated reader.");
        message.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(message);

        setContentPane(new JScrollPane(main));
    }

    private JPanel card(String icon, String title, String value) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(BORDER),
                BorderFactory.createEmptyBorder(12, 8, 12, 8)));

        JLabel i = new JLabel(icon);
        i.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 25));
        i.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel v = new JLabel(value);
        v.setFont(new Font("Segoe UI", Font.BOLD, 22));
        v.setAlignmentX(Component.CENTER_ALIGNMENT);
        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        t.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(i);
        panel.add(v);
        panel.add(t);
        return panel;
    }
}
