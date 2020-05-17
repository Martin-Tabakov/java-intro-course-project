import java.util.ArrayList;

public class Chance {

    /**
     * Initiates the specific actions for the {@code Chance} tile
     * @param player The current player placed on the tile
     */
    public void activateChance(Player player){
        checkEvilPlan(player);
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
        findChance(rng,5,player);
    }

    /**
     *Activates a negative chance
     * @param rng Random number. For current testing
     * @param player The current player placed on the tile
     */
    private void negativeChance(int rng,Player player){
        findChance(rng,0,player);
    }

    /**
     * Activates an Evil plan if the current player has it and is active
     * @param player The current player placed on the tile
     */
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==1){
            System.out.println("Зъл план активиран от "+ player.getFullPlayerType());
            player.cash+=100;
            String desc= Reader.read("Steal",1,3).get(2);
            System.out.println(desc);
        }
    }

    private void findChance(int rng,int offset,Player player){
        for(int i=1+offset;i<=5+offset;i++){
            ArrayList<String> chance = Reader.read("Chance",i,5);
            String chanceName = chance.get(0);
            String chanceDesc = chance.get(1);
            int rangeMin =Integer.parseInt(chance.get(2));
            int rangeMax =Integer.parseInt(chance.get(3));
            int money =Integer.parseInt(chance.get(4));

            if(rng>=rangeMin && rng <=rangeMax){
                player.cash+=money;
                System.out.println(chanceName);
                System.out.println(chanceDesc);
                return;
            }
        }
    }
}
