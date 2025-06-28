package UI;

import Models.Passenger;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdmView extends JPanel {
    public AdmView(SkyPortManager manager) {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("VIEW PASSENGERS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        // Define table columns
        String[] columns = {"PASSENGER ID", "NAME", "EMAIL"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table read-only
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        try {
            // Fetch passengers from the database
            List<Passenger> passengers = manager.getAdminService().getAllPassengers();
            for (Passenger p : passengers) {
                Object[] row = { p.getFullname(), p.getEmail()};
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading passengers: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
