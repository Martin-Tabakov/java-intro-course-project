import java.util.Random;
import java.util.Scanner;

public class Application {
    public static void main(String[] args){
        Game game = new Game();
        game.setBotQuantity();
        game.shuffleBoard();
        game.makeCycle();
        System.out.println("////////////////////////////////////////////////////////////");
        game.shuffleBoard();
        game.makeCycle();
        System.out.println("////////////////////////////////////////////////////////////");
    }

    /**
     * Generates a random number withing a certain range
     * @param lowerBound Smallest number to generate
     * @param upperBound Biggest number to generate
     * @return Random number
     */
    public static int throwDice(int lowerBound,int upperBound){
        Random random = new Random();
        int rnd;
        if(lowerBound<0){
            rnd = random.nextInt(Math.abs(lowerBound)+Math.abs(upperBound)+1);
            return rnd- Math.abs(lowerBound);
        }
        else{
            rnd = random.nextInt(upperBound-lowerBound+1);
            return rnd + lowerBound;
        }
    }

    /**
     * Checks whether a number is within certain limits, including the limit values
     * @param number The number to be checked
     * @param lowerBound The minimum allowed value
     * @param upperBound The maximum allowed value
     * @return {@code true} if the number is within limits, otherwise {@code false}
     */
    public static boolean isNumberInRange(int number, int lowerBound, int upperBound)
    {
        return number>=lowerBound && number<=upperBound;
    }

    /**
     * Prompts the Player to insert a number within a range until the number is correct
     * @param messages Array of messages to display on the console
     * @param lowerBound Minimum value
     * @param upperBound Maximum value
     * @return The correctly inserted number
     */
    public static int makeDecision(String[] messages,int lowerBound,int upperBound){
        Scanner scanner = new Scanner(System.in);
        boolean correctInput = false;
        int option=0;
        while (!correctInput){

            for (String message : messages) System.out.println(message);

            option=scanner.nextInt();
            correctInput= Application.isNumberInRange(option,lowerBound,upperBound);
        }
        return option;
    }
}
