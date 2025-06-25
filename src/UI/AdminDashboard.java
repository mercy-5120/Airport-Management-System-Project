package UI;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JPanel {
    public AdminDashboard(CardLayout layout, JPanel container) {
        setLayout(new BorderLayout());

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(170, 153, 146));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setPreferredSize(new Dimension(180, 0));

        ImageIcon originalIcon = new ImageIcon("Src/Assets/logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel logo = new JLabel(new ImageIcon(scaledImage));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));
        sidebar.add(logo);
        sidebar.add(Box.createRigidArea(new Dimension(0, 20)));

        JButton addFlightBtn = new JButton("ADD FLIGHT");
        UIUtils.styleButtonDash(addFlightBtn);
        sidebar.add(addFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton editFlightBtn = new JButton("EDIT FLIGHT");
        UIUtils.styleButtonDash(editFlightBtn);
        sidebar.add(editFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton deleteFlightBtn = new JButton("DELETE FLIGHT");
        UIUtils.styleButtonDash(deleteFlightBtn);
        sidebar.add(deleteFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton viewPassengersBtn = new JButton("VIEW PASSENGERS");
        UIUtils.styleButtonDash(viewPassengersBtn);
        sidebar.add(viewPassengersBtn);
        sidebar.add(Box.createVerticalGlue());

        JButton logoutBtn = new JButton("LOG OUT ⤴");
        UIUtils.styleButton(logoutBtn);
        logoutBtn.setMaximumSize(new Dimension(160, 40));
        logoutBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logoutBtn);
        sidebar.add(Box.createVerticalStrut(20));

        // Main content area with CardLayout
        CardLayout mainCardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(mainCardLayout);
        mainPanel.setBackground(Color.WHITE);
        mainPanel.add(new AdminAddFlight(), "add");
        mainPanel.add(new AdmEdit(), "edit");
        mainPanel.add(new AdmEdit(), "delete");
        mainPanel.add(new AdmView(), "view");

        // Default panel
        mainCardLayout.show(mainPanel, "add");

        // Sidebar button actions
        addFlightBtn.addActionListener(e -> mainCardLayout.show(mainPanel, "add"));
        editFlightBtn.addActionListener(e -> mainCardLayout.show(mainPanel, "edit"));
        deleteFlightBtn.addActionListener(e -> mainCardLayout.show(mainPanel, "delete"));
        viewPassengersBtn.addActionListener(e -> mainCardLayout.show(mainPanel, "view"));

        // Logout action
        logoutBtn.addActionListener(e ->
                layout.show(container, "login"));

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }





}