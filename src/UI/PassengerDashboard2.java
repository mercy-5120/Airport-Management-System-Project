package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class PassengerDashboard2 extends JPanel {
    private JFrame parentFrame;
    private DefaultTableModel tableModel;
    private JTable flightTable;
    private String username;

    public PassengerDashboard2(String username, JFrame parentFrame) {
        this.username = username;
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());

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
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));

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
            } else if (btnText.equals("VIEW FLIGHTS")) {
                btn.addActionListener(e -> {
                    // Example navigation (assuming PassengerDashboard exists)
                    parentFrame.setContentPane(new PassengerDashboard(username, parentFrame));
                    parentFrame.revalidate();
                });
            }
            // Add other buttons if needed

            sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
            sidebar.add(btn);
        }

        // Main area
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);

        JLabel welcome = new JLabel("WELCOME " + username.toUpperCase(), SwingConstants.CENTER);
        welcome.setFont(new Font("Inter", Font.BOLD, 18));
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setBorder(BorderFactory.createEmptyBorder(20, 0, 5, 0));
        topPanel.add(welcome);

        JLabel sectionTitle = new JLabel("CANCEL FLIGHT", SwingConstants.CENTER);
        sectionTitle.setFont(new Font("Inter", Font.BOLD, 16));
        sectionTitle.setForeground(Color.decode("#E0BD3B"));
        sectionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        sectionTitle.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        topPanel.add(sectionTitle);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // Table setup
        String[] column = {"FLIGHT NO", "ORIGIN", "DESTINATION", "DEPARTURE", "ARRIVAL", "DATE", "SEAT ID", "TICKET ID", "STATUS"};
        tableModel = new DefaultTableModel(column, 0);
        flightTable = new JTable(tableModel);

        flightTable.setFont(new Font("Inter", Font.PLAIN, 14));
        flightTable.setRowHeight(30);
        flightTable.setShowGrid(true);
        flightTable.setGridColor(Color.GRAY);
        flightTable.setBackground(Color.WHITE);
        flightTable.setForeground(Color.BLACK);

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
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Inter", Font.BOLD, 14));
        cancelButton.setBackground(new Color(92, 78, 78));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);
        cancelButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cancelButton.addActionListener(e -> cancelSelectedFlight());

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(cancelButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Add to main frame
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }

    private void cancelSelectedFlight() {
        int selectedRow = flightTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a flight to cancel.");
            return;
        }

        String status = (String) tableModel.getValueAt(selectedRow, 8);
        if ("CANCELLED".equalsIgnoreCase(status)) {
            JOptionPane.showMessageDialog(this, "This flight is already cancelled.");
            return;
        }

        String flightNo = (String) tableModel.getValueAt(selectedRow, 0);
        tableModel.setValueAt("CANCELLED", selectedRow, 8);
        JOptionPane.showMessageDialog(this, "Flight " + flightNo + " has been cancelled successfully.");

        // Check if all flights are cancelled
        if (allFlightsCancelled()) {
            // Switch to PassengerDashboard3 (no booked flights)
            parentFrame.setContentPane(new PassengerDashboard3(username, parentFrame));
            parentFrame.revalidate();
        }
    }

    private boolean allFlightsCancelled() {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String status = (String) tableModel.getValueAt(i, 8);
            if (!"CANCELLED".equalsIgnoreCase(status)) {
                return false;
            }
        }
        return true;
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
            JFrame frame = new JFrame("Passenger Dashboard2");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new PassengerDashboard("UserName", frame));
            frame.setVisible(true);
        });
    }
}