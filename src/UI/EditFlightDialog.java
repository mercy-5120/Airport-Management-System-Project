package UI;

import Models.Flight;
import Services.IAdminService;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class EditFlightDialog extends JDialog {

    public EditFlightDialog(Window parent, Object[] rowData, int rowIndex, JTable table, IAdminService IAdminService) {
        super(parent, "Edit Flight", ModalityType.APPLICATION_MODAL);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField flightIdField = new JTextField(rowData[0].toString(), 15);
        JTextField originField = new JTextField(rowData[1].toString(), 15);
        JTextField destField = new JTextField(rowData[2].toString(), 15);
        JTextField departureField = new JTextField(rowData[3].toString(), 15);
        JTextField arrivalField = new JTextField(rowData[4].toString(), 15);
        JTextField dateField = new JTextField(rowData[5].toString(), 15);
        JTextField seatsField = new JTextField(rowData[6].toString(), 15);
        JTextField priceField = new JTextField(rowData[7].toString(), 15);

        gbc.gridx=0; gbc.gridy=0; add(new JLabel("FLIGHT ID:"), gbc);
        gbc.gridx=1; add(flightIdField, gbc);

        gbc.gridx=0; gbc.gridy++; add(new JLabel("ORIGIN:"), gbc);
        gbc.gridx=1; add(originField, gbc);

        gbc.gridx=0; gbc.gridy++; add(new JLabel("DESTINATION:"), gbc);
        gbc.gridx=1; add(destField, gbc);

        gbc.gridx=0; gbc.gridy++; add(new JLabel("DEPARTURE:"), gbc);
        gbc.gridx=1; add(departureField, gbc);

        gbc.gridx=0; gbc.gridy++; add(new JLabel("ARRIVAL:"), gbc);
        gbc.gridx=1; add(arrivalField, gbc);

        gbc.gridx=0; gbc.gridy++; add(new JLabel("DATE:"), gbc);
        gbc.gridx=1; add(dateField, gbc);

        gbc.gridx=0; gbc.gridy++; add(new JLabel("AVAILABLE SEATS:"), gbc);
        gbc.gridx=1; add(seatsField, gbc);

        gbc.gridx=0; gbc.gridy++; add(new JLabel("PRICE:"), gbc);
        gbc.gridx=1; add(priceField, gbc);

        JButton saveBtn = new JButton("Save");
        gbc.gridx=0; gbc.gridy++; gbc.gridwidth=2; gbc.anchor=GridBagConstraints.CENTER;
        add(saveBtn, gbc);

        saveBtn.addActionListener(ev -> {
            try {
                // Update JTable UI
                table.setValueAt(originField.getText().trim(), rowIndex, 1);
                table.setValueAt(destField.getText().trim(), rowIndex, 2);
                table.setValueAt(departureField.getText().trim(), rowIndex, 3);
                table.setValueAt(arrivalField.getText().trim(), rowIndex, 4);
                table.setValueAt(dateField.getText().trim(), rowIndex, 5);
                table.setValueAt(seatsField.getText().trim(), rowIndex, 6);
                table.setValueAt(priceField.getText().trim(), rowIndex, 7);

                // Update DB with your flight service
                Flight updatedFlight = new Flight(
                      flightIdField.getText().trim(),
                        originField.getText().trim(),
                        departureField.getText().trim(),
                        destField.getText().trim(),
                        arrivalField.getText().trim(),
                        LocalDate.parse(dateField.getText().trim()),
                        Integer.parseInt(seatsField.getText().trim()),
                        new BigDecimal(priceField.getText().trim())
                );

                IAdminService.updateFlight(updatedFlight);



                JOptionPane.showMessageDialog(this, "Flight updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating flight: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });



        pack();
        setLocationRelativeTo(parent);
    }
}
