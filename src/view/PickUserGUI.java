package view;

import javax.swing.*;
import java.awt.event.*;

public class PickUserGUI extends JFrame {
    private JButton loginButton;
    private JButton signUpButton;

    public PickUserGUI() {
        setTitle("User Selection");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        loginButton = new JButton("Login as Registered User");
        loginButton.setBounds(40, 30, 200, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle login action
                Driver.openUserSignInGUI();
                dispose(); // Close PickUserGUI after selecting login
                // You can perform any action related to login here
            }
        });
        panel.add(loginButton);

        signUpButton = new JButton("Sign Up as Registered User");
        signUpButton.setBounds(40, 65, 200, 25);
        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle sign up action
                Driver.openGuestRegistrationGUI();
                dispose(); // Close PickUserGUI after selecting sign up
                // You can perform any action related to sign up here
            }
        });
        panel.add(signUpButton);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PickUserGUI();
        });
    }
}