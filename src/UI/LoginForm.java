package UI;

import Models.Passenger;
import Models.Role;
import Models.User;
import Services.ILoginService;
import SkyportManager.SkyPortManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends JPanel {
    private SkyPortManager manager;
    public LoginForm(CardLayout layout, JPanel container,SkyPortManager manager) {
        setLayout(new BorderLayout());
this.manager=manager;
        // image
        JPanel imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("Src/Assets/airport.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
            }

            public Dimension getPreferredSize() {
                return new Dimension(0, 300);
            }
        };

        // Login area
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("USER LOGIN");
        UIUtils.styleTitle(titleLabel);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("EMAIL");
        emailPanel.add(emailLabel);
        UIUtils.stylePanel(emailPanel);

        JPanel emailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField emailField = new JTextField();
        emailFieldPanel.add(emailField);
        UIUtils.stylePanel(emailFieldPanel);


        JPanel passPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("PASSWORD");
        passPanel.add(passLabel);
        UIUtils.stylePanel(passPanel);

        JPanel passFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPasswordField passwordField = new JPasswordField();
        passFieldPanel.add(passwordField);
        UIUtils.stylePanel(passFieldPanel);

        UIUtils.styleLabel(emailLabel);
        UIUtils.styleLabel(passLabel);
        UIUtils.styleField(emailField);
        UIUtils.styleField(passwordField);

        JLabel signUpLink = UIUtils.createLinkLabel("Sign up");
        JLabel togglePass = UIUtils.createShowPasswordToggle(passwordField);

        JPanel linkPanel = new JPanel(new BorderLayout());
        linkPanel.setOpaque(false);
        linkPanel.setMaximumSize(new Dimension(400, 30));
        linkPanel.add(signUpLink, BorderLayout.WEST);
        linkPanel.add(togglePass, BorderLayout.EAST);

        JButton loginButton = new JButton("Login");
        UIUtils.styleButton(loginButton);

        loginButton.addActionListener(e -> {
            try {
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                User user = manager.getLoginService().loginUser(email, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(this, "Login successful. Welcome, " + user.getFullname() + "!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);

                    if (user.getRole() == Role.passenger) {
                        Passenger passenger = manager.getPassengerService().getPassengerByEmail(email);
                        container.add(new PassengerDashboard(layout, container, manager,passenger), "PassengerDashboard");

                        layout.show(container, "PassengerDashboard");
                    } else if (user.getRole() == Role.admin) {
                        container.add(new AdminDashboard(layout, container, manager), "AdminDashboard");

                        layout.show(container, "AdminDashboard");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid email or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace(); // See exact error in terminal
                JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });



        signUpLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                layout.show(container, "signup");
            }
        });

        formPanel.add(titleLabel);
        formPanel.add(emailPanel);
        formPanel.add(emailFieldPanel);
        formPanel.add(passPanel);
        formPanel.add(passFieldPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(linkPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(loginButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(formPanel);

        add(imagePanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);
    }
}
