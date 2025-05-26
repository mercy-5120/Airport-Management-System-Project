import javax.swing.*;
import java.awt.*;

public class PassengerDashboard extends JPanel {
    public PassengerDashboard(String username) {
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(92, 78, 78));
        sidebar.setPreferredSize(new Dimension(150, 0));

        //logo
        ImageIcon originalIcon = new ImageIcon("Assets/logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledImage));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

        // SideBar buttons
        String[] buttons = {"VIEW FLIGHTS", "BOOK FLIGHT", "CANCEL FLIGHT", "BOOKING HISTORY", "LOG OUT"};
        for (String btnText : buttons) {
            JButton btn = new JButton(btnText);
            btn.setMaximumSize(new Dimension(140, 40));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            UIUtils.styleButton(btn);

            if (btnText.equals("LOG OUT")) {
                sidebar.add(Box.createVerticalGlue());
                btn.setForeground(Color.WHITE);
                btn.setBackground(Color.decode("#E0BD3B"));
                btn.addActionListener(e -> System.exit(0));
                sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
                sidebar.add(btn);
            }

            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(btn);
        }

        // Main area
        JPanel mainPanel = new JPanel(new BorderLayout());
        JLabel welcome = new JLabel("WELCOME " + username.toUpperCase(), SwingConstants.CENTER);
        welcome.setFont(new Font("Inter", Font.BOLD, 18));
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(welcome, BorderLayout.NORTH);

        JLabel sectionTitle = new JLabel("AVAILABLE FLIGHTS", SwingConstants.CENTER);
        sectionTitle.setFont(new Font("Inter", Font.BOLD, 16));
        sectionTitle.setForeground(new Color(218, 165, 32));
        mainPanel.add(sectionTitle, BorderLayout.CENTER);

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }
}
