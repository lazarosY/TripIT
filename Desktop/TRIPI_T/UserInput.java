import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class UserInput {
    Scanner read = new Scanner(System.in);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public void dest() {
        System.out.println("Where whould you like to travel?");
        String destination = read.nextLine();
    }

    public void budget() {
        System.out.println("How much would you like to spend? (In €)");
        double bud = read.nextDouble();
        
    }

    public void getTripDates() throws ParseException {
       System.out.println("When does your trip begin? (YYYY-MM-DD)");
        String temp1 = read.nextLine();
        LocalDate date1 = LocalDate.parse(temp1);
        System.out.println("When does your trip end? (YYYY-MM-DD)");
        String temp2 = read.nextLine();
        LocalDate date2 = LocalDate.parse(temp2);
        long diffInDays = ChronoUnit.DAYS.between(date1, date2);
        System.out.println(diffInDays);
    }
    public void pref() {
        boolean correct_answer = false;
        String userPref = "";
        Scanner scanner = new Scanner(System.in); // Δημιουργία ενός Scanner object
        while(correct_answer != true) {
            System.out.println("Tell us about your interests; (E.g. football,museum,shopping...)");
            userPref = scanner.nextLine(); 
            String regex = "^(\\w+)(,\\s*\\w+){0,4}$";
            String[] interests = userPref.split(",\\s*");
            boolean allValid = true;
            for (String interest : interests) {
                if (!interest.matches("\\w+")) {
                    allValid = false;
                    break;
                }
            }
            if (userPref.matches(regex) && allValid && interests.length <= 5) {
                correct_answer = true;
                break;
            } else {
                System.out.println("Please give us again your interests with the correct format");
            }   
        }
    }

}
    
