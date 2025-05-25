import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JPanel {
    public Login(CardLayout layout, JPanel container) {
        setLayout(new BorderLayout());

        // image
        JPanel imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("Assets/airport.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
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

        JLabel emailLabel = new JLabel("EMAIL");
        JTextField emailField = new JTextField();
        JLabel passLabel = new JLabel("PASSWORD");
        JPasswordField passwordField = new JPasswordField();

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
            if (emailField.getText().trim().isEmpty() || new String(passwordField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("Welcome: " + emailField.getText().trim());
            }
        });

        signUpLink.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                layout.show(container, "signup");
            }
        });

        formPanel.add(titleLabel);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(passLabel);
        formPanel.add(passwordField);
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
