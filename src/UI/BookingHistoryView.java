package UI;

import Models.Booking;
import Models.Flight;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookingHistoryView extends JPanel {
    private final SkyPortManager manager;
    private final int userId;

    public BookingHistoryView(SkyPortManager manager, int userId) {
        this.manager = manager;
        this.userId = userId;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("BOOKING HISTORY", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        String[] columns = {
                "Booking ID", "Flight Number", "Status",
                "Origin", "Destination", "Departure", "Arrival", "Date", "Price"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        try {
            List<Booking> bookings = manager.getBookingService().viewBookingHistory(userId);
            for (Booking b : bookings) {
                Flight f = b.getFlight();
                Object[] row;
                if (f != null) {
                    row = new Object[]{
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
                } else {
                    row = new Object[]{
                            b.getBookingId(),
                            b.getFlightNo(),
                            b.getStatus(),
                            "N/A", "N/A", "N/A", "N/A", "N/A", "N/A"
                    };
                }
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading booking history: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
