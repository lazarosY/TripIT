import java.io.IOException;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;
public class Main {
    public static  void main(String[] args) throws ParseException {
        String message5;
        String longtitude;
        String latitude;
        int retryCount = 0;
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
        obj.datecomparison(user,ChatDays);
        Double bud = user.getbud();
        long days = obj.datecomparison(user,ChatDays);
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
        System.out.println(response3);
        double recbudget = Double.parseDouble(response3);
        BudgetComment obj2 = new BudgetComment();
        String dest = obj2.BudgetComparison(user, recbudget);
        String inte = user.getPref();
        String message2 = "I want you to give me a detailed schedule with specific activities for each day separately based on the following interests " + inte +  " for " + dest
            + " with the total money that can be spent being " + bud + ".";
        String response2 = GPT.chatGPT(message2);
        String message4 =  response2 + "I want you to return the name of one of the places you proposed with no introduction , no explanation just a name";
        String response4 = GPT.chatGPT(message4);
        while (retryCount < 3) {  // You can adjust the number of retries as needed
            try {
                //have to add requests here
                message5= "give me the latitude of "+ response4 +" with no introduction , no explanation just a number";
                latitude = GPT.chatGPT(message5);
                response4 ="give me the longtitude of"+response4+ "with no introduction , no explanation just a number";
                longtitude = GPT.chatGPT(message5);
                break;  
            } catch (Exception e) {
                if (e.getMessage().contains("429")) {
                    //Retries if the rate is limited
                    System.out.println("Rate limited. Retrying after 5 seconds...");
                    sleep(5000);  
                    retryCount++;
                }
            }
        }
        PlacesAPIgoogle obj3 = new PlacesAPIgoogle();
        String id= obj3.getplaceid(latitude,longtitude);
        MAPS obj4 = new MAPS();
        JSONObject json = new JSONObject(obj4.GoogleReviews(id));
        JSONArray jsonarr = json.getJSONObject("result").getJSONArray("reviews");
        for (int i=0;i<jsonarr.length();i++){
            JSONObject review = jsonarr.getJSONObject(i);
            String sreview = review.toString();
            System.out.println(sreview);
        }  
        System.out.print("Overall"+obj4.GoogleRating(id));
        try {
            ProgramCreator.programMaker(response2);
            System.out.println("The file was successfully created on the Desktop.");
        } catch (IOException e) {
            System.err.println("Error while creating the file: " + e.getMessage());
        }
    }  
    private static void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    


}
