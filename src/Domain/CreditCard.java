package Domain;

import java.util.ArrayList;
import java.util.List;

public class CreditCard {
    private String cardNumber;
    private String cardholderName;
    private String expiryDate;

    private List<Payment> payments;

    public CreditCard(String cardNumber, String cardholderName, String expiryDate) {
        this.cardNumber = cardNumber;
        this.cardholderName = cardholderName;
        this.expiryDate = expiryDate;
        this.payments = new ArrayList<>(); // Initialize payments list
    }

    // Getters and setters for the fields

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    // Method to add a payment to the list
    public void addPayment(Payment payment) {
        if (payments == null) {
            payments = new ArrayList<>();
        }
        payments.add(payment);
    }

    public Payment getPayment(int index) {
        return payments.get(index);
    }

    public List<Payment> getPayments() {
        return payments;
    }
}