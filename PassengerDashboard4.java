import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;

public class PassengerDashboard4 extends JPanel {
    public PassengerDashboard4(String username, CardLayout layout, JPanel container) {
        setLayout(new BorderLayout());
        add(createSidebar(username, layout, container), BorderLayout.WEST);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(createHeader(username, "BOOKING HISTORY"), BorderLayout.NORTH);

        String[] columns = {"FLIGHT ID", "ORIGIN", "DESTINATION", "DATE", "TICKET ID", "STATUS"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
        JTable flightTable = new JTable(tableModel);

        flightTable.setFont(new Font("Inter", Font.PLAIN, 14));
        flightTable.setRowHeight(20);
        flightTable.setShowGrid(true);
        flightTable.setGridColor(Color.GRAY);
        flightTable.setBackground(new Color(245, 245, 245));
        flightTable.setForeground(Color.BLACK);

        JTableHeader header = flightTable.getTableHeader();
        header.setFont(new Font("Inter", Font.BOLD, 14));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < flightTable.getColumnCount(); i++) {
            flightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JPanel createSidebar(String username, CardLayout layout, JPanel container) {
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
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

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
                btn.addActionListener(e -> layout.show(container, "login"));
                sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
                sidebar.add(btn);
                continue;
            }
            if (btnText.equals("VIEW FLIGHTS")) {
                btn.addActionListener(e -> layout.show(container, "dashboard"));
            }
            if (btnText.equals("CANCEL FLIGHT")) {
                btn.addActionListener(e -> layout.show(container, "dashboard2"));
            }
            if (btnText.equals("BOOKING HISTORY")) {
                btn.addActionListener(e -> layout.show(container, "dashboard4"));
            }
            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(btn);
        }
        return sidebar;
    }

    private JPanel createHeader(String username, String sectionTitleText) {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setBackground(Color.WHITE);

        JLabel welcome = new JLabel("WELCOME " + username.toUpperCase(), SwingConstants.CENTER);
        welcome.setFont(new Font("Inter", Font.BOLD, 18));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        headerPanel.add(welcome);

        JLabel sectionTitle = new JLabel(sectionTitleText, SwingConstants.CENTER);
        sectionTitle.setFont(new Font("Inter", Font.BOLD, 16));
        sectionTitle.setForeground(new Color(218, 165, 32));
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        headerPanel.add(sectionTitle);

        return headerPanel;
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
            JFrame frame = new JFrame("Passenger Dashboard4");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new PassengerDashboard("UserName", frame));
            frame.setVisible(true);
        });
    }
}
