import java.util.Random;

/**
 * @author Martin Tabakov
 */
public class Application {
    public static void main(String[] args) {
        Game game = new Game();
        game.setBotQuantity();
        playGameLoops(game);
    }

    /**
     * The main game loop. Runs until there is a winner
     *
     * @param game The Game Object
     */
    public static void playGameLoops(Game game) {
        while (true) {
            game.shuffleBoard();
            game.makeCycle();
            if (endGame(game)) return;
        }
    }

    /**
     * Checks if there is a winner at the end of each cycle and prints the end game message
     *
     * @param game Game Object
     * @return {@code true} if there is a winner, otherwise {@code false}
     */
    public static boolean endGame(Game game) {
        if (game.haveWinner) {
            System.out.println("Победител е " + game.winner.getFullPlayerType());
            return true;
        }
        return false;
    }

    /**
     * Generates a random number withing a certain range
     *
     * @param lowerBound Smallest number to generate,inclusive
     * @param upperBound Biggest number to generate,inclusive
     * @return Random number
     */
    public static int RNG(int lowerBound, int upperBound) {
        Random random = new Random();
        int rnd;
        if (lowerBound < 0) {
            rnd = random.nextInt(Math.abs(lowerBound) + Math.abs(upperBound) + 1);
            return rnd - Math.abs(lowerBound);
        } else {
            rnd = random.nextInt(upperBound - lowerBound + 1);
            return rnd + lowerBound;
        }
    }

    /**
     * Checks whether a number is within certain limits, including the limit values
     *
     * @param number     The number to be checked
     * @param lowerLimit The minimum allowed value
     * @param upperLimit The maximum allowed value
     * @return {@code true} if the number is within limits, otherwise {@code false}
     */
    public static boolean isNumberInRange(int number, int lowerLimit, int upperLimit) {
        return number >= lowerLimit && number <= upperLimit;
    }


}
