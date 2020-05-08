import java.util.Random;

public class Application {
    public static void main(String[] args) {

    }

    /**
     * Generates a random number withing a certain range
     * @param lowerBound smallest number to generate
     * @param upperBound biggest number to generate
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
}
