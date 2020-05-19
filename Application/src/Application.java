import java.util.Random;

public class Application {
    public static void main(String[] args){
        Game game = new Game();
        game.setBotQuantity();
        playGameLoops(game);
    }

    public static void playGameLoops(Game game){

        while (true){
            game.shuffleBoard();
            game.makeCycle();
            if (endGame(game)) return;
        }
    }

    public static boolean endGame(Game game){
        if(game.haveWinner) {
            System.out.println("Победител е " + game.winner.getFullPlayerType());
            return true;
        }
        else return false;
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
    public static boolean isNumberInRange(int number, int lowerBound, int upperBound) {
        return number>=lowerBound && number<=upperBound;
    }



}
