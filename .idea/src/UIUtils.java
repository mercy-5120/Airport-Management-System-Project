import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UIUtils {
    private static final Font DEFAULT_FONT = new Font("Segoe UI", Font.PLAIN, 14);
    private static final Font LABEL_FONT = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font BUTTON_FONT = new Font("Segoe UI", Font.BOLD, 14);
    private static final Color PRIMARY_COLOR = new Color(92, 78, 78);
    private static final Color HOVER_COLOR = new Color(90, 90, 90);
    private static final Color FIELD_BORDER_COLOR = new Color(200, 200, 200);
    public static final Color LINK_COLOR = Color.decode("#5C4E4E");

    public static void styleField(JTextField field) {
        field.setFont(DEFAULT_FONT);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setPreferredSize(new Dimension(320, 35));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(FIELD_BORDER_COLOR, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
    }

    public static void styleLabel(JLabel label) {
        label.setFont(LABEL_FONT);
        label.setForeground(Color.DARK_GRAY);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    }

    public static void stylePanel(JPanel panel) {
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(350, 40));

    }

    public static void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(320, 40));
        button.setMaximumSize(new Dimension(320, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HOVER_COLOR);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(PRIMARY_COLOR);
            }
        });
    }

    public static void styleTitle(JLabel label) {
        label.setFont(new Font("Inter", Font.BOLD, 24));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
    }

    public static JLabel createLinkLabel(String text) {
        JLabel label = new JLabel("<HTML><U>" + text + "</U></HTML>");
        label.setForeground(LINK_COLOR);
        label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return label;
    }

    public static JLabel createShowPasswordToggle(JPasswordField passwordField) {
        JLabel togglePass = createLinkLabel("Show Password");
        togglePass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (passwordField.getEchoChar() == 0) {
                    passwordField.setEchoChar('•');
                    togglePass.setText("<HTML><U>Show Password</U></HTML>");
                } else {
                    passwordField.setEchoChar((char) 0);
                    togglePass.setText("<HTML><U>Hide Password</U></HTML>");
                }
            }
        });
        return togglePass;
    }
}