import javax.swing.*;
import java.awt.*;

public class AdmView extends JPanel {
    public AdmView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("VIEW PASSENGERS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        // Table for passengers (placeholder data)
        String[] columns = {"PASSENGER ID", "NAME", "TICKET ID", "FLIGHT ID", "DATE", "STATUS", "PASSPORT NUMBER"};
        Object[][] data = {};
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}