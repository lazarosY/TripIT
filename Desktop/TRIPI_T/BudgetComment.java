package trip;
import java.util.Scanner;

public class BudgetComment {
    public String BudgetComparison(UserInput userInput, double recbudget) {
        double bud = userInput.getbud(); 
        String dest = userInput.getdestination();
        long days = userInput.getDiffInDays();
        if(bud < recbudget) {
            System.out.println("Your budget is limited in comparison with the recommended amount you'll need for the activities of your trip.");
            System.out.println("Would you like to switch to the recommended budget of " + recbudget + "?" );
            System.out.println("Insert 1 for change, 2 for keeping your initial choice");
            Scanner input = new Scanner(System.in);
            int ans = input.nextInt();
            if(ans == 1) {
                bud = recbudget;
                System.out.println("We have successfully switched your budget to the recommended amount of " + bud + "€");
            } else {
                System.out.println("Would you like to travel to a destination that fits your budjet more, or go to " + dest + " with your initial choice of budget?");
                System.out.println("Insert 1 for recommendation of different destinations, 2 for keeping your initial choice.");
                Scanner input2 = new Scanner(System.in);
                int ans2 = input2.nextInt();
                if (ans2 == 1) {
                    String message4 = ("Give us 3 international destinations that fit a budget of " + bud +
                         ". The budget is in euros and only contains the activities for " + days +
                             " days. Give me the answer in this fprmat: 1.FirstDestination, 2.SecondDestination, 3.ThirdDestination all in the same line. I don't need an introduction, nor an explanation, except from the three destinations in the format mentioned."); 
                    String response2 = GPT.chatGPT(message4);
                    String[] cityArray = response2.split(",\\s+");
                    String city1 = cityArray[0] + ", " + cityArray[1];
                    String city2 = cityArray[2] + ", " + cityArray[3];
                    String city3 = cityArray[4] + ", " + cityArray[5];
                    System.out.println(city1);
                    System.out.println(city2);
                    System.out.println(city3);
                    System.out.println("Where would you like to travel from these recommended destinations?");
                    System.out.println("Insert 1, 2 or 3.");
                    int choice1 = input.nextInt();
                    if (choice1==1) {
                        return city1;
                    }else if(choice1==2){
                        return city2;
                    }else{
                        return city3;
                    }

                } else {
                    System.out.println("You chose to travel to " + dest +"with a more limited budget");
                    return dest;
                }
            }
        } else {
            System.out.println("Your budget to travel to " + dest + " is more than enough compared to the recommended amount needed for this trip.");
            return dest;
        }
        return dest;
    }
}
