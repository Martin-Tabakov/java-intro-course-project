public class Trap {
    int placedByPlayerID=0;
    int trapID;
    boolean isActive=false;

    // TODO: 13-May-20 Re-Check Functionality
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
                player.cash-=100*trapID;
                placedByPlayerID=0;
                isActive=false;
                trapID=0;
            }
            else{
                System.out.println("You escaped from your own trap! Thou shall not pay today! ");
            }
        }
        System.out.println("Trap Set Off by "+player.getPlayerType() +player.id);
        placedByPlayerID=0;
        isActive=false;
        player.cash-=100*trapID;
        trapID=0;
    }

    private void placeTrap(Player player){


        if( player.isBot) botPlacesTrap(player);
        else playerPlacesTrap(player);

    }

    private void botPlacesTrap(Player player){
        int willPlaceTrap = Application.throwDice(1,2);

        if (willPlaceTrap==1){
            trapID= Application.throwDice(1,5);
            selectTrap(player);
        }
        else System.out.println("Trap NOT placed by "+player.id);
    }
    private void selectTrap(Player player){
        player.cash-=100*trapID;
        placedByPlayerID=player.id;
        isActive=true;
        System.out.println("Trap placed by "+player.id);
    }


    private void playerPlacesTrap(Player player){
        int trapChoice = player.makeDecision("Want to place a trap?");

        if (trapChoice>0 && player.cash>=100){
            player.cash-=100*trapChoice;
            placedByPlayerID=player.id;
            isActive=true;
            System.out.println("Trap placed by "+player.id);
        }
        else System.out.println("Trap NOT placed by "+player.id);
    }
}
