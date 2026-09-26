import java.awt.*;
import javax.swing.*;

public class LoginUI extends JFrame {
    public LoginUI() {
        setTitle("JudgeByTheCover - Login");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBackground(new Color(248, 246, 242));
        main.setBorder(BorderFactory.createEmptyBorder(50, 55, 50, 55));

        JLabel logo = new JLabel("📚");
        logo.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 55));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("JudgeByTheCover");
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel("Discover. Read. Review.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        Dimension fieldSize = new Dimension(380, 40);
        username.setMaximumSize(fieldSize);
        password.setMaximumSize(fieldSize);
        username.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        password.setFont(new Font("Segoe UI", Font.PLAIN, 15));

        JLabel userLabel = new JLabel("Username");
        JLabel passLabel = new JLabel("Password");
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton login = new JButton("🔐 Login");
        login.setFont(new Font("Segoe UI Emoji", Font.BOLD, 15));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        login.setPreferredSize(new Dimension(150, 42));

        login.addActionListener(e -> {
            String user = username.getText().trim();
            String pass = new String(password.getPassword()).trim();

            if (user.isEmpty() || pass.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Please enter both username and password.",
                        "Login", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this,
                    "Welcome, " + user + "! 📚",
                    "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new MainUI();
        });

        main.add(logo);
        main.add(Box.createVerticalStrut(10));
        main.add(title);
        main.add(Box.createVerticalStrut(5));
        main.add(subtitle);
        main.add(Box.createVerticalStrut(35));
        main.add(userLabel);
        main.add(Box.createVerticalStrut(6));
        main.add(username);
        main.add(Box.createVerticalStrut(18));
        main.add(passLabel);
        main.add(Box.createVerticalStrut(6));
        main.add(password);
        main.add(Box.createVerticalStrut(30));
        main.add(login);

        add(main);
        setVisible(true);
    }
}
