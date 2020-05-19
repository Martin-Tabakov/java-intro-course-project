public class Tile {

    Invest invest = null;
    Steal steal = null;
    Chance chance = null;
    Trap trap = null;

    int id;
    int pos;
    String tileSymbol;

    /**
     * Constructor for a tile. Creates a tiles specific role based on its ID
     *
     * @param id  ID of the tiles behaviour
     * @param pos The position where it is located on the board
     */
    public Tile(int id, int pos) {
        this.id = id;
        this.pos = pos;
        createTile();
    }

    /**
     * Activates this tile`s effects when a player lands on it
     *
     * @param player Current active player, stepping on a tile
     */
    public void setTile(Player player) {
        switch (id) {
            case 0:
                player.calculateCash();
                break;
            case 1:
                invest.invest(player);
                break;
            case 2:
                steal.steal(player);
                break;
            case 3:
                chance.activateChance(player);
                break;
            case 4:
                startParty(player);
                break;
            case 5:
                trap.activate(player);
                break;
        }
    }

    /**
     * Reduces money when a player lands on a {@code Party} tile
     *
     * @param player The current player placed on the tile
     */
    private void startParty(Player player) {
        System.out.println("Парти започнато от " + player.getFullPlayerType());
        player.cash -= 25;
    }

    /**
     * Creates a new type of tile based on this tile`s ID
     */
    private void createTile() {
        switch (id) {
            case 0:
                tileSymbol = "S ";
                break;
            case 1:
                invest = new Invest();
                tileSymbol = "I ";
                break;
            case 2:
                steal = new Steal();
                tileSymbol = "St";
                break;
            case 3:
                chance = new Chance();
                tileSymbol = "C ";
                break;
            case 4:
                tileSymbol = "P ";
                break;
            case 5:
                trap = new Trap();
                tileSymbol = "T ";
                break;
        }
    }

    /**
     * Swaps two tiles
     * @param gameBoard       The array containing all tiles
     * @param i               Current tile position
     * @param newTilePosition New tile position
     */
    public static void switchTilePosition(Tile[] gameBoard, int i, int newTilePosition) {

        Tile tempTile = gameBoard[newTilePosition];
        gameBoard[newTilePosition] = gameBoard[i];
        gameBoard[newTilePosition].pos = newTilePosition;
        gameBoard[i] = tempTile;
        gameBoard[i].pos = i;

    }
}
