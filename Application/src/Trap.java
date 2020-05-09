public class Trap {
    int placedByPlayerID=0;
    boolean isActive=false;

    public void activate(Player player){
        if(isActive) setOff(player);
        else placeTrap(player);

    }

    private void setOff(Player player){
        if(player.id==placedByPlayerID){
            System.out.println("Felt into your own trap! HAAHAHhahA");
            int dice =Application.throwDice(1,10);
            if(dice%3 !=0){
                System.out.println("Thou shall pay the price for thy greed!");
                player.cash-=100;
                placedByPlayerID=0;
                isActive=false;
            }
            else{
                System.out.println("You escaped from your own trap! Thou shall not pay today! ");
            }
        }
        System.out.println("Trap Set Off by player "+player.id);
        placedByPlayerID=0;
        isActive=false;
    }

    private void placeTrap(Player player){
        System.out.println("Want to place a trap? Player"+ player.id);
        int willPlaceTrap = Application.throwDice(1,2);
        if (willPlaceTrap==1 && player.cash>=100){
            player.cash-=100;
            placedByPlayerID=player.id;
            isActive=true;
            System.out.println("Trap placed by "+player.id);
        }
        else System.out.println("Trap NOT placed by "+player.id);
    }
}
