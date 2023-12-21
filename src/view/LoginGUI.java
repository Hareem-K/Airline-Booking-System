package view;


import javax.swing.*;

import java.awt.event.*;
import java.sql.SQLException;



public class LoginGUI {

    // private member variables for the GUI components

    private JLabel userLabel;
    private JTextField usernameText;
    private JLabel passwordLabel;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton guestButton;
    private JButton registerButton;
    private JLabel userTypeLabel;
    private JComboBox userType;


    /**
     * Constructor for the LoginGUI. Creates the GUI and displays it to the user.
     */
    public LoginGUI(){

        // initialize the GUI components and display them on screen
        JFrame frame = new JFrame("Welcome");
        JPanel loginPanel = new JPanel();
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(loginPanel);
        loginPanel.setLayout(null);

        userTypeLabel = new JLabel("Admin Login");
        userTypeLabel.setBounds(165, 20, 200, 25);
        loginPanel.add(userTypeLabel);

      

        userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 50, 80, 25);
        loginPanel.add(userLabel);

        usernameText = new JTextField(20);
        usernameText.setBounds(100, 50, 200, 25);
        loginPanel.add(usernameText);

        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 80, 80, 25);
        loginPanel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 80, 200, 25);
        loginPanel.add(passwordText);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 110, 200, 25);

        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameText.getText();
                String password = new String(passwordText.getPassword());

                if (username.equalsIgnoreCase("attendant") && password.equalsIgnoreCase("attendant")) {
                    // Call the method in Driver for admin login
                    JOptionPane.showMessageDialog(null, "Successful login!", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
                    try {
                        Driver.attendant();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect login. Access Denied.", "Login Error", JOptionPane.ERROR_MESSAGE);
                    // Clear fields to allow re-entry
                    usernameText.setText("");
                    passwordText.setText("");
                }

                // Close the frame after triggering the action

            }
        });

        guestButton = new JButton("Continue as Customer");
        guestButton.setBounds(100, 140, 200, 25);
        guestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Driver.openPickUserGUI(); // Call the method in Driver to open the next GUI

                frame.dispose(); // Dispose the current frame if needed
            }
        });
        loginPanel.add(guestButton);


        frame.setContentPane(loginPanel);
        frame.setLocationRelativeTo(null); // Set location to the center of the screen
        frame.setVisible(true);
    }

    public static void main(String[] args){
        new LoginGUI();
    }
}




