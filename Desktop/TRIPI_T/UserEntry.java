import java.util.Scanner;
public class UserEntry {
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
        //Kalei tin UserEmail
        if(email.equals(SQL)){
            System.out.println("The email you enter already has an account."); 
            System.out.println("To log in to the existing account click: 1."); 
            System.out.println("To create a new account with another email click: 2");
            int choise = scan.nextInt();
        }else{
            System.out.println("Please type the username");
            String usname = scan.nextLine();
            System.out.println("Please enter the code");
            int usercode = scan.nextInt();
        }
    }

    public void UserLog(){
        //Kalei tin UserEmail
        if(email.equals(SQL)){
            System.out.println("Please enter your password");
            boolean validpass = false;
            do {
                System.out.println("Please enter your password");
                int pass = scan.nextInt();
                if(pass==SQL){
                    validpass = true;
                }
            }while(validpass==false);
        }else{
            System.out.println ("The email you entered does not exist.");
            System.out.println("To enter a new email click: 1."); 
            System.out.println("To subscribe click: 2.");
            int choises = scan.nextInt();
        }
    }
}