public class Trap {
    int placedByPlayerID=0;
    int trapID;
    boolean isActive=false;
    int[] trapPrice = new int[]{0,100,200,100,50,100};

    // TODO: 13-May-20 Re-Check Functionality
    public void activate(Player player){
        if(isActive) setOff(player);
        else placeTrap(player);

    }

    private void setOff(Player player){
        int dice =Application.throwDice(1,10);

        if(player.id==placedByPlayerID && dice%3==0) {
            System.out.println("Felt into your own trap, but successfully escaped!");
            return;
        }
        System.out.println("Trap Set Off by "+player.getFullPlayerType());
        placedByPlayerID=0;
        isActive=false;
        player.trapsID[trapID]++;
        trapID=0;
    }

    private void placeTrap(Player player){
        if(player.trapsID[3]>0) {
            System.out.println("Active De-buff! Cant Place Traps");
            return;
        }
        if(player.isBot) botPlacesTrap(player);
        else playerPlacesTrap(player);
    }

    private void botPlacesTrap(Player player){

        if (Application.throwDice(0,1)==1){
            int selectedTrap= Application.throwDice(1,5);
            selectTrap(player,selectedTrap);
            return;
        }
        System.out.println("Trap NOT placed by "+player.id);
    }

    private void selectTrap(Player player,int selectedTrap){
        if(player.cash<trapPrice[selectedTrap]) return;
        trapID=selectedTrap;
        player.cash-=trapPrice[trapID];
        placedByPlayerID=player.id;
        isActive=true;
        System.out.println("Trap placed by "+player.id);
    }


    private void playerPlacesTrap(Player player){
        int trapChoice = player.makeDecision("Want to place a trap?");

        if (trapChoice>0 && player.cash>=trapPrice[trapChoice]){
            player.cash-=trapPrice[trapChoice];
            placedByPlayerID=player.id;
            isActive=true;
            System.out.println("Trap placed by "+player.getFullPlayerType());
        }
        else System.out.println("Trap NOT placed by "+player.getFullPlayerType());
    }
}
