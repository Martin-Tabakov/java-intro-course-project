import java.util.Random;
import java.util.Scanner;

public class Game {
    int gameCycle = 0;
    Tile[] gameBoard = new Tile[20];
    Player[] players;
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor for the Game
     */
    public Game() {
        initBoard();
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
     * Selects the number of bots to participate in the game and initialises the player array
     */
    public void setBotQuantity(){
        System.out.print("Избери с колко Бота ще играеш: ");
        int quantity = scanner.nextInt();
        initPlayers(quantity);
    }

    /**
     * Initialises the partaker`s positions and IDs
     */
    private void initPlayers(int quantity) {
        players = new Player[quantity+1];
        players[0] = new Player(1, false);
        for(int i=1;i<players.length;i++) players[i] = new Player(i+1, true);
    }

    /**
     * Makes a game cycle
     * Cycle - One full rotation of all players through the board to the starting position from where they began
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
        clearStealTiles();
    }

    /**
     * Resets all {@code Steal} tiles to be reused
     */
    private void clearStealTiles(){
        for (Tile tile : gameBoard) if (tile.id == 2) tile.steal.clearTile();
    }

    /**
     * Calculates the парички for players after completing a game cycle
     */
    private void calculateBalance(){
        for (Player player : players) player.calculateCash();
    }
    /**
     * Assigns every player an evil plan
     */
    private void assignEvilPlans(){
        for (Player player : players) player.assignEvilPlan();
    }

    /**
     * Cycles through the list of players prompting them to take a turn.<br><hr>
     * If the current Player`s cycle is equal to the game cycle a move will be made and the tile where the player lands will be activated<br>
     * Otherwise his turn will be skipped as he is waiting on the {@code Start} position for the rest of the players to complete their cycle<br>
     * @return {@code true} if all players made a cycle, otherwise {@code false}
     */
    public boolean playerCycle() {
        int playersMadeCycle =0;

        for (Player currentPlayer : players) {
            if (currentPlayer.cycle == gameCycle) {
                currentPlayer.makeTurn();
                if (currentPlayer.pos > 19) {
                    currentPlayer.pos = 0;
                    currentPlayer.cycle++;
                    System.out.println(currentPlayer.getPlayerType()+currentPlayer.id+" се премести на "+currentPlayer.pos);
                    continue;
                }
                System.out.println(currentPlayer.getPlayerType() +currentPlayer.id+" се премести на "+currentPlayer.pos);
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


    /**
     * Prints a row of tiles
     * @param begin The first tile`s position
     * @param end The last tile`s position
     * @param offset The number of whitespaces needed to center the board
     * @param reverse If {@code true} the tiles will be in descending order If {@code false} the tiles will be printed in ascending order
     */
    private void printTiles(int begin,int end,int offset,boolean reverse){
        System.out.println(String.format("|%s|%s|%s|",
                " ".repeat(offset),
                String.join("||",getRoll(begin,end,reverse)),
                " ".repeat(offset)
        ));
    }

    /**
     * Selects a certain amount of tiles and places them into a new Array
     * @param begin The first tile`s position
     * @param end The last tile`s position
     * @param reverse If {@code true} the tiles will be in descending order If {@code false} the tiles will be printed in ascending order
     * @return Array with tile symbols
     */
    private String[] getRoll(int begin,int end,boolean reverse){
        String[] tiles = new  String[end-begin+1];
        for(int i=0;i<tiles.length;i++) tiles[i]=(reverse)? gameBoard[end-i].tileSymbol:gameBoard[begin+i].tileSymbol;
        return tiles;
    }

    /**
     * Prints the positions of certain tiles in a roll. Works ike {@code printTiles()}
     */
    private void printNumbers(int begin,int end,int offset,boolean reverse){
        System.out.println(String.format("|%s|%s|%s|",
                " ".repeat(offset),
                String.join("||",createArray(begin,end,reverse)),
                " ".repeat(offset)
        ));
    }

    /**
     * Does the same thing as the method {@code getRoll()} but for the tile positions
     */
    private String[] createArray(int firstPos,int lastPos,boolean reverse){
        String[] newArray = new String[lastPos-firstPos+1];
        for(int i=0;i<newArray.length;i++) newArray[i]=(reverse)? convertNum(lastPos-i) : convertNum(firstPos+i);

        return  newArray;
    }

    /**
     * Converts a number into a string value and adds a white space after it if it is less than 10
     * @param num The number to be converted
     * @return The number converted into a String value
     */
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
     * Yes it is discussing o look at
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
     * Prints all participants`  ID, position and current парички balance
     */
    public void printPlayerInfo(){
        printLine(60);
        for (Player player :players) {

            String playerInfo = String.format("%s %d се намира на поле %d и има %d парички.",
                    player.getPlayerType(),
                    player.id,
                    player.pos,
                    player.cash);

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
