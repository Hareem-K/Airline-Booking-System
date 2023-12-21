package view;

import Domain.Flight;
import Domain.Seat;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PaymentGUI extends JFrame {

    public PaymentGUI(Seat seat, String cardNumber, Flight flight) {
        setTitle("Payment Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2)); // Vertically arranged, 2 columns

        panel.add(new JLabel("Seat ID: "));
        panel.add(new JLabel(seat.getSeatID()));

        panel.add(new JLabel("Price: "));
        panel.add(new JLabel(calculateSeatPrice(seat.getSeatType(), flight)));

        panel.add(new JLabel("Card Number: "));
        panel.add(new JLabel(cardNumber));

        panel.add(new JLabel("Flight Number: "));
        panel.add(new JLabel(String.valueOf(flight.getFlightNumber())));

        JButton confirmButton = new JButton("Confirm Payment");
        confirmButton.addActionListener(e -> {
            double price = Double.parseDouble(calculateSeatPrice(seat.getSeatType(), flight).substring(1));
            try {
                Driver.confirmPayment(seat, cardNumber, flight.getFlightNumber(), price);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        });

        panel.add(confirmButton);

        add(panel);
        // Calculate the x-coordinate to position the window on the right side
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int windowWidth = getWidth(); // Assuming 'frame' is your JFrame
        int xCoordinate = screenWidth - windowWidth;

// Set the location of the window

        setVisible(true);
    }

    // Method to calculate seat price based on seat type and flight date
    private String calculateSeatPrice(String seatType, Flight flight) {
        double basePrice;

        if ("First Class".equals(seatType)) {
            basePrice = 100.0;
        } else if ("Business Class".equals(seatType)) {
            basePrice = 80.0;
        } else {
            basePrice = 50.0;
        }

        // Parse the string date to a Date object
        Timestamp departureTimestamp = flight.getDepartureTime(); // Replace this with the actual Timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // Format the Timestamp to the desired date string
            String dateString = dateFormat.format(departureTimestamp);
            // Parse the string date to a Date object
            Date departureDate = dateFormat.parse(dateString);


            // Check if the flight is on the first day of the month for discount
            Calendar cal = Calendar.getInstance();
            cal.setTime(departureDate);
            int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            // If the flight is on the first day of the month, apply a 10% discount
            if (dayOfMonth == 1) {
                basePrice *= 0.9; // 10% discount
                // Show a pop-up message for the special promotion
                String message = "Congratulations! You've got a 10% discount applied to your flight.";
                JOptionPane.showMessageDialog(null, message, "Special Promotion", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the parsing exception
        }

        return "$" + basePrice;
    }

}
