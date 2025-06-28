package UI;

import Models.Flight;
import Models.FlightStatus;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AdminAddFlight extends JPanel {
    private final SkyPortManager manager;

    public AdminAddFlight(SkyPortManager manager) {
        this.manager = manager;
        setLayout(new GridBagLayout());
        setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel title = new JLabel("ADD FLIGHTS");
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        gbc.gridwidth = 2;
        gbc.gridx = 0; gbc.gridy = 0;
        add(title, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;
        add(new JLabel("FLIGHT NO:"), gbc);
        JTextField flightNo = new JTextField(15);
        gbc.gridx = 1;
        add(flightNo, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("ORIGIN:"), gbc);
        JTextField origin = new JTextField(15);
        gbc.gridx = 1;
        add(origin, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("DESTINATION:"), gbc);
        JTextField destination = new JTextField(15);
        gbc.gridx = 1;
        add(destination, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("DEPARTURE:"), gbc);
        JTextField departure = new JTextField(15);
        gbc.gridx = 1;
        add(departure, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("ARRIVAL:"), gbc);
        JTextField arrival = new JTextField(15);
        gbc.gridx = 1;
        add(arrival, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("DATE:"), gbc);
        JTextField date = new JTextField(15);
        gbc.gridx = 1;
        add(date, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("SEATS:"), gbc);
        JTextField seats = new JTextField(15);
        gbc.gridx = 1;
        add(seats, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("PRICE:"), gbc);
        JTextField price = new JTextField(15);
        gbc.gridx = 1;
        add(price, gbc);

        gbc.gridx = 0; gbc.gridy++;
        add(new JLabel("STATUS:"), gbc);
        JComboBox<FlightStatus> statusCombo = new JComboBox<>(FlightStatus.values());
        gbc.gridx = 1;
        add(statusCombo, gbc);

        gbc.gridx = 0; gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addBtn = new JButton("ADD");
        UIUtils.styleButton(addBtn);
        add(addBtn, gbc);

        addBtn.addActionListener(e -> {
            try {
                String flightNumber = flightNo.getText().trim();
                String flightOrigin = origin.getText().trim();
                String flightDestination = destination.getText().trim();
                String flightDeparture = departure.getText().trim();
                String flightArrival = arrival.getText().trim();
                LocalDate flightDate = LocalDate.parse(date.getText().trim());
                int flightSeats = Integer.parseInt(seats.getText().trim());
                BigDecimal flightPrice = BigDecimal.valueOf(Double.parseDouble(price.getText().trim()));
                FlightStatus flightStatus = (FlightStatus) statusCombo.getSelectedItem();

                if (flightNumber.isEmpty() || flightOrigin.isEmpty() || flightDestination.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Flight flight = new Flight(
                        flightNumber,
                        flightOrigin,
                        flightDeparture,
                        flightDestination,
                        flightArrival,
                        flightDate,
                        flightSeats,
                        flightPrice
                );

                manager.getAdminService().addFlight(flight);

                JOptionPane.showMessageDialog(this, "Flight added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error adding flight: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
