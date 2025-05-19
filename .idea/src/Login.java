import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {
    public Login(CardLayout layout, JPanel container) {
        setLayout(new BorderLayout());

        // Image Panel
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Image image=new ImageIcon("Assets/airport.jpg").getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };



        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("USER LOGIN");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        UIUtils.styleField(emailField);
        UIUtils.styleField(passwordField);

        JLabel emailLabel = new JLabel("EMAIL");
        JLabel passLabel = new JLabel("PASSWORD");

        UIUtils.styleLabel(emailLabel);
        UIUtils.styleLabel(passLabel);

        JButton loginButton = new JButton("Login");
        UIUtils.styleButton(loginButton);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                System.out.println("Welcome: " + email);
                // Proceed to dashboard logic if needed
            }
        });

        // Links: Sign Up & Show Password
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linkPanel.setOpaque(false);
        JLabel signUpLink = new JLabel("<HTML><U>Sign Up</U></HTML>");
        JLabel togglePass = new JLabel("<HTML><U>Show Password</U></HTML>");
        signUpLink.setForeground(Color.BLUE);
        togglePass.setForeground(Color.BLUE);

        signUpLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        togglePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        signUpLink.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                layout.show(container, "signup");
            }
        });

        togglePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setEchoChar(passwordField.getEchoChar() == 0 ? '•' : (char) 0);
            }
        });

        linkPanel.add(signUpLink);
        linkPanel.add(Box.createHorizontalStrut(20));
        linkPanel.add(togglePass);

        // Assemble Form
        formPanel.add(titleLabel);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(linkPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(loginButton);

        // Add panels
        add(imagePanel, BorderLayout.CENTER);
        add(formPanel, BorderLayout.SOUTH);
    }
}
