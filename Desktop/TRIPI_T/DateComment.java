import java.util.Scanner;
public class DateComment {
    public void datecomparison(UserInput userInput, int recnum) {
        long daysnum = userInput.getDiffInDays(); 
        if (daysnum == recnum) {
            System.out.println("The recommended days match with your choice!");
        } else {
            System.out.println("The recommended days for visiting this destination are " + recnum + ". Would you like to change the dates of your trip?");
            System.out.println("Insert Yes for change, No for keeping your initial choice.");
            Scanner input = new Scanner(System.in);
            String ans = input.nextLine();
            Changedate(ans, userInput, recnum); 
        }
    }

    public void Changedate(String ans, UserInput userInput, int recnum) {
        if (ans.equals("Yes")) {
            long newDiffInDays;
            do {
                userInput.getTripDates(); 
                newDiffInDays = userInput.getDiffInDays();
            } while (newDiffInDays != recnum); 
        } else {
            System.out.println("We're keeping your initial choice of dates.");
        }
    }
}
    