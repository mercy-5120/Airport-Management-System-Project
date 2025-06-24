import javax.swing.*;
import java.awt.*;

public class AdmEdit extends JPanel {
    public AdmEdit() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JLabel title = new JLabel("EDIT FLIGHT", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
        title.setForeground(new Color(218, 165, 32));
        add(title, BorderLayout.NORTH);

        // Table for flights (placeholder data)
        String[] columns = {"FLIGHT ID", "ORIGIN", "DESTINATION", "DEPARTURE", "ARRIVAL", "DATE", "AVAILABLE SEATS", "PRICE"};
        Object[][] data = {
                {"FL001", "Nairobi", "Mombasa", "9:00 a.m", "10:45 a.m", "12/06/2025", "50/50", "Ksh 12000"}
        };
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}