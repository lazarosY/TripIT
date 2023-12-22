import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
    
        //New user 
        Hash.registration("test2@test.com", "tester");
        
        //Login user wrong password
        if (DataBaseConnectivity.authentication("test@test.com", "abc")) {
            System.out.println("Successful login");
        } else {
            System.out.println("Wrong email or password");
        }

        //Login user correct password
        if (DataBaseConnectivity.authentication("test@test.com", "tester")) {
            System.out.println("Successful login");
        } else {
            System.out.println("Wrong email or password");
        }

        LocalDate dep_date = LocalDate.of(2024, 1, 23);
        LocalDate arrival_date = LocalDate.of(2024, 1, 28);

        //SearchInsert 
        SearchHistory.saveSearch(1,"Paris",dep_date, arrival_date,"history",1000);
        


    }
}