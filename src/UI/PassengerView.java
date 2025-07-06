package UI;

import Models.Flight;
import Models.Passenger;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PassengerView extends JPanel {
    private final SkyPortManager manager;
    private final Passenger passenger;
    private final DefaultTableModel tableModel;

    public PassengerView(SkyPortManager manager, Passenger passenger) {
        this.manager = manager;
        this.passenger = passenger;
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("AVAILABLE FLIGHTS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        JButton bookBtn = new JButton("BOOK SELECTED FLIGHT");
        bookBtn.setBackground(new Color(218, 165, 32));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(bookBtn, BorderLayout.SOUTH);

        String[] columns = {"FLIGHT NO", "ORIGIN", "DESTINATION", "DEPARTURE", "ARRIVAL", "DATE", "AVAILABLE SEATS"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // make table read-only
            }
        };
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Populate flights initially
        loadFlights();

        bookBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    // Extract flight number as string
                    String flightNo = table.getValueAt(selectedRow, 0).toString();

                    int availableSeats = Integer.parseInt(table.getValueAt(selectedRow, 6).toString());
                    if (availableSeats <= 0) {
                        JOptionPane.showMessageDialog(this, "No seats available on this flight.", "Booking Failed", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    boolean booked = manager.getBookingService().bookFlight(passenger.getUserId(), flightNo);
                    if (booked) {
                        JOptionPane.showMessageDialog(this, "Flight booked successfully!", "Booking Success", JOptionPane.INFORMATION_MESSAGE);
                        table.setValueAt(availableSeats - 1, selectedRow, 6);
                    } else {
                        JOptionPane.showMessageDialog(this, "Booking failed: flight is full or unavailable.", "Booking Failed", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Booking failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a flight to book.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    // ✅ THIS METHOD MUST BE OUTSIDE THE CONSTRUCTOR
    private void loadFlights() {
        try {
            tableModel.setRowCount(0); // clear existing data
            List<Flight> flights = manager.getFlightService().getAllFlights();
            for (Flight f : flights) {
                Object[] row = {
                        f.getFlightNo(),
                        f.getOrigin(),
                        f.getDestination(),
                        f.getDepartureTime(),
                        f.getArrivalTime(),
                        f.getDate(),
                        f.getCapacity()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading flights: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}