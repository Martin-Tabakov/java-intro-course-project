import java.util.Random;

public class Game {
    int gameCycle = 0;
    Tile[] gameBoard = new Tile[20];
    Player[] players = new Player[5];

    public Game() {
        initBoard();
        initPlayers();
    }

    /**
     * Sets the initial tile positions for the tiles
     */
    private void initBoard() {
        int id=1,i;
        gameBoard[0] = new Tile(0, 0);
        for(i=1;i<=3;i++) gameBoard[i] = new Tile(id,i);
        id=2;
        for(;i<=6;i++) gameBoard[i] = new Tile(id,i);
        id=3;
        for(;i<=9;i++) gameBoard[i] = new Tile(id,i);
        id=4;
        for(;i<=12;i++) gameBoard[i] = new Tile(id,i);
        id=5;
        for(;i<=19;i++) gameBoard[i] = new Tile(id,i);
    }

    /**
     * Initialises the partaker`s positions and IDs
     */
    private void initPlayers() {
        players[0] = new Player(1, false, 0);
        for(int i=1;i<5;i++) players[i] = new Player(i+1, true, 0);
    }

    /**
     * Makes a game cycle - all players go through the board to the starting position from where they began
     */
    public void makeCycle() {
        gameCycle++;
        assignEvilPlans();
        boolean isPartialCycle = false;
        while (!isPartialCycle) {
            printBoard();
            printPlayerInfo();
            isPartialCycle= playerCycle();
        }
        sortPlayers();
        calculateBalance();
        clearTiles();
    }

    /**
     * Resets all {@code Steal} tiles to be reused
     */
    private void clearTiles(){
        for (Tile tile : gameBoard) if (tile.id == 2) tile.steal.clearTile();
    }

    /**
     * Calculates парички for players after completing a game cycle
     */
    private void calculateBalance(){
        for (Player player : players) Start.calculateCash(player);
    }
    /**
     * Assigns every player an evil plan
     */
    private void assignEvilPlans(){
        for (Player player : players) {
            Start.assignEvilPlan(player);
        }
    }

    /**
     * Cycles through the list of players prompting them to take a turn.
     * If the player`s cycle is equal to the game cycle a move will be made and the tile where the player lands will be activated
     * @return {@code true} if all players made a cycle, otherwise {@code true}
     */
    public boolean playerCycle() {
        Player currentPlayer;
        int playersMadeCycle =0;

        for (Player player : players) {
            currentPlayer = player;

            if (currentPlayer.cycle == gameCycle) {
                player.makeTurn();
                if (currentPlayer.pos > 19) {
                    currentPlayer.pos = 0;
                    currentPlayer.cycle++;
                    System.out.println("Player "+player.id+" moves to position "+player.pos);
                    continue;
                }
                System.out.println("Player "+player.id+" moves to "+player.pos);
                gameBoard[currentPlayer.pos].setTile(currentPlayer);
            } else playersMadeCycle++;

        }
        return playersMadeCycle ==players.length;
    }

    /**
     * Sorts the list of players in descending order by their парички
     */
    public void sortPlayers() {
        int n = players.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (players[j].cash < players[j + 1].cash) {
                    Player tempPlayer = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = tempPlayer;
                }
    }

    /**
     * Shuffles the positions of the tiles in the game board.
     * The {@code Start} tile stays in its place, unaffected by the shuffle
     */
    public void shuffleBoard() {
        Tile tempTile;
        int newTilePosition;
        Random random = new Random();
        for (int i = 1; i < gameBoard.length; i++) {
            newTilePosition = random.nextInt(19) + 1;

            tempTile = gameBoard[newTilePosition];
            gameBoard[newTilePosition] = gameBoard[i];
            gameBoard[newTilePosition].pos = newTilePosition;
            gameBoard[i] = tempTile;
            gameBoard[i].pos = i;
        }
    }


    private void printTiles(int begin,int end,int offset,boolean reverse){
        System.out.println(String.format("|%s|%s|%s|",
                " ".repeat(offset),
                String.join("||",getRoll(begin,end,reverse)),
                " ".repeat(offset)
        ));
    }

    private String[] getRoll(int begin,int end,boolean reverse){
        String[] tiles = new  String[end-begin+1];
        for(int i=0;i<tiles.length;i++) tiles[i]=(reverse)? gameBoard[end-i].tileSymbol:gameBoard[begin+i].tileSymbol;
        return tiles;
    }

    private void printNumbers(int begin,int end,int offset,boolean reverse){
        System.out.println(String.format("|%s|%s|%s|",
                " ".repeat(offset),
                String.join("||",createArray(begin,end,reverse)),
                " ".repeat(offset)
        ));
    }

    private String[] createArray(int firstPos,int lastPos,boolean reverse){
        String[] newArray = new String[lastPos-firstPos+1];
        for(int i=0;i<newArray.length;i++) newArray[i]=(reverse)? convertNum(lastPos-i) : convertNum(firstPos+i);

        return  newArray;
    }

    private String convertNum(int num){
        return (num>9)? String.valueOf(num):(num)+" ";
    }

    /**
     * Prints the game board to the console
     */
    public void printBoard() {
        int offset = 13;
        printLine(60);
        printUpperBoard(offset);
        printMiddleBoard();
        printLowerBoard(offset);
        printLine(60);
    }

    /**
     * Prints the upper part of the game board - tile positions [10;17]
     */
    private void printUpperBoard(int offset){
        printNumbers(10,17,offset,false);
        printTiles(10,17,offset,false);
    }

    /**
     * Prints the middle part of the game board - tile positions [8;9] and [18;19]
     */
    private void printMiddleBoard(){

        System.out.print(String.format("|         |%d ||%s|                        ",9, gameBoard[9].tileSymbol));
        System.out.println(String.format("|%s||%d|         |", gameBoard[18].tileSymbol,18));

        System.out.print(String.format("|         |%d ||%s|                        ",8, gameBoard[8].tileSymbol));
        System.out.println(String.format("|%s||%d|         |", gameBoard[19].tileSymbol,19));


    }

    /**
     * Prints the lower part of the game board - tile positions [0;7]
     */
    private void printLowerBoard(int offset){
        printTiles(0,7,offset,true);
        printNumbers(0,7,offset,true);
    }



    /**
     * Prints the player information needed for the current player to take a decision.
     * The player`s ID, position, current парички balance
     */
    public void printPlayerInfo(){
        printLine(60);
        String playerType;
        for (Player player :players) {
            playerType=(player.isBot)?"Bot":"Player";

            String playerInfo = String.format("%s %d is located at position %d with cash balance %d"
                    ,playerType,
                    player.id,
                    player.pos
                    ,player.cash);

            int totalSpaces =58-playerInfo.length();
                String rightSpaces = " ".repeat(totalSpaces/2+totalSpaces%2);
                String leftSpaces = " ".repeat(totalSpaces/2);

            System.out.println(String.format("|%s%s%s|",leftSpaces,playerInfo,rightSpaces));
        }
        printLine(60);
    }

    /**
     * Prints a line of character a certain amount of times
     * @param repetitions the number of character repetitions
     */
    public void printLine(int repetitions){
        System.out.println("=".repeat(repetitions));
    }
}
