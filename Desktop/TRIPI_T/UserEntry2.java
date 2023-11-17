import java.util.Scanner;

public class UserEntry2 {
    Scanner scan = new Scanner(System.in);
    boolean validemail = false;

    public void UserSelec(){
    System.out.println("To register click: 1");
    System.out.println("To log in click: 2");
    int ch = scan.nextInt();
    }    

    public void UserEmail(){
        System.out.println("Please enter your email");
        String email = scan.nextLine();
    }
    public void UserReg(){
        //KALEI UserEmail
        System.out.println("Please type the username");
        String usname = scan.nextLine();
        System.out.println("Please enter the code");
        int usercode = scan.nextInt();
    }
    public void UserLog(){
        //Kalei tin UserEmail
            System.out.println("Please enter your password");
}
