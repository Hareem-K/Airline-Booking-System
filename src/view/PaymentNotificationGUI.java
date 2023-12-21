package view;

import Domain.Flight;
import Domain.Seat;

import javax.swing.*;
import java.awt.*;

public class PaymentNotificationGUI extends JFrame {

    public PaymentNotificationGUI(Seat seat, Flight flight, double price, String cardNum) {
        setTitle("Payment Notification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel detailsPanel = new JPanel(new GridLayout(0, 1));

        JLabel seatLabel = new JLabel("Seat: " + seat.getSeatID());
        JLabel flightLabel = new JLabel("Flight: " + flight.getFlightNumber() + " to " + flight.getDestination());
        JLabel timeLabel = new JLabel("Time: " + flight.getDepartureTime().toString()); // Adjust this as needed
        JLabel priceLabel = new JLabel("Price: $" + price);
        JLabel cardNumLabel = new JLabel("Card Number: " + cardNum);

        detailsPanel.add(seatLabel);
        detailsPanel.add(flightLabel);
        detailsPanel.add(timeLabel);
        detailsPanel.add(priceLabel);
        detailsPanel.add(cardNumLabel);

        add(detailsPanel, BorderLayout.CENTER);
        // Calculate the x-coordinate to position the window on the right side
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

// Calculate the x-coordinate to position the window in the middle-right
        int windowWidth = getWidth(); // Assuming 'frame' is your JFrame
        int windowHeight = getHeight(); // Assuming 'frame' is your JFrame
        int xCoordinate = (int) (screenSize.getWidth() - windowWidth);
        int yCoordinate = (int) ((screenSize.getHeight() - windowHeight) / 2);

// Set the location of the window
        setLocation(xCoordinate, yCoordinate);
        setVisible(true);
    }

    // Additional methods or adjustments can be made based on your GUI preferences
}