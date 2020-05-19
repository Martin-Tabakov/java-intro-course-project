import java.util.ArrayList;

public class Player {

    int id;
    int pos = 0;
    int cash = 1000;
    int income = 0;
    int profit = 0;
    int cycle = 1;
    int planId = 0;
    int[] trapsID = new int[6];
    boolean isPlanActive;
    boolean isBot;
    ArrayList<ArrayList<String>> investedCompanies = new ArrayList<>();

    /**
     * Constructor for a game Participant(Player/Bot)
     *
     * @param id    Participant unique ID
     * @param isBot {@code true} If the participant is a Bot, {@code false } if he is a Player
     */
    public Player(int id, boolean isBot) {
        this.id = id;
        this.isBot = isBot;
    }

    /**
     * Changes the position of the player X positions based on a thrown dice
     */
    public void makeTurn() {
        int posToMove = Application.RNG(1, 2);
        pos += posToMove;
    }

    /**
     * Returns the player type
     *
     * @return Player type
     */
    public String getPlayerType() {
        return (isBot) ? "Бот" : "Играч";
    }

    /**
     * Gets the game participant`s type and id
     *
     * @return String containing Player type and Id
     */
    public String getFullPlayerType() {
        return String.format("%s %d", getPlayerType(), id);
    }

    /**
     * Calculates the remaining парички for the player after completing a game cycle
     */
    public void calculateCash() {
        computeInvestments();
        calcIncomeDeBuff();
        profit = income;
        calcProfitDeBuff();

        System.out.println(getFullPlayerType() + " печалба " + profit);
        cash += profit;
        profit = 0;
        income = 0;
    }

    /**
     * Calculates the results of all investments made by the player
     */
    private void computeInvestments() {
        ArrayList<String> temp;
        for (ArrayList<String> investedCompany : investedCompanies) {
            temp = investedCompany;
            int investedCash = Integer.parseInt(temp.get(0));
            float returnMultiplier = Float.parseFloat(temp.get(1));
            int bottomRange = Integer.parseInt(temp.get(2));
            int topRange = Integer.parseInt(temp.get(3));
            int rng = Application.RNG(bottomRange, topRange);

            System.out.println(String.format("%d %d %d %d", investedCash, bottomRange, topRange, rng));
            if (rng >= 0) income += investedCash * returnMultiplier + investedCash;
        }
        investedCompanies = new ArrayList<>();
    }

    /**
     * Calculates the result after applying the Profit deBuff if the player has one.
     */
    private void calcProfitDeBuff() {
        int dice = Application.RNG(1, 10);
        if (trapsID[2] > 0 && profit > 0 && (dice == 2 || dice == 8)) profit = 0;
    }

    /**
     * Calculates the result after applying the Income deBuff if the player has one or more.
     */
    private void calcIncomeDeBuff() {
        for (int i = 0; i < trapsID[1]; i++) income -= income / 10;
    }

    /**
     * Assigns a random Evil plan to the player
     */
    public void assignEvilPlan() {
        planId = Application.RNG(1, 3);
        isPlanActive = false;
    }

}
