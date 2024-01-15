import java.util.Scanner;

public class BudgetComment {

    public UserInput BudgetComparison(UserInput userInput, double recbudget) {

        double bud = userInput.getbud();
        String dest = userInput.getdestination();
        long days = userInput.getDiffInDays();
        int ans = 0;
        int ans2 = 0;
        int choice1 = 0;

        if (bud < recbudget) {

            System.out.println(
                    "Your budget is limited in comparison with the recommended amount you'll need for the activities of your trip.");
            System.out.println("Would you like to switch to the recommended budget of " + recbudget + "?");
            System.out.println("Insert 1 for change, 2 for keeping your initial choice");
            Scanner input = new Scanner(System.in);
            boolean valid = false;
            while (!valid) {
                if (input.hasNextInt()) {
                    ans = input.nextInt();
                    if (ans == 1 || ans == 2) {
                        valid = true;
                    } else {
                        System.out.println("Please enter 1 or 2.");
                    }
                } else {
                    input.next(); // consume the invalid input
                    System.out.println("Please enter a valid integer (1 or 2).");
                }
            }

            if (ans == 1) {
                userInput.setbud(recbudget);
                System.out
                        .println("We have successfully switched your budget to the recommended amount of " + recbudget
                                + "€");
                return userInput;
            } else {
                System.out.println("Would you like to travel to a destination that fits your budget more, or go to "
                        + dest + " with your initial choice of budget?");
                System.out.println(
                        "Insert 1 for recommendation of different destinations, 2 for keeping your initial choice.");
                Scanner input2 = new Scanner(System.in);
                boolean valid2 = false;
                while (!valid2) {
                    if (input2.hasNextInt()) {
                        ans2 = input2.nextInt();
                        if (ans2 == 1 || ans2 == 2) {
                            valid2 = true;
                        } else {
                            System.out.println("Please enter 1 or 2.");
                        }
                    } else {
                        input2.next(); // consume the invalid input
                        System.out.println("Please enter a valid integer (1 or 2).");
                    }
                }
                if (ans2 == 1) {
                    String message4 = ("Give us 3 international destinations that fit a budget of " + bud +
                            ". The budget is in euros and only contains the activities for " + days +
                            " days. Give me the answer in this fprmat: 1.FirstDestination, 2.SecondDestination, 3.ThirdDestination all in the same line. I don't need an introduction, nor an explanation, except from the three destinations in the format mentioned.");
                    String response2 = GPT2.chatGPT2(message4);
                    String[] cityArray = response2.split(",\\s+");
                    String city1 = "";
                    String city2 = "";
                    String city3 = "";

                    try {
                        city1 = cityArray[0] + ", " + cityArray[1];
                        city2 = cityArray[2] + ", " + cityArray[3];
                        city3 = cityArray[4] + ", " + cityArray[5];
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("No alternatives found. Please start again.");
                        System.exit(1);
                    }
                    System.out.println(city1);
                    System.out.println(city2);
                    System.out.println(city3);
                    System.out.println("Where would you like to travel from these recommended destinations?");
                    System.out.println("Insert 1, 2 or 3.");
                    Scanner input3 = new Scanner(System.in);
                    boolean valid3 = false;
                    while (!valid3) {
                        if (input3.hasNextInt()) {
                            choice1 = input3.nextInt();
                            if (choice1 == 1 || choice1 == 2 || choice1 == 3) {
                                valid3 = true;
                            } else {
                                System.out.println("Please enter 1 or 2 or 3.");
                            }
                        } else {
                            input3.next(); // consume the invalid input
                            System.out.println("Please enter a valid integer (1 or 2 or 3).");
                        }
                    }
                    if (choice1 == 1) {
                        userInput.setdestination(city1);
                        return userInput;

                    } else if (choice1 == 2) {
                        userInput.setdestination(city2);
                        return userInput;
                    } else {
                        userInput.setdestination(city3);
                        return userInput;
                    }
                } else {
                    System.out.println("You chose to travel to " + dest + " with a more limited budget");
                }
            }
        } else {
            System.out.println("Your budget to travel to " + dest
                    + " is more than enough compared to the recommended amount needed for this trip.");
        }
        return userInput;
    }
}
