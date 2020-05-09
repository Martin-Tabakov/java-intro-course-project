public class Chance {
    public void activateChance(Player player){
        System.out.println("Life gave a chance to Player "+player.id);
        getChancePositivity(player);
    }
    private void getChancePositivity(Player player){
        int dice= Application.throwDice(1,10);
        int dice2= Application.throwDice(1,100);
        if(dice%2==0) positiveChance(dice2,player);
        else negativeChance(dice2,player);

    }
    private void positiveChance(int rng,Player player){
        player.cash+=100;
        System.out.println("Chance is positive for player" + player.id);
    }
    private void negativeChance(int rng,Player player){
        player.cash-=100;
        System.out.println("Chance is negative for player" + player.id);
    }
}
