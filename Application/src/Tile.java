public class Tile {

    Start start = null;
    Invest invest = null;
    Steal steal = null;
    Chance chance = null;
    Party party = null;
    Trap trap = null;

    int id;
    int pos;
    char tileSymbol;

    public Tile(int id,int pos){
        this.id = id;
        this.pos = pos;
        createTile();
    }

    /**
     * Activates the effect of the tile the player is placed on
     * @param playerId ID of the player that activates the tile effect
     */
    public void setTile(int playerId){
        switch (id){
            case 0: start.calculateCash(playerId); break;
            case 1: invest.invest(playerId); break;
            case 2: steal.steal(playerId); break;
            case 3: chance.activateChance(playerId); break;
            case 4: party.startParty(playerId); break;
            case 5: trap.activate(playerId); break;
        }
    }

    private void createTile(){
        switch (id){
            case 0: start = new Start(); tileSymbol = 'X'; break;
            case 1: invest = new Invest(); tileSymbol = 'I'; break;
            case 2: steal = new Steal(); tileSymbol = 'S'; break;
            case 3: chance = new Chance(); tileSymbol = 'C'; break;
            case 4: party = new Party(); tileSymbol = 'P'; break;
            case 5: trap = new Trap(); tileSymbol = 'T'; break;
        }
    }
}
