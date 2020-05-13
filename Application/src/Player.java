public class Player {

    int id;
    int companyId=0;
    int pos=0;
    int cash=1000;
    int income;
    int profit;
    int expenditure;
    int cycle=1;
    int planId=0;
    int[] trapsID = new int[6];
    boolean isPlanActive;
    boolean isBot;

    /**
     * Constructor for a game Participant(Player/Bot)
     * @param id Participant unique ID
     * @param isBot {@code true} If the participant is a Bot, {@code false } if he is a Player
     */
    // TODO: 11-May-20 Add human player Interface
    public Player(int id,boolean isBot) {
        this.id = id;
        this.isBot = isBot;
    }

    /**
     * Changes the position of the player X positions based on a thrown dice
     */
    public void makeTurn(){
        int posToMove = Application.throwDice(1,2);
        pos+=posToMove;
    }

    /**
     * Returns the player type
     * @return Player type
     */
    public String getPlayerType(){
        return (isBot)? "Бот":"Играч";
    }

    /**
     * Gets the game participant`s type and id
     * @return String containing Player type and Id
     */
    public String getFullPlayerType(){
        return String.format("%s %d",getPlayerType(),id);
    }

    /**
     * Calculates the remaining парички for the player after completing a game cycle
     */
    public void calculateCash(){
        computeDeBuffs();
        System.out.println("Начало на ход за " +getFullPlayerType());
    }

    /**
     * Calculates the de buffs that affect the profits and income of the player after the game cycle
     */
    private void computeDeBuffs(){
        int dice =Application.throwDice(1,10);
        if(trapsID[2]>0 && profit>0 && (dice==2 || dice==8)) profit=0;

        for(int i=1;i< trapsID[1];i++) profit-=profit/10;
    }

    /**
     * Assigns a random Evil plan to the player
     */
    public void assignEvilPlan(){
        planId= Application.throwDice(1,3);
        isPlanActive=false;
    }

}
