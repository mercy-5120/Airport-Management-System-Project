import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow() {
        setTitle("SkyPort Limited");
        setSize(450, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new ImageIcon("Assets/logo.png").getImage());

        CardLayout cardLayout = new CardLayout();
        JPanel container = new JPanel(cardLayout);

        Login login = new Login(cardLayout, container);
        Sign_Up signUp = new Sign_Up(cardLayout, container);

        container.add(login, "login");
        container.add(signUp, "signup");

        add(container);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainWindow::new);
    }
}
