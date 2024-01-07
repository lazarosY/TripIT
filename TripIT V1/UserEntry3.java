import java.util.Scanner;

public class UserEntry3 {
    Scanner scan = new Scanner(System.in);
    boolean validEmail = false;
    String email = "";

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
        System.out.println("Please enter your password");
        String password = scan.nextLine();
        Hash.registration(email, password);
    }

    public void userLog() {
        String password;
        do {
            userEmail();
            System.out.println("Please enter your password");
            password = scan.nextLine();
            if (!DataBaseConnectivity.emailExists(email)) {
                System.out.println("The email entered doesn't exist. Register here.");
                userReg();
                break;
            } else if (DataBaseConnectivity.authentication(email, password)) {
                System.out.println("Successful login");
            } else {
                System.out.println("Wrong email or password. Try again.");
            }
        } while (!DataBaseConnectivity.authentication(email, password));
    }
}
