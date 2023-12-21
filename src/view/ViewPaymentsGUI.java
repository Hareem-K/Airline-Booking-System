package view;

import controllers.CreditCardController;
import controllers.PaymentController;
import Domain.CreditCard;
import Domain.Payment;

import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

public class ViewPaymentsGUI {
    private JTextArea paymentsTextArea;

    public ViewPaymentsGUI(String username) throws SQLException {
        JFrame frame = new JFrame("View Payments");
        JPanel panel = new JPanel();

        paymentsTextArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(paymentsTextArea);
        panel.add(scrollPane);

        JButton returnButton = new JButton("Return to Hub");
        returnButton.addActionListener(e -> {
            Driver.openUserHubGUI();
            frame.dispose(); // Close the current window
        });
        panel.add(returnButton);

        frame.getContentPane().add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        displayPayments(username);
    }

    private void displayPayments(String username) throws SQLException {
        CreditCardController ccController = new CreditCardController();
        PaymentController paymentController = new PaymentController();

        CreditCard creditCard = ccController.getCreditCard(username);

        if (creditCard != null) {
            List<Payment> payments = paymentController.getPaymentsByCardNumber(creditCard.getCardNumber());

            if (payments != null && !payments.isEmpty()) {
                for (Payment payment : payments) {
                    paymentsTextArea.append(payment.toString() + "\n");
                }
            } else {
                paymentsTextArea.append("No payments found for this card.");
            }
        } else {
            paymentsTextArea.append("No credit card information found for this user.");
        }
    }
}