public class Steal {
    int planId=0;
    boolean isTaken=false;

    public void steal(Player player){
        checkEvilPlan(player);
        System.out.println("Looks like we have a robber!"+player.getPlayerType()+player.id);
        if(planId==0 && !player.isPlanActive && !isTaken) assignPlan(player);
    }
    private void assignPlan(Player player){
        isTaken=true;
        planId= player.planId;
        player.isPlanActive=true;
    }
    public void clearTile(){
        this.planId=0;
        isTaken=false;
    }
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==3) {
            player.cash += 100;
            System.out.println("Evil plan activated "+ player.getPlayerType() + player.id);
        }
    }
}
