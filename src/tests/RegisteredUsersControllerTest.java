package tests;

import controllers.RegisteredUsersController;
import Domain.RegisteredUsers;

import java.util.List;

public class RegisteredUsersControllerTest {

    public static void main(String[] args) {
        try {
            RegisteredUsersController registeredUsersController = new RegisteredUsersController();

            List<RegisteredUsers> users = registeredUsersController.getAllRegisteredUsers();

            if (users.isEmpty()) {
                System.out.println("No registered users found.");
            } else {
                System.out.println("Registered Users:");
                for (RegisteredUsers user : users) {
                    // Display user information
                    System.out.println("FullName: " + user.getName());
                    System.out.println("Email: " + user.getEmail());
                    if (user.getCreditCard() != null)
                        System.out.println("CreditCard: " + user.getCreditCard().getCardNumber());
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}