import java.util.Scanner;


public class BudgetComment {
    public void BudgetComparison(double bud, double recbudget) {
        if(bud < recbudget) {
            System.out.println("Your budget is limited in comparison with the recommended amount you'll need for the activities of your trip.");
            System.out.println("Would you like to switch to the recommended budget of " + recbudget + "?" );
            System.out.println("Insert 1 for change, 2 for keeping your initial choice");
            Scanner input = new Scanner(System.in);
            String ans = input.nextLine();
            if(ans == 1) {
                bud = recbudget;
                System.out.println("We have successfully switched your budget to the recommended amount of " + bud + "€");
            } else {
                System.out.println("Would you like to travel to a destination that fits your budjet more, or go to" + dest + " with your initial choice of budget?");
                System.out.println("Insert 1 for recommendation of different destinations, 2 for keeping your initial choice.");
                Scanner input2 = new Scanner(System.in);
                String ans2 = input2.nextLine();
                if (ans2 == 1) {
                    //syndesh me CHATGPT
                } else {
                    System.out.println("You chose to travel to " + dest +"with a more limited budget");
                }
            }
        } else {
            System.out.println("Your budget to travel to " + dest + "is more than enough compared to the recommended amount needed for this trip");
        }
    }
}
