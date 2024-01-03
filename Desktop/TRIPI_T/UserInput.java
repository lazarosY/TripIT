import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;


public class UserInput {
    private String userPref = "";
    private long diffInDays;
    private double bud;
    private String destination;
    Scanner read = new Scanner(System.in);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void dest() {
         while (true) {
            try {
                System.out.println("Where would you like to travel?");
                String input = read.nextLine();
                if (!input.matches("[a-zA-Z]+")) {
                    throw new IllegalArgumentException("Please enter a valid destination without numbers or special characters.");
                }
                destination = input;
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String getdestination(){
        return destination;
    }

    public void budget() {
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.println("How much would you like to spend? (In €)");
                bud = read.nextDouble();
                if (bud <= 0) {
                    throw new IllegalArgumentException("Budget should be a positive number.");
                }
                validInput = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid number for the budget.");
                read.nextLine(); // Consume invalid input
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public double getbud(){
        return bud;
    }

    public void getTripDates() {
        while (true) {
            try{
                LocalDate today = LocalDate.now();
                System.out.println("When does your trip begin? (YYYY-MM-DD)");
                String temp1 = read.nextLine();
                LocalDate date1 = LocalDate.parse(temp1);
                if (date1.isBefore(today.plusDays(1))) {
                    System.out.println("Departure date should be at least one day ahead of today. Please re-enter.");
                    continue;
                }
                System.out.println("When does your trip end? (YYYY-MM-DD)");
                String temp2 = read.nextLine();
                LocalDate date2 = LocalDate.parse(temp2);
                if (date2.isBefore(date1)) {
                    System.out.println("Arrival date should be after the departure date. Please re-enter.");
                    continue;
                }
                diffInDays = ChronoUnit.DAYS.between(date1, date2);
                System.out.println("Trip duration: " + diffInDays + " days");
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please enter dates in YYYY-MM-DD format.");
            }
        }
    }

    public long getDiffInDays() {
        return diffInDays;
    }

    public void pref() {
        boolean correct_answer = false;
        Scanner scanner = new Scanner(System.in); 
        while(correct_answer != true) {
            System.out.println("Tell us about your interests; (E.g. football, museum, shopping...)");
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
    
    public String getPref() {
        return userPref;
    }
}
