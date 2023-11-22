import java.util.Scanner;

public class Budget extends GPT {
    private double userBudget;
    private String destination;
    private String preferences;
    private double minimumRequiredBudget; 
    private Scanner in = new Scanner(System.in);

    public Budget(double userBudget, String destination, String preferences) {
        this.userBudget = userBudget;
        setDestination(destination);  // Set destination using the setter to fetch minimum budget
        this.preferences = preferences;
    }

    public void setUserBudget(double userBudget) {
        this.userBudget = userBudget;
    }

    //Setters and Getters
    public double getUserBudget() {
        return userBudget;
    }

    public void setDestination(String destination) {
        this.destination = destination;
        this.minimumRequiredBudget = GPT.getMinimumBudgetForDestination(destination); // Fetch minimum required budget
    }

    public String getDestination() {
        return destination;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public String getPreferences() {
        return preferences;
    }

    public double getMinimumRequiredBudget() {
        return minimumRequiredBudget;
    }

    // Process the response and decide on the next step
    public String planTrip() {
        if (userBudget < minimumRequiredBudget) {
            System.out.println("TO CHANGE YOUR BUDGET TYPE Yes OR TAKE AN ALTERNATIVE DESTINATION TYPE No");
            String response = in.nextLine();
            switch (response) {
                case "Yes":
                    // Allow the user to update the budget
                    while (userBudget < minimumRequiredBudget) {
                        System.out.println("Enter new budget: ");
                        userBudget = in.nextDouble();
                        in.nextLine(); // Consume newline
                        if (userBudget < minimumRequiredBudget) {
                            System.out.println("Your budget is still below the recommended. Press 'No' if you changed opinion.");
                            response = in.nextLine();
                            if ("No".equals(response)) {
                                break;
                            }
                        }
                    }
                    break;
                case "No":
                    return suggestAlternative();
            }
        }
        return "Your plan is ready."; // Placeholder for successful plan
    }

    public boolean isWithinBudget() {
        return userBudget >= minimumRequiredBudget;
    }

    private String suggestAlternative() {
        // Placeholder for alternative destination suggestion
        // This can be enhanced to provide more sophisticated suggestions based on user preferences and budget
        return "Alternative destination suggestion based on your budget and preferences.";
    }
public static void main(String[] args) {
        // Create an instance of Budget and test the functionality
        Budget myBudget = new Budget(500, "Paris", "Sightseeing");
        System.out.println(myBudget.planTrip());
        // Additional testing code as required
    }
}
