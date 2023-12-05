package trip;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserEntry3 {
    Scanner scan = new Scanner(System.in);
    boolean validEmail = false;
    String email = "";
    Map<String, String> users = new HashMap<>(); 


    public void start() {
        System.out.println("To register, click: 1");
        System.out.println("To log in, click: 2");
        System.out.println("To exit, click: 3");

        int choice = scan.nextInt();
        scan.nextLine(); 

        switch (choice) {
            case 1:
                userReg();
                break;
            case 2:
                userLog();
                break;
            case 3:
                System.out.println("Exiting...");
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public void userEmail() {
        System.out.println("Please enter your email");
        email = scan.nextLine();
    }

    public void userReg() {
        userEmail();

        if (users.containsKey(email)) {
            System.out.println("The email you entered already has an account.");
            System.out.println("Do you want to log in with this email? (Y/N)");
            String choice = scan.nextLine();

            if (choice.equalsIgnoreCase("Y")) {
                userLog();
            } else if (choice.equalsIgnoreCase("N")) {
                System.out.println("Please enter a different email");
                userReg();
            } else {
                System.out.println("Invalid choice");
            }
        } else {
            System.out.println("Please enter the username");
            String username = scan.nextLine();
            System.out.println("Please enter the code");
            int usercode = scan.nextInt();
            scan.nextLine();

            // Αποθήκευση του email και του κωδικού στο Map
            users.put(email, Integer.toString(usercode));

            System.out.println("User successfully registered!");
        }
    }

    public void userLog() {
        userEmail();

        if (users.containsKey(email)) {
            System.out.println("Please enter your password");
            String password = scan.nextLine();

            if (password.equals(users.get(email))) {
                System.out.println("Login successful!");
            } else {
                System.out.println("Invalid password");
            }
        } else {
            System.out.println("The email you entered does not exist.");
            System.out.println("Do you want to register with this email? (YES/NO)");
            String choice = scan.nextLine();

            if (choice.equalsIgnoreCase("YES")) {
                userReg();
            } else if (choice.equalsIgnoreCase("NO")) {
                System.out.println("Please enter a different email");
                userLog();
            } else {
                System.out.println("Invalid choice");
            }
        }
    }
}
