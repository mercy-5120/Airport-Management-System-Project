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
    private static final Color PRIMARY_COLOR = new Color(60, 63, 65);
    private static final Color HOVER_COLOR = new Color(90, 90, 90);
    private static final Color FIELD_BORDER_COLOR = new Color(200, 200, 200);

    public static void styleField(JTextField field) {
        field.setFont(DEFAULT_FONT);
        field.setBackground(Color.WHITE);
        field.setForeground(Color.BLACK);
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(FIELD_BORDER_COLOR, 1, true),
                new EmptyBorder(8, 10, 8, 10)
        ));
    }

    public static void styleLabel(JLabel label) {
        label.setFont(LABEL_FONT);
        label.setForeground(Color.DARK_GRAY);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
    }

    public static void styleButton(JButton button) {
        button.setFont(BUTTON_FONT);
        button.setForeground(Color.WHITE);
        button.setBackground(PRIMARY_COLOR);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 40));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Hover effect
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
}
