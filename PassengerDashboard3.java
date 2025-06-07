import javax.swing.*;
import java.awt.*;

public class PassengerDashboard3 extends JPanel {
    private JFrame parentFrame;

    public PassengerDashboard3(String username, JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

        // Sidebar (same style)
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(92, 78, 78));
        sidebar.setPreferredSize(new Dimension(150, 0));

        ImageIcon originalIcon = new ImageIcon("Src/Assets/logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledImage));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(logo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        String[] buttons = {"VIEW FLIGHTS", "BOOK FLIGHT", "CANCEL FLIGHT", "BOOKING HISTORY", "LOG OUT"};
        for (String btnText : buttons) {
            JButton btn = new JButton(btnText);
            btn.setMaximumSize(new Dimension(140, 40));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            styleButton(btn);

            if (btnText.equals("LOG OUT")) {
                sidebar.add(Box.createVerticalGlue());
                btn.setForeground(Color.WHITE);
                btn.setBackground(Color.decode("#E0BD3B"));
                btn.addActionListener(e -> System.exit(0));
                sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
                sidebar.add(btn);
            } else if (btnText.equals("VIEW FLIGHTS")) {
                btn.addActionListener(e -> {
                    parentFrame.setContentPane(new PassengerDashboard2(username, parentFrame));
                    parentFrame.revalidate();
                });
            }
            // You can add more button listeners here if needed

            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(btn);
        }

        // Main panel content
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel welcome = new JLabel("WELCOME " + username.toUpperCase(), SwingConstants.CENTER);
        welcome.setFont(new Font("Inter", Font.BOLD, 18));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        topPanel.add(welcome);

        JLabel sectionTitle = new JLabel("CANCEL FLIGHT", SwingConstants.CENTER);
        sectionTitle.setFont(new Font("Inter", Font.BOLD, 16));
        sectionTitle.setForeground(Color.decode("#E0BD3B"));
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        topPanel.add(sectionTitle);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JLabel noFlightsLabel = new JLabel("NO BOOKED FLIGHTS", SwingConstants.CENTER);
        noFlightsLabel.setFont(new Font("Inter", Font.BOLD, 20));
        noFlightsLabel.setForeground(Color.BLACK);
        mainPanel.add(noFlightsLabel, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Inter", Font.PLAIN, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(92, 78, 78));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btn.setFocusPainted(false);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Passenger Dashboard3");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new PassengerDashboard("UserName", frame));
            frame.setVisible(true);
        });
    }
}
