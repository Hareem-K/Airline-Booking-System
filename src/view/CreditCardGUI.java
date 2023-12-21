package view;


import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CreditCardGUI {
    private JFrame frame;
    private JTextField cardNumberField;
    private JTextField expiryDateField;

    public CreditCardGUI(String fullName) {
        frame = new JFrame("Enter Credit Card Details");
        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel cardNumberLabel = new JLabel("Card Number:");
        JLabel expiryDateLabel = new JLabel("Expiry Date:");
        cardNumberField = new JTextField();
        expiryDateField = new JTextField();

        panel.add(cardNumberLabel);
        panel.add(cardNumberField);
        panel.add(expiryDateLabel);
        panel.add(expiryDateField);

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String cardNumber = cardNumberField.getText();
            String expiryDate = expiryDateField.getText();

            boolean isSuccess = false;
            try {
                isSuccess = Driver.setCreditCard(fullName, cardNumber, expiryDate);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            if (isSuccess) {
                JOptionPane.showMessageDialog(null, "Credit card details saved successfully!");
                Driver.openPaymentGUI();
                frame.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Failed to save credit card details. Please try again.");
            }
        });

        panel.add(submitButton);

        frame.add(panel);
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set location to the center of the screen
        frame.setVisible(true);
    }
}