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

        // LOGIN LOGIC
        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            if (emailField.getText().trim().isEmpty() || new String(passwordField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
            }
            else if (email.equalsIgnoreCase("admin@skyport.com") && password.equals("admin123")) {
                if (container.getComponentCount() < 3) {
                    container.add(new AdminDashboard(layout, container), "adminDashboard");
                }
                layout.show(container, "adminDashboard");
            } else {

                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                PassengerDashboard dashboard = new PassengerDashboard(email, parentFrame);
                Container parent = parentFrame.getContentPane();
                parent.removeAll();
                parent.setLayout(new CardLayout());
                parent.add(dashboard, "dashboard");
                ((CardLayout) parent.getLayout()).show(parent, "dashboard");
                parentFrame.revalidate();
                parentFrame.repaint();
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
