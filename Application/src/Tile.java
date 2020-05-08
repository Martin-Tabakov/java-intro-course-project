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
