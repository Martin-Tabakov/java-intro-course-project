public class Steal {
    int planId=0;
    boolean isTaken=false;

    public void steal(Player player){
        System.out.println("Looks like we have a robber! He is Player "+player.id);
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
}
