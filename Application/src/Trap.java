import java.util.ArrayList;
import java.util.Scanner;

public class Trap {
    int placedByPlayerID=0;
    int trapID;
    boolean isActive=false;
    int[] trapPrice = new int[6];
    public Trap(){
        for (int i = 1; i <= 5; i++) {
            ArrayList<String> chance = Reader.read("Trap", i, 4);
            int cost = Integer.parseInt(chance.get(3));
            trapPrice[i]= cost;
        }
    }

    /**
     * Initiates the specific actions for the {@code Trap} tile
     * @param player The current player placed on the tile
     */
    public void activate(Player player){
        if(isActive) setOff(player);
        else placeTrap(player);

    }

    /**
     * Activates the trap placed on this tile
     * @param player The current player placed on the tile
     */
    private void setOff(Player player){
        int dice =Application.throwDice(1,10);

        if(player.id==placedByPlayerID && dice%3==0) {
            System.out.println("Попадна в свой капан, но избегна последствията!");
            return;
        }
        System.out.println("Капан задействан от "+player.getFullPlayerType());
        placedByPlayerID=0;
        isActive=false;
        player.trapsID[trapID]++;
        trapID=0;
    }

    /**
     * Allows the placement of a trap based on the current players` type
     * @param player The current player placed on the tile
     */
    private void placeTrap(Player player){
        if(player.trapsID[3]>0) return;

        if(player.isBot) botPlacesTrap(player);
        else playerPlacesTrap(player);
    }

    /**
     * Prompts a bot choose whether to place a trap or not
     * @param player The current player placed on the tile
     */
    private void botPlacesTrap(Player player){

        if (Application.throwDice(0,1)==1){
            int selectedTrap= Application.throwDice(1,5);
            selectTrap(player,selectedTrap);
            return;
        }
        System.out.println("Капан не е поставен от Бот "+player.id);
    }

    /**
     * Checks whether the bot can place a trap. If yes than he places it,otherwise he doesn`t
     * @param player The current player placed on the tile
     * @param selectedTrap The trap selected by the bot to place
     */
    private void selectTrap(Player player,int selectedTrap){
        if(player.cash<trapPrice[selectedTrap]) return;
        trapID=selectedTrap;
        player.cash-=trapPrice[trapID];
        placedByPlayerID=player.id;
        isActive=true;
        System.out.println("Капан е поставен от Бот "+player.id);
    }

    /**
     * Prompts a dialog for a Human player to place a trap
     * @param player The current player placed on the tile
     */
    private void playerPlacesTrap(Player player){
        int trapChoice = makeDecision(0,5);

        if (trapChoice>0 && player.cash>=trapPrice[trapChoice]){
            player.cash-=trapPrice[trapChoice];
            placedByPlayerID=player.id;
            isActive=true;
            System.out.println("Капан е поставен от "+player.getFullPlayerType());
        }
        else System.out.println("Капан не е поставен от "+player.getFullPlayerType());
    }

    private void printMenu() {
        System.out.println("(0) : Не, благодаря, не вярвам в злото");
        for (int i = 1; i <= 5; i++) {
            ArrayList<String> chance = Reader.read("Trap", i, 4);
            String trapName = chance.get(0);
            int cost = Integer.parseInt(chance.get(3));
            System.out.println(String.format("(%d) : %s (%d)",i,trapName,cost));
        }
    }
    public int makeDecision(int lowerBound,int upperBound){
        Scanner scanner = new Scanner(System.in);
        boolean correctInput = false;
        int option=0;
        while (!correctInput){
            printMenu();
            option=scanner.nextInt();
            correctInput= Application.isNumberInRange(option,lowerBound,upperBound);
        }
        return option;
    }
}
