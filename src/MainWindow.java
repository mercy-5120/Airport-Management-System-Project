import javax.swing.*;
import java.awt.*;
import UI.*;


public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("SkyPort Limited");
        setSize(500, 760);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("Src/Assets/logo.png").getImage());

        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        container.add(new LoginForm(cardLayout, container), "login");
        container.add(new SignUpForm(cardLayout, container), "signup");

        add(container);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}