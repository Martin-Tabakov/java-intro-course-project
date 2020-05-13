public class Steal {
    int planId=0;
    boolean isTaken=false;

    public void steal(Player player){
        checkEvilPlan(player);
        System.out.println("Looks like we have a robber!"+player.getPlayerType()+player.id);
        if(activeDeBuff(player)) return;
        if(planId==0 && !player.isPlanActive && !isTaken) assignPlan(player);
    }

    private boolean activeDeBuff(Player player){
        if(player.trapsID[4]>0){
            System.out.println("Active De-Buff! Cant Activate Trap!");
            player.trapsID[4]=0;
            return true;
        }
        return false;
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
