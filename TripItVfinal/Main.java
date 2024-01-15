import java.io.IOException;
import java.text.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) throws ParseException {
        SetupDatabase.dbSetup();
        UserEntry3 userEntry = new UserEntry3();
        userEntry.start();
        UserInput user = new UserInput();
        user.dest();
        user.getTripDates();
        user.budget();
        user.pref();
        String city = user.getdestination();
        String message = "Give me an absolute number of days concerning the days you suggest  to go on an excursion  in the "
                + city
                + " based on its size and the attractions it has,with no introduction,no explanation, no other word after the number just the number";
        String response = GPT.chatGPT(message);
        Boolean foundNumber = false;
        while (!foundNumber) {
            if (response.matches("^\\d+$")) {
                foundNumber = true;
            } else {
                response = GPT.chatGPT(message);
            }
        }
        int ChatDays = Integer.parseInt(response);
        DateComment obj = new DateComment();
        Double bud = user.getbud();
        long days = obj.dateComparison(user, ChatDays);
        System.out.println(days);
        String message3 = " For visiting " + city + " for " + days +
                " an absolute amount of money in euros based on only the activities someone would do without containing the accomodation and the transport,with no introduction,no explanation, no other word after the number just the number.";
        String response3 = GPT.chatGPT(message3);
        Boolean valid1 = false;
        while (!valid1) {
            if (response3.matches("^\\d+(\\.\\d+)?$")) {
                valid1 = true;
            } else {
                response3 = GPT.chatGPT(message3);
            }
        }
        double recbudget = Double.parseDouble(response3);
        BudgetComment obj2 = new BudgetComment();
        user = obj2.BudgetComparison(user, recbudget);
        String dest = user.getdestination();
        bud = user.getbud();
        String inte = user.getPref();
        String message2 = "I want you to give me a detailed schedule with specific activities for each day separately based on the following interests "
                + inte + " for " + dest
                + " with the total money that can be spent being " + bud + ".";
        String response2 = GPT.chatGPT(message2);

        String reminder = response2
                + "Give me the name of the most famous place tha was cited to visit in this message that we previously got from you, no introduction no explanation just a name";
        String poi = GPT2.chatGPT2(reminder);
        String latitude = GPT2
                .chatGPT2("Give me the latitude of " + poi + " in google maps. No explanation, no introduction print only numbers");
        String longtitude = GPT2
                .chatGPT2("Give me the longtitude of " + poi + " in google maps. No explanation, no introduction print only numbers");
        String place_id = PlacesAPIgoogle.getplaceid(latitude,longtitude);
        System.out.println("--------------------------------------------------------------");

        MAPS help = new MAPS();
        JSONObject json = new JSONObject(help.GoogleReviews(place_id));
        System.out.println(poi);

        if (json.has("result") && json.getJSONObject("result").has("reviews")) {
            JSONArray jsonarr = json.getJSONObject("result").getJSONArray("reviews");

            for (int i = 0; i < jsonarr.length(); i++) {
                JSONObject review = jsonarr.getJSONObject(i);
                String authorName = review.getString("author_name");
                double rating = review.getDouble("rating");
                String reviewText = review.getString("text");

                System.out.println("Review #" + (i + 1));
                System.out.println("Author: " + authorName);
                System.out.println("Rating: " + rating);
                System.out.println("Review: " + reviewText);
                System.out.println("----------------------------");
            }
            System.out.print("Overall" + help.GoogleRating(place_id));
        } else {
            PlaceReviews.reviews(poi);
        }
        try {
            ProgramCreator.programMaker(response2, dest);
            System.out.println("The file was successfully created on the Desktop.");
        } catch (IOException e) {
            System.err.println("Error while creating the file: " + e.getMessage());
        }
    }
}