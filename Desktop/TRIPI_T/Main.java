package trip;
import java.io.IOException;
import java.text.ParseException;
public class Main {
    public static  void main(String[] args) throws ParseException {
        UserEntry3 userEntry = new UserEntry3();
        userEntry.start();
        UserInput user = new  UserInput();
        user.dest();
        user.getTripDates();
        user.budget();
        user.pref();
        String city = user.getdestination();
        String message = "Give me an absolute number of days concerning the days you suggest  to go on an excursion  in the " + city + " based on its size and the attractions it has,with no introduction,no explanation, no other word after the number just the number";
        String response = GPT.chatGPT(message);
        System.out.println(response);
        int ChatDays = Integer.parseInt(response);
        DateComment obj = new DateComment();
        obj.datecomparison(user,ChatDays);
        Double bud = user.getbud();
        long days = obj.datecomparison(user,ChatDays);
        String message3 = " For visiting " + city + " for "+ days +
            " an absolute amount of money in euros based on only the activities someone would do without containing the accomodation and the transport,with no introduction,no explanation, no other word after the number just the number.";
        String response3 = GPT.chatGPT(message3);
        double recbudget = Integer.parseInt(response3);
        System.out.println(recbudget);
        BudgetComment obj2 = new BudgetComment();
        String dest = obj2.BudgetComparison(user, recbudget);
        String inte = user.getPref();
        String message2 = "I want you to give me a schedule for each day separately based on the following interests " + inte + " for the " + dest
            + " with total money that can be spent to be " + bud + ". All in the same line.";
        String response2 = GPT.chatGPT(message2);
        System.out.println(response2);
        try {
            ProgramCreator.programMaker(response2);
            System.out.println("Το αρχείο δημιουργήθηκε με επιτυχία.");
        } catch (IOException e) {
            System.err.println("Σφάλμα κατά τη δημιουργία του αρχείου: " + e.getMessage());
        }
    }  
}
