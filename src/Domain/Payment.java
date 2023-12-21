package Domain;

public class Payment {
    private String cardNumber;
    private String paymentType;
    private int flightID;
    private double price;

    public Payment(String cardNumber, String paymentType, int flightID, double price) {
        this.cardNumber = cardNumber;
        this.paymentType = paymentType;
        this.flightID = flightID;
        this.price = price;
    }

    // Getters and setters for each attribute
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // Inside your Payment class
    @Override
    public String toString() {
        return "Payment Information:" +
                "\nCard Number: " + cardNumber +
                "\nPayment Type: " + paymentType +
                "\nFlight ID: " + flightID +
                "\nPrice: $" + price;
    }
    // Other methods as needed
}