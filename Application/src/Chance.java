public class Chance {
    public void activateChance(Player player){
        checkEvilPlan(player);
        System.out.println("Life gave a chance to Player "+player.id);
        getChancePositivity(player);
    }
    private void getChancePositivity(Player player){
        int dice= activeDeBuff(player);
        int dice2= Application.throwDice(1,100);

        if(dice%2==0) positiveChance(dice2,player);
        else negativeChance(dice2,player);

    }

    private int activeDeBuff(Player player){
        if (player.trapsID[5]==0) return Application.throwDice(1,10);

        player.trapsID[5]=0;
        return 1;
    }
    private void positiveChance(int rng,Player player){
        player.cash+=rng;
        System.out.println("Chance is positive for "+player.getPlayerType() + player.id);
    }
    private void negativeChance(int rng,Player player){
        player.cash-=rng;
        System.out.println("Chance is negative for "+player.getPlayerType() + player.id);
    }

    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==1){
            System.out.println("Evil plan activated "+ player.getPlayerType() + player.id);
            player.cash+=100;
        }
    }
}
