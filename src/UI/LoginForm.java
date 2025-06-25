package UI;

import Models.Role;
import Models.User;
import Repositories.UserRepository;
import Services.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginForm extends JPanel {
    public LoginForm(CardLayout layout, JPanel container) {
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

      UserService userService=new UserRepository() ;
        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());
            if (emailField.getText().trim().isEmpty() || new String(passwordField.getPassword()).trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                return;
            }

            User user = userService.loginUser(email, password);

            if (user != null) {
                if(user.getRole()== Role.PASSENGER){
                    JOptionPane.showMessageDialog(this, "Login Successful.", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                   layout.show(container, "passenger");
                   this.setVisible(false);
                }else {
                    layout.show(container, "admin");
                }
            }else {
                JOptionPane.showMessageDialog(this, "Login Failed.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return;
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
