package UI;

import javax.swing.*;
import java.awt.*;

public class AdminAddFlight extends JPanel {
    public AdminAddFlight() {
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton addBtn = new JButton("ADD");
        UIUtils.styleButton(addBtn);
        add(addBtn, gbc);
    }
}