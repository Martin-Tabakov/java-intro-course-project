public class Steal {
    int planId=0;
    boolean isTaken=false;

    /**
     * Initiates the specific actions for the {@code Steal} tile
     * @param player The current player placed on the tile
     */
    public void steal(Player player){
        checkEvilPlan(player);
        System.out.println("Крадец "+player.getFullPlayerType());
        if(activeDeBuff(player)) return;
        if(planId==0 && !player.isPlanActive && !isTaken) setTilePlan(player);
    }

    /**
     * Checks whether the player has a De-Buff active that prevents him from activating the tile
     * @param player The current player
     * @return {@code true} If the player has a De-Buff, otherwise {@code false}
     */
    private boolean activeDeBuff(Player player){
        if(player.trapsID[4]>0){
            System.out.println("Active De-Buff! Cant Activate Trap!");
            player.trapsID[4]=0;
            return true;
        }
        return false;
    }

    /**
     * Sets the tile the player`s plan and makes the tile unusable by another player for the current ame cycle
     * @param player The current player
     */
    private void setTilePlan(Player player){
        isTaken=true;
        planId= player.planId;
        player.isPlanActive=true;
    }

    /**
     * Clears the tile, making it usable for this game cycle
     */
    public void clearTile(){
        this.planId=0;
        isTaken=false;
    }

    /**
     * Activates an Evil plan if the player has it and is active
     * @param player The current player placed on the tile
     */
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==3) {
            player.cash += 100;
            System.out.println("Зъл план активиран от "+ player.getFullPlayerType());
        }
    }
}
