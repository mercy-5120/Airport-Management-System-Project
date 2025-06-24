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

        JLabel logo = new JLabel(new ImageIcon("Src/Assets/logo.png"));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(Box.createVerticalStrut(30));
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(30));

        JButton addFlightBtn = new JButton("ADD FLIGHT");
        UIUtils.styleButton(addFlightBtn);
        addFlightBtn.setMaximumSize(new Dimension(160, 40));
        addFlightBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(addFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton editFlightBtn = new JButton("EDIT FLIGHT");
        UIUtils.styleButton(editFlightBtn);
        editFlightBtn.setMaximumSize(new Dimension(160, 40));
        editFlightBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(editFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton deleteFlightBtn = new JButton("DELETE FLIGHT");
        UIUtils.styleButton(deleteFlightBtn);
        deleteFlightBtn.setMaximumSize(new Dimension(160, 40));
        deleteFlightBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(deleteFlightBtn);
        sidebar.add(Box.createVerticalStrut(10));

        JButton viewPassengersBtn = new JButton("VIEW PASSENGERS");
        UIUtils.styleButton(viewPassengersBtn);
        viewPassengersBtn.setMaximumSize(new Dimension(160, 40));
        viewPassengersBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
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
        logoutBtn.addActionListener(e -> layout.show(container, "login"));

        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);
    }
}