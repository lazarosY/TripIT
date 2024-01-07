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
        String response= GPT.chatGPT(message);
        Boolean foundNumber = false;
        while (!foundNumber) {
            if (response.matches("^\\d+$")) {
                foundNumber = true;
            }else {
                response= GPT.chatGPT(message);
            }
        }
        int ChatDays = Integer.parseInt(response);
        DateComment obj = new DateComment();
        Double bud = user.getbud();
        long days = obj.dateComparison(user,ChatDays);
        String message3 = " For visiting " + city + " for "+ days +
            " an absolute amount of money in euros based on only the activities someone would do without containing the accomodation and the transport,with no introduction,no explanation, no other word after the number just the number.";
        String response3 = GPT.chatGPT(message3);
        Boolean valid1= false;
        while(!valid1){
            if (response3.matches("^\\d+(\\.\\d+)?$")) {
                valid1 = true;
            }else{
               response3 = GPT.chatGPT(message3); 
            }
        }
        double recbudget = Double.parseDouble(response3);
        BudgetComment obj2 = new BudgetComment();
        String dest = obj2.BudgetComparison(user, recbudget);
        String inte = user.getPref();
        String message2 = "I want you to give me a detailed schedule with specific activities for each day separately based on the following interests " + inte +  " for " + dest
            + " with the total money that can be spent being " + bud + ".";
        String response2 = GPT.chatGPT(message2);
        try {
            ProgramCreator.programMaker(response2);
            System.out.println("The file was successfully created on the Desktop.");
        } catch (IOException e) {
            System.err.println("Error while creating the file: " + e.getMessage());
        }
    }  
}
