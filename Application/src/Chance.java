public class Chance {

    /**
     * Initiates the specific actions for the {@code Chance} tile
     * @param player The current player placed on the tile
     */
    public void activateChance(Player player){
        checkEvilPlan(player);
        System.out.println("Живота даде шанс на "+player.getFullPlayerType());
        getChancePositivity(player);
    }

    /**
     * Activates a positive or a negative chance
     * @param player The current player placed on the tile
     */
    private void getChancePositivity(Player player){
        int dice= activeDeBuff(player);
        int dice2= Application.throwDice(1,100);

        if(dice%2==0) positiveChance(dice2,player);
        else negativeChance(dice2,player);

    }

    /**
     * Checks whether the player has active De-Buff.
     * @param player Current active player
     * @return if the player has no De-Buff -random number that determinates  what chance he gets.
     * If the player has De-Buff -1 so that he gets a negative chance
     */
    private int activeDeBuff(Player player){
        if (player.trapsID[5]==0) return Application.throwDice(1,10);

        player.trapsID[5]=0;
        return 1;
    }

    /**
     * Activates a positive chance
     * @param rng Random number. For current testing
     * @param player The current player placed on the tile
     */
    private void positiveChance(int rng,Player player){
        player.cash+=rng;
        System.out.println("Позитивен Шанс за "+player.getFullPlayerType());
    }

    /**
     *Activates a negative chance
     * @param rng Random number. For current testing
     * @param player The current player placed on the tile
     */
    private void negativeChance(int rng,Player player){
        player.cash-=rng;
        System.out.println("Негативен Шанс за "+player.getFullPlayerType());
    }

    /**
     * Activates an Evil plan if the current player has it and is active
     * @param player The current player placed on the tile
     */
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==1){
            System.out.println("Зъл план активиран от "+ player.getFullPlayerType());
            player.cash+=100;
        }
    }
}
