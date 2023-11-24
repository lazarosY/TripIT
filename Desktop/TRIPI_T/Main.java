import java.text.ParseException;

public class Main {

    public static void main(String[] args) throws ParseException {
        //UserEntry3 userEntry = new UserEntry3();
        //userEntry.start();
        UserInput user = new  UserInput();
        user.dest();
        user.getTripDates();
        user.budget();
        user.pref();
        DateComment obj = new DateComment();
        obj.datecomparison(user,10);
    }  
}
