import java.util.Scanner;

public class DateComment {
    public long dateComparison(UserInput userInput, int recnum) {
        long daysnum = userInput.getDiffInDays();

        if (daysnum == recnum) {
            System.out.println("The recommended days match with your choice!");
            return daysnum;
        } else {
            System.out.println("The recommended days for visiting this destination are " + recnum
                    + ". Would you like to change the dates of your trip?");
            System.out.println("Insert Yes for change, No for keeping your initial choice.");

            Scanner input = new Scanner(System.in);
            String ans;
            boolean valid = false;

            do {

                ans = input.nextLine();

                if (!ans.equalsIgnoreCase("Yes") && !ans.equalsIgnoreCase("No")) {
                    System.out.println("Please enter only Yes or No.");
                } else if (ans.equalsIgnoreCase("No")) {
                    valid = true;
                    System.out.println("We're keeping your initial choice of dates.");
                    return daysnum;
                } else if (ans.equalsIgnoreCase("Yes")) {
                    valid = true;
                    return Changedate(ans, userInput, recnum, daysnum);
                }
            } while (!valid);
        }
        return daysnum;
    }

    public long Changedate(String ans, UserInput userInput, int recnum, long daysnum) {
        if (ans.equalsIgnoreCase("Yes")) {
            long newDiffInDays;
            int i = 0;
            do {
                if (i != 0) {
                    System.out.println("The preferred days are " + recnum + ".Please enter correct dates.");
                }
                userInput.getTripDates(); 
                newDiffInDays = userInput.getDiffInDays();
                i++;
            } while (newDiffInDays != recnum);
            return recnum;
        } else {
            System.out.println("We're keeping your initial choice of dates.");
            return daysnum;
        }
    }
}
