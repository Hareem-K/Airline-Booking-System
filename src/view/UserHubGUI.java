package view;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class UserHubGUI {
    private JButton browseFlightsButton;
    private JButton cancelFlightButton;
    private JButton viewPaymentsButton;

    public UserHubGUI() {
        JFrame frame = new JFrame("Welcome");
        JPanel hubPanel = new JPanel(new GridBagLayout());

        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(hubPanel);

        // Layout control
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Browse button
        browseFlightsButton = new JButton("Browse flights");
        browseFlightsButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        hubPanel.add(browseFlightsButton, gbc);

        browseFlightsButton.addActionListener(e -> {
            try {
                Driver.openBrowseFlightsGUI();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
        });

        // Cancel button
        cancelFlightButton = new JButton("View and cancel flights");
        cancelFlightButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        hubPanel.add(cancelFlightButton, gbc);

        cancelFlightButton.addActionListener(e -> {
            try {
                Driver.openCancelFlightGUI();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
        });

        // View Payments button
        viewPaymentsButton = new JButton("View Payments");
        viewPaymentsButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        hubPanel.add(viewPaymentsButton, gbc);

        viewPaymentsButton.addActionListener(e -> {
            try {
                Driver.openViewPaymentsGUI();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            frame.dispose();
        });

        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        hubPanel.add(exitButton, gbc);

        exitButton.addActionListener(e -> {
            frame.dispose(); // Close the frame
        });

        // Show and center frame
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}