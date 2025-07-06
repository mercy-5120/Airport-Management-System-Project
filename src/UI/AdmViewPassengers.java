package UI;

import Models.Passenger;
import Services.IAdminService;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdmViewPassengers extends JPanel {
    private final IAdminService adminService;
    private final DefaultTableModel tableModel;

    public AdmViewPassengers(SkyPortManager manager) {
        this.adminService = manager.getAdminService();

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("VIEW PASSENGERS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        String[] columns = {"PASSENGER ID", "NAME", "EMAIL"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        refresh();  // load data initially
    }

    public void refresh() {
        tableModel.setRowCount(0);  // clear existing data
        try {
            List<Passenger> passengers = adminService.getAllPassengers();
            for (Passenger p : passengers) {
                Object[] row = {
                        p.getUserId(),
                        p.getFullname(),
                        p.getEmail()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading passengers: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
