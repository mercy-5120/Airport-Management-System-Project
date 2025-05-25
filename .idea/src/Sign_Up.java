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
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("USER REGISTRATION");
        UIUtils.styleTitle(titleLabel);

        JPanel namePanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("FULL NAME");
        namePanel.add(nameLabel);
        UIUtils.stylePanel(namePanel);

        JPanel nameFieldPanel=new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField fullNameField = new JTextField();
        nameFieldPanel.add(fullNameField);
        UIUtils.stylePanel(nameFieldPanel);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("EMAIL");
        emailPanel.add(emailLabel);
        UIUtils.stylePanel(emailPanel);

        JPanel emailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField emailField = new JTextField();
        emailFieldPanel.add(emailField);
        UIUtils.stylePanel(emailFieldPanel);


        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("PASSWORD");
        passwordPanel.add(passLabel);
        UIUtils.stylePanel(passwordPanel);


        JPanel passFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPasswordField passwordField = new JPasswordField();
        passFieldPanel.add(passwordField);
        UIUtils.stylePanel(passFieldPanel);


        JLabel[] labels = { nameLabel, emailLabel,  passLabel };
        JTextField[] fields = { fullNameField, emailField};
        for (JLabel label : labels) UIUtils.styleLabel(label);
        for (JTextField field : fields) UIUtils.styleField(field);
        UIUtils.styleField(passwordField);

        JLabel signIn = UIUtils.createLinkLabel("Sign in");
        JLabel togglePass = UIUtils.createShowPasswordToggle(passwordField);

        JPanel linkPanel = new JPanel(new BorderLayout());
        linkPanel.setSize(new Dimension(80, 20));
        linkPanel.setBackground(Color.WHITE);
        linkPanel.add(signIn,BorderLayout.WEST);
        linkPanel.add(togglePass, BorderLayout.EAST);

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
        formPanel.add(namePanel);
        formPanel.add(nameFieldPanel);
        formPanel.add(emailPanel);
        formPanel.add(emailFieldPanel);
        formPanel.add(passwordPanel);
        formPanel.add(passFieldPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(linkPanel);
        formPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        formPanel.add(signUp);

        add(formPanel, BorderLayout.CENTER);
        add(imagePanel, BorderLayout.SOUTH);
    }
}
