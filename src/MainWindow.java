import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

import SkyportManager.SkyPortManager;
import UI.*;
import dbUtil.DatabaseManager;

public class MainWindow extends JFrame {
    public MainWindow(SkyPortManager manager) {
        setTitle("SkyPort Limited");
        setSize(1000, 800); // updated for better dashboard space
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("Src/Assets/logo.png").getImage());

        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        // Pass the manager to forms:
        container.add(new LoginForm(cardLayout, container, manager), "login");
        container.add(new SignUpForm(cardLayout, container, manager), "signup");

        // Add dashboards to the container so CardLayout knows them:
//        container.add(new PassengerDashboard("Passenger", this), "PassengerDashboard");
        container.add(new AdminDashboard(cardLayout, container, manager), "AdminDashboard");

        add(container);

        cardLayout.show(container, "login");  // start with login screen
        setVisible(true);
    }

    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        try {
            dbManager.connect();
            Connection conn = dbManager.getConnection();

            // Build your SkyPortManager with the established connection:
            SkyPortManager manager = new SkyPortManager(conn);

            // Start the Swing UI, passing manager to MainWindow
            SwingUtilities.invokeLater(() -> new MainWindow(manager));

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to connect to database: " + e.getMessage(),
                    "Database Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1); // Exit if DB connection fails
        }
    }
}
