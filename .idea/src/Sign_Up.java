import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Sign_Up extends JPanel {
    public Sign_Up(CardLayout layout, JPanel container) {
        setLayout(new BorderLayout());

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

        // Sign-up area
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("USER REGISTRATION");
        UIUtils.styleTitle(titleLabel);

        JLabel nameLabel = new JLabel("FULL NAME");
        JTextField fullNameField = new JTextField();
        JLabel emailLabel = new JLabel("EMAIL");
        JTextField emailField = new JTextField();

        JLabel passLabel = new JLabel("PASSWORD");
        JPasswordField passwordField = new JPasswordField();

        JLabel[] labels = { nameLabel, emailLabel,  passLabel };
        JTextField[] fields = { fullNameField, emailField};
        for (JLabel label : labels) UIUtils.styleLabel(label);
        for (JTextField field : fields) UIUtils.styleField(field);
        UIUtils.styleField(passwordField);

        JLabel signIn = UIUtils.createLinkLabel("Sign in");
        JLabel togglePass = UIUtils.createShowPasswordToggle(passwordField);

        JPanel linkPanel = new JPanel();
        linkPanel.setLayout(new BoxLayout(linkPanel, BoxLayout.X_AXIS));
        linkPanel.setOpaque(false);
        linkPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        linkPanel.add(signIn);
        linkPanel.add(Box.createHorizontalGlue());
        linkPanel.add(togglePass);

        JButton signUp = new JButton("Sign Up");
        UIUtils.styleButton(signUp);

        signUp.addActionListener(e -> {
            if (fullNameField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() ||
                   new String(passwordField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            } else {
                System.out.println("Registered: " + fullNameField.getText().trim());
            }
        });

        signIn.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                layout.show(container, "login");
            }
        });

        formPanel.add(titleLabel);
        formPanel.add(nameLabel);
        formPanel.add(fullNameField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);

        formPanel.add(passLabel);
        formPanel.add(passwordField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(linkPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(signUp);

        add(formPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.SOUTH);
    }
}
