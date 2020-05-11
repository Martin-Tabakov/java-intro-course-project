public class Tile {

    Start start = null;
    Invest invest = null;
    Steal steal = null;
    Chance chance = null;
    Party party = null;
    Trap trap = null;

    int id;
    int pos;
    String tileSymbol;

    /**
     * Constructor for a tile. Creates a tiles specific role based on its ID
     * @param id ID of the tiles behaviour
     * @param pos The position where it is located on the board
     */
    public Tile(int id,int pos){
        this.id = id;
        this.pos = pos;
        createTile();
    }

    /**
     * Activates this tile`s effects when a player lands on it
     * @param player Current active player, stepping on a tile
     */
    public void setTile(Player player){
        switch (id){
            case 0: start.calculateCash(player); break;
            case 1: invest.invest(player); break;
            case 2: steal.steal(player); break;
            case 3: chance.activateChance(player); break;
            case 4: party.startParty(player); break;
            case 5: trap.activate(player); break;
        }
    }

    /**
     * Creates a new type of tile based on this tile`s ID
     */
    private void createTile(){
        switch (id){
            case 0: start = new Start(); tileSymbol = "St"; break;
            case 1: invest = new Invest(); tileSymbol = "I "; break;
            case 2: steal = new Steal(); tileSymbol = "S " ; break;
            case 3: chance = new Chance(); tileSymbol = "C "; break;
            case 4: party = new Party(); tileSymbol = "P "; break;
            case 5: trap = new Trap(); tileSymbol = "T "; break;
        }
    }
}
