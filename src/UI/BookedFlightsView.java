package UI;

import Models.Booking;
import Models.Flight;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookedFlightsView extends JPanel {
    private final SkyPortManager manager;
    private final int userId;
    private final DefaultTableModel tableModel;
    private JTable table;

    public BookedFlightsView(SkyPortManager manager, int userId) {
        this.manager = manager;
        this.userId = userId;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("YOUR BOOKED FLIGHTS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        String[] columns = {
                "Booking ID", "Flight Number", "Status",
                "Origin", "Destination", "Departure", "Arrival", "Date", "Price"
        };

        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton cancelBtn = new JButton("CANCEL SELECTED BOOKING");
        cancelBtn.setEnabled(false);
        cancelBtn.setBackground(new Color(218, 165, 32));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(cancelBtn, BorderLayout.SOUTH);

        // Enable cancel button when a row is selected
        table.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                cancelBtn.setEnabled(table.getSelectedRow() != -1);
            }
        });

        // Cancel button action
        cancelBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a booking to cancel.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String bookingId = table.getValueAt(selectedRow, 0).toString();
            String flightNo = table.getValueAt(selectedRow, 1).toString();

            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to cancel booking ID " + bookingId + " for flight " + flightNo + "?",
                    "Confirm Cancel", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                try {
                    manager.getBookingService().cancelBooking(userId, flightNo);
                    JOptionPane.showMessageDialog(this, "Booking cancelled successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                    refresh();
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error cancelling booking: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        refresh();  // load data initially
    }

    public void refresh() {
        tableModel.setRowCount(0); // clear old data

        try {
            List<Booking> bookings = manager.getBookingService().viewBookingHistory(userId);
            for (Booking b : bookings) {
                Flight f = b.getFlight();
                if (f != null) {
                    Object[] row = {
                            b.getBookingId(),
                            b.getFlightNo(),
                            b.getStatus(),
                            f.getOrigin(),
                            f.getDestination(),
                            f.getDepartureTime(),
                            f.getArrivalTime(),
                            f.getDate(),
                            f.getPrice()
                    };
                    tableModel.addRow(row);
                } else {
                    Object[] row = {
                            b.getBookingId(),
                            b.getFlightNo(),
                            b.getStatus(),
                            "N/A","N/A","N/A","N/A","N/A","N/A"
                    };
                    tableModel.addRow(row);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error refreshing booked flights: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
