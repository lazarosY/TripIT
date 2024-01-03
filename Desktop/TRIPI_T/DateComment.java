import java.util.Scanner;
public class DateComment {
    public long datecomparison(UserInput userInput, int recnum) {
        long daysnum = userInput.getDiffInDays(); 
        if (daysnum == recnum) {
            System.out.println("The recommended days match with your choice!");
            return daysnum;
        } else {
            System.out.println("The recommended days for visiting this destination are " + recnum + ". Would you like to change the dates of your trip?");
            System.out.println("Insert Yes for change, No for keeping your initial choice.");
            Scanner input = new Scanner(System.in);
            String ans;
            boolean valid = false;
            boolean messageDisplayed = false; // Προσθήκη μεταβλητής για το μήνυμα
            do {
                ans = input.nextLine();
                if (!ans.equalsIgnoreCase("Yes") && !ans.equalsIgnoreCase("No")) {
                    System.out.println("Please enter only Yes or No.");
                } else {
                    valid = true;
                    if (!messageDisplayed) {
                        messageDisplayed = true;
                    } else {
                        System.out.println("We're keeping your initial choice of dates.");
                        return daysnum;
                    }
                }
            } while (!valid);
            return Changedate(ans, userInput, recnum, daysnum); 
        }
    }

    public long Changedate(String ans, UserInput userInput, int recnum,long daysnum) {
        if (ans.equals("Yes")) {
            long newDiffInDays;
            int i=0;
            do {
                if(i!=0){
                    System.out.println("The preferred days are " + recnum + ".Please enter correct dates.");
                }
                userInput.getTripDates(); 
                newDiffInDays = userInput.getDiffInDays();
                i++;
            } while (newDiffInDays != recnum);
            return  recnum;
        } else {
            System.out.println("We're keeping your initial choice of dates.");
             return daysnum;
        }
    }
}
