import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sign_Up extends JPanel {
    public Sign_Up(CardLayout layout, JPanel container) {
        setLayout(new BorderLayout());

        // Image Panel
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("assets/airport.jpg");
                if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                    Image image = icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                    g.drawImage(image, 0, 0, this);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                    g.setColor(Color.BLACK);
                    g.drawString("Image not found", 10, 20);
                }
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 150);
            }
        };

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("USER Sign Up");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        // Fields
        JTextField fullNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField roleField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        UIUtils.styleField(fullNameField);
        UIUtils.styleField(emailField);
        UIUtils.styleField(roleField);
        UIUtils.styleField(passwordField);

        JLabel nameLabel = new JLabel("FULL NAME");
        JLabel emailLabel = new JLabel("EMAIL");
        JLabel roleLabel = new JLabel("ROLE");
        JLabel passLabel = new JLabel("PASSWORD");

        UIUtils.styleLabel(nameLabel);
        UIUtils.styleLabel(emailLabel);
        UIUtils.styleLabel(roleLabel);
        UIUtils.styleLabel(passLabel);

        JButton signUp = new JButton("Sign Up");
        UIUtils.styleButton(signUp);
        signUp.setAlignmentX(Component.CENTER_ALIGNMENT);

        signUp.addActionListener(e -> {
            String fullName = fullNameField.getText().trim();
            String email = emailField.getText().trim();
            String role = roleField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            if (fullName.isEmpty() || email.isEmpty() || role.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Please fill in all fields.",
                        "Missing Information",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                System.out.println("Registered: " + fullName);
            }
        });

        // Links
        JPanel linkPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        linkPanel.setOpaque(false);
        JLabel signIn = new JLabel("<HTML><U>Sign in</U></HTML>");
        JLabel togglePass = new JLabel("<HTML><U>Show Password</U></HTML>");
        signIn.setForeground(Color.BLUE);
        togglePass.setForeground(Color.BLUE);
        signIn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        togglePass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        signIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                layout.show(container, "login");
            }
        });

        togglePass.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                passwordField.setEchoChar(passwordField.getEchoChar() == 0 ? '•' : (char) 0);
            }
        });

        linkPanel.add(signIn);
        linkPanel.add(Box.createHorizontalStrut(20));
        linkPanel.add(togglePass);

        // Assembling form
        formPanel.add(Box.createVerticalGlue());
        formPanel.add(titleLabel);
        formPanel.add(nameLabel);
        formPanel.add(fullNameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(roleLabel);
        formPanel.add(roleField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(passLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(linkPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(signUp);
        formPanel.add(Box.createVerticalGlue());

        // Add panels
        add(formPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.SOUTH);
    }
}
