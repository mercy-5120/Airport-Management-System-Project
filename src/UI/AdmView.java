package UI;

import Models.Flight;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdmView extends JPanel {
    private final SkyPortManager manager;
    private final DefaultTableModel tableModel;

    public AdmView(SkyPortManager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("VIEW FLIGHTS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        // Define table columns
        String[] columns = {
                "FLIGHT ID", "FLIGHT NO", "ORIGIN", "DESTINATION",
                "DEPARTURE", "ARRIVAL", "DATE", "AVAILABLE SEATS", "PRICE"
        };
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table read-only
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        refresh(); // load data on startup
    }

    /**
     * Refreshes the flights table with the latest data from the database.
     */
    public void refresh() {
        tableModel.setRowCount(0); // clear existing rows
        try {
            List<Flight> flights = manager.getFlightService().getAllFlights();
            for (Flight f : flights) {
                Object[] row = {
                        f.getFlightID(),
                        f.getFlightNo(),
                        f.getOrigin(),
                        f.getDestination(),
                        f.getDepartureTime(),
                        f.getArrivalTime(),
                        f.getDate(),
                        f.getCapacity(),
                        f.getPrice()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading flights: " + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
