package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class PassengerDashboard extends JPanel {
    public PassengerDashboard(String username, JFrame parentFrame) {
        setLayout(new BorderLayout());
        JFrame frame = new JFrame("Passenger Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new PassengerDashboard("UserName", frame));


        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(92, 78, 78));
        sidebar.setPreferredSize(new Dimension(150, 0));

        // Logo
        ImageIcon originalIcon = new ImageIcon("Src/Assets/logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledImage));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(logo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // Sidebar buttons
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
                continue;
            }

            // Link CANCEL FLIGHT to PassengerDashboard2
            if (btnText.equals("CANCEL FLIGHT")) {
                btn.addActionListener(e -> {
                    parentFrame.setContentPane(new PassengerDashboard2(username, parentFrame));
                    parentFrame.revalidate();
                });
            }

            // Link BOOKING HISTORY to PassengerDashboard4
            if (btnText.equals("BOOKING HISTORY")) {
                btn.addActionListener(e -> {
                    parentFrame.setContentPane(new PassengerDashboard4(username, parentFrame));
                    parentFrame.revalidate();
                });
            }

            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(btn);
        }

        // Main content panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel welcome = new JLabel("WELCOME " + username.toUpperCase(), SwingConstants.CENTER);
        welcome.setFont(new Font("Inter", Font.BOLD, 18));
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));
        mainPanel.add(welcome, BorderLayout.NORTH);

        JLabel sectionTitle = new JLabel("AVAILABLE FLIGHTS", SwingConstants.CENTER);
        sectionTitle.setFont(new Font("Inter", Font.BOLD, 16));
        sectionTitle.setForeground(new Color(218, 165, 32));
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        mainPanel.add(sectionTitle, BorderLayout.CENTER);

        // Table setup
        String[] column = {"FLIGHT NO", "ORIGIN", "DESTINATION", "DEPARTURE", "ARRIVAL", "DATE", "AVAILABLE SEATS"};
        DefaultTableModel tableModel = new DefaultTableModel(column, 0);
        JTable flightTable = new JTable(tableModel);

        flightTable.setFont(new Font("Inter", Font.PLAIN, 14));
        flightTable.setRowHeight(30);
        flightTable.setShowGrid(true);
        flightTable.setGridColor(Color.GRAY);

        JTableHeader header = flightTable.getTableHeader();
        header.setFont(new Font("Inter", Font.BOLD, 14));
        ((DefaultTableCellRenderer) header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < flightTable.getColumnCount(); i++) {
            flightTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        flightTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    c.setBackground(row % 2 == 0 ? Color.WHITE : new Color(245, 245, 245));
                }
                return c;
            }
        });

        JScrollPane scrollPane = new JScrollPane(flightTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Inter", Font.PLAIN, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(92, 78, 78));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btn.setFocusPainted(false);
    }



}