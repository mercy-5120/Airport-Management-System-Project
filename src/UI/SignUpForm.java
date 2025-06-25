package UI;

import Models.Passenger;
import PasswordUtil.PasswordUtil;
import Repositories.PassengerRepository;
import Repositories.UserRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SignUpForm extends JPanel {
    private CardLayout layout;      // Add fields
    private JPanel container;

    public SignUpForm(CardLayout layout, JPanel container) {
        this.layout = layout;        // Assign fields
        this.container = container;

        setLayout(new BorderLayout());

        // Image panel
        JPanel imagePanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("Src/Assets/airport.jpg").getImage(), 0, 0, getWidth(), getHeight(), this);
            }

            public Dimension getPreferredSize() {
                return new Dimension(0, 300);
            }
        };

        // Form panel setup (unchanged) ...
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("USER REGISTRATION");
        UIUtils.styleTitle(titleLabel);

        // Name panel
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("FULL NAME");
        namePanel.add(nameLabel);
        UIUtils.stylePanel(namePanel);

        JPanel nameFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField fullNameField = new JTextField(20);  // Add preferred width
        nameFieldPanel.add(fullNameField);
        UIUtils.stylePanel(nameFieldPanel);

        // Email panel
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("EMAIL");
        emailPanel.add(emailLabel);
        UIUtils.stylePanel(emailPanel);

        JPanel emailFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JTextField emailField = new JTextField(20);
        emailFieldPanel.add(emailField);
        UIUtils.stylePanel(emailFieldPanel);

        // Password panel
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passLabel = new JLabel("PASSWORD");
        passwordPanel.add(passLabel);
        UIUtils.stylePanel(passwordPanel);

        JPanel passFieldPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPasswordField passwordField = new JPasswordField(20);
        passFieldPanel.add(passwordField);
        UIUtils.stylePanel(passFieldPanel);

        JLabel[] labels = {nameLabel, emailLabel, passLabel};
        JTextField[] fields = {fullNameField, emailField};
        for (JLabel label : labels) UIUtils.styleLabel(label);
        for (JTextField field : fields) UIUtils.styleField(field);
        UIUtils.styleField(passwordField);

        JLabel signIn = UIUtils.createLinkLabel("Sign in");
        JLabel togglePass = UIUtils.createShowPasswordToggle(passwordField);

        JPanel linkPanel = new JPanel(new BorderLayout());
        linkPanel.setSize(new Dimension(80, 20));
        linkPanel.setBackground(Color.WHITE);
        linkPanel.add(signIn, BorderLayout.WEST);
        linkPanel.add(togglePass, BorderLayout.EAST);

        JButton signUp = new JButton("Sign Up");
        UIUtils.styleButton(signUp);

        // Sign Up button listener
        signUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String fullname = fullNameField.getText().trim();
                String email = emailField.getText().trim();
                String password = new String(passwordField.getPassword());

                if (fullname.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Please fill in all fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                String hashedPassword= PasswordUtil.encryptPassword(password);

                UserRepository userRepository = new UserRepository();
                if(userRepository.findbyEmail(email)!=null){
                    JOptionPane.showMessageDialog(SignUpForm.this, "Email already exists.", "Email already exists", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Passenger newPassenger=new Passenger(fullname,email,hashedPassword);
                PassengerRepository passengerService =new PassengerRepository();
                boolean success= passengerService.registerPassenger(newPassenger);

                if(success){
                    JOptionPane.showMessageDialog(SignUpForm.this, "Registration successful.", "Registration Successful", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    JOptionPane.showMessageDialog(SignUpForm.this, "Registration failed.", "Registration Failed", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                    // Switch to login panel after successful registration
                    layout.show(container, "login");
                }

        });

        // Sign In label listener (to switch to login panel)
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
