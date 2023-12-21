package view;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GuestRegistrationGUI {
    
    private JLabel fullnameLabel;
    private JTextField fullnameText;

    private JLabel emailLabel;
    private JTextField emailText;
    
    private JLabel passwordLabel;
    private JPasswordField passwordText;


    private JButton registerButton;

    public GuestRegistrationGUI(){

        JFrame frame = new JFrame("Register to continue");
        JPanel registrationPanel = new JPanel(new GridBagLayout());
        
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(registrationPanel);   

        // Layout control 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Full name field
        fullnameLabel = new JLabel("Full name:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        registrationPanel.add(fullnameLabel, gbc);

        fullnameText = new JTextField(20); 
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        registrationPanel.add(fullnameText, gbc);

        // Email field
        emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        registrationPanel.add(emailLabel, gbc);

        emailText = new JTextField(20); 
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        registrationPanel.add(emailText, gbc);

        // Password field
        passwordLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        registrationPanel.add(passwordLabel, gbc);

        passwordText = new JPasswordField(20); 
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        registrationPanel.add(passwordText, gbc);



        // Register button
        registerButton = new JButton("Register");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER; 
        registrationPanel.add(registerButton, gbc);

        registerButton.addActionListener(e -> {
            boolean registrationSuccessful = false;
            while (!registrationSuccessful) {
                String fullName = fullnameText.getText();
                String email = emailText.getText();
                String password = String.valueOf(passwordText.getPassword());

                try {
                    registrationSuccessful = Driver.addUser(fullName, email, password);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                if (!registrationSuccessful) {
                    // Display an error message
                    // For example:
                    // errorMessageLabel.setText("Registration failed. Please re-enter information.");

                    // Clear text fields
                    fullnameText.setText("");
                    emailText.setText("");
                    passwordText.setText("");
                }
            }

            frame.dispose(); // Dispose the current frame if needed
            Driver.openUserSignInGUI(); // Call the method in Driver to open the next GUI
        });

        // Show and center frame
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
