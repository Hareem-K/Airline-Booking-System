package view;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UserSignInGUI {

    private JFrame frame;
    private JLabel emailLabel;
    private final JTextField emailField;
    private JPasswordField passwordField;
    private JButton signInButton;

    public UserSignInGUI() {
        frame = new JFrame("User Sign-In");
        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(20, 20, 80, 25);
        loginPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(100, 20, 200, 25);
        loginPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 50, 80, 25);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(100, 50, 200, 25);
        loginPanel.add(passwordField);

        signInButton = new JButton("Sign In");
        signInButton.setBounds(130, 90, 80, 25);
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = String.valueOf(passwordField.getPassword());

                boolean isAuthenticated = false;
                try {
                    isAuthenticated = Driver.authenticateLogin(email, password);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                if (isAuthenticated) {
                    JOptionPane.showMessageDialog(frame, "Login successful!");
                    Driver.openUserHubGUI();
                    frame.dispose(); // Closes the current window

                    // Add your logic here for what happens after successful login
                } else {
                    JOptionPane.showMessageDialog(frame, "Incorrect login information. Please try again.");
                    // Clear fields to allow for another attempt
                    emailField.setText("");
                    passwordField.setText("");
                }
            }
        });
        loginPanel.add(signInButton);

        frame.add(loginPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
