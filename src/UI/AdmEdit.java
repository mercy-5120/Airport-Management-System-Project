package UI;

import Models.Flight;
import Services.IAdminService;
import Services.IFlightService;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class AdmEdit extends JPanel {
    private IFlightService flightService;
    private IAdminService adminService;
    private DefaultTableModel model;

    public AdmEdit(SkyPortManager manager) {
        this.flightService = manager.getFlightService();
        this.adminService = manager.getAdminService();
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("FLIGHTS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        String[] columns = {"FLIGHT NO", "ORIGIN", "DESTINATION", "DEPARTURE", "ARRIVAL", "DATE", "AVAILABLE SEATS", "PRICE"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton deleteBtn = new JButton("DELETE SELECTED FLIGHT");
        deleteBtn.setEnabled(false);
        deleteBtn.setBackground(new Color(218, 165, 32));
        deleteBtn.setForeground(Color.WHITE);
        deleteBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(deleteBtn, BorderLayout.SOUTH);

        deleteBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a flight to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String flightNo = table.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete flight " + flightNo + "?",
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    List<Flight> flights = flightService.getAllFlights();
                    Flight flightToDelete = flights.stream()
                            .filter(f -> f.getFlightNo().equals(flightNo))
                            .findFirst().orElse(null);

                    if (flightToDelete != null) {
                        adminService.deleteFlight(flightToDelete);
                        model.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(this, "Flight deleted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Flight not found in database.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting flight: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                deleteBtn.setEnabled(table.getSelectedRow() != -1);
            }
        });

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow >= 0) {
                        Object[] rowData = new Object[table.getColumnCount()];
                        for (int i = 0; i < table.getColumnCount(); i++) {
                            rowData[i] = table.getValueAt(selectedRow, i);
                        }
                        new EditFlightDialog(SwingUtilities.getWindowAncestor(AdmEdit.this), rowData, selectedRow, table, adminService).setVisible(true);
                    }
                }
            }
        });

        refresh(); // load initial data
    }

    /**
     * Reloads the flights table with fresh data from the database.
     */
    public void refresh() {
        model.setRowCount(0); // clear existing rows
        try {
            List<Flight> flights = flightService.getAllFlights();
            for (Flight f : flights) {
                Object[] rowData = {
                        f.getFlightNo(),
                        f.getOrigin(),
                        f.getDestination(),
                        f.getDepartureTime(),
                        f.getArrivalTime(),
                        f.getDate(),
                        f.getCapacity(),
                        f.getPrice()
                };
                model.addRow(rowData);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error refreshing flights: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
