package UI;

import Models.Passenger;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class PassengerDashboard extends JPanel {
    private SkyPortManager manager;

    public PassengerDashboard(CardLayout layout, JPanel container, SkyPortManager manager, Passenger passenger) {
        this.manager = manager;
        setLayout(new BorderLayout());



        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(92, 78, 78));
        sidebar.setPreferredSize(new Dimension(180, 0));

        // Logo
        ImageIcon originalIcon = new ImageIcon("Src/Assets/logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledImage));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(logo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        // Sidebar buttons

        JButton viewFlightBtn = new JButton("VIEW FLIGHTS");
        viewFlightBtn.setBackground(new Color(92, 78, 78));
        UIUtils.styleButtonDash(viewFlightBtn);
        sidebar.add(viewFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton bookedFlightBtn = new JButton("BOOKED FLIGHTS");
        UIUtils.styleButtonDash(bookedFlightBtn);
        sidebar.add(bookedFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton historyFlightBtn = new JButton("BOOKING HISTORY ");
        UIUtils.styleButtonDash(historyFlightBtn);
        sidebar.add(historyFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));


        JButton logoutBtn = new JButton("LOG OUT ⤴");
        UIUtils.styleButton(logoutBtn);
        logoutBtn.setMaximumSize(new Dimension(160, 40));
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logoutBtn);
        sidebar.add(Box.createVerticalStrut(20));

        // Main content panel
        CardLayout mainCardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(mainCardLayout);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(new PassengerView(manager,passenger), "view");
        mainPanel.add(new BookedFlightsView(manager, passenger.getUserId()), "booked");
        mainPanel.add(new BookingHistoryView(manager, passenger.getUserId()), "history");




        mainCardLayout.show(mainPanel, "view");

        // Sidebar button actions
        viewFlightBtn.addActionListener(e -> mainCardLayout.show(mainPanel, "view"));
        bookedFlightBtn.addActionListener(e -> {
            BookedFlightsView bookedFlightsView = (BookedFlightsView) mainPanel.getComponent(1);
            bookedFlightsView.refresh();  // refresh when opening booked flights
            mainCardLayout.show(mainPanel, "booked");
        });

        historyFlightBtn.addActionListener(e -> mainCardLayout.show(mainPanel, "history"));

        logoutBtn.addActionListener(e ->
                layout.show(container, "login"));

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Inter", Font.PLAIN, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(new Color(92, 78, 78));
        btn.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        btn.setFocusPainted(false);
    }

    }

