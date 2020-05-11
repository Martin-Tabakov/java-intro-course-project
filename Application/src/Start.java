public class Start {
    public static void calculateCash(Player player){

        System.out.println("The start of an adventure for Player "+player.id);
    }

    public static void assignEvilPlan(Player player){
        player.planId= Application.throwDice(1,3);
        player.isPlanActive=false;
    }
}