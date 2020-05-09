import java.util.Random;

public class Game {

    Tile[] gameBoard = new Tile[20];
    Player[] players = new Player[5];

    public Game() {
        initBoard();
        initPlayers();
    }


    /**
     * Sets the initial tile positions
     */
    private void initBoard() {
        //Специално за Михаил Петров
        gameBoard[0] = new Tile(0, 0);
        gameBoard[1] = new Tile(1, 1);
        gameBoard[2] = new Tile(1, 2);
        gameBoard[3] = new Tile(1, 3);
        gameBoard[4] = new Tile(2, 4);
        gameBoard[5] = new Tile(2, 5);
        gameBoard[6] = new Tile(2, 6);
        gameBoard[7] = new Tile(3, 7);
        gameBoard[8] = new Tile(3, 8);
        gameBoard[9] = new Tile(3, 9);
        gameBoard[10] = new Tile(4, 10);
        gameBoard[11] = new Tile(4, 11);
        gameBoard[12] = new Tile(4, 12);
        gameBoard[13] = new Tile(5, 13);
        gameBoard[14] = new Tile(5, 14);
        gameBoard[15] = new Tile(5, 15);
        gameBoard[16] = new Tile(5, 16);
        gameBoard[17] = new Tile(5, 17);
        gameBoard[18] = new Tile(5, 18);
        gameBoard[19] = new Tile(5, 19);
    }

    private void initPlayers() {
        players[0] = new Player(1, false, 0);
        players[1] = new Player(2, true, 0);
        players[2] = new Player(3, true, 0);
        players[3] = new Player(4, true, 0);
        players[4] = new Player(5, true, 0);
    }

    public void makeCycle() {
        for (int i=0;i<players.length;i++){
            players[i].makeTurn();

            gameBoard[players[i].pos].setTile(players[i].id);
        }
    }

    public void sortPlayers() {
        int n = players.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (players[j].cash < players[j + 1].cash) {
                    Player temp = players[j];
                    players[j] = players[j + 1];
                    players[j + 1] = temp;
                }

    }

    public void printPlayers() {
        for (Player player : players) {
            System.out.print("id " + player.id);
            System.out.print("pos " + player.pos);
        }
        System.out.println();
    }

    /**
     * Shuffles the positions of the tiles in the game board.
     * The Start tile stays in its place, unaffected by the shuffle
     */
    public void shuffleBoard() {
        Tile temp;
        int newPos;
        Random random = new Random();
        for (int i = 1; i < gameBoard.length; i++) {
            newPos = random.nextInt(19) + 1;

            temp = gameBoard[newPos];
            gameBoard[newPos] = gameBoard[i];
            gameBoard[newPos].pos=newPos;
            gameBoard[i] = temp;
            gameBoard[i].pos=i;
        }
    }

    /**
     * Prints the board to the console
     */
    public void printBoard() {

        for (Tile tile : gameBoard) System.out.print("|" + tile.tileSymbol + "|");
        System.out.println();
        for (Tile tile : gameBoard) System.out.print("|" + tile.pos + "|");
        System.out.println();

        /*int l=gameBoard.length;
        for(int i=l/2;i<l;i++)
            System.out.print("|"+ gameBoard[i].tileSymbol+"|");
        System.out.println();
        for (int i=l/2-1;i>=0;i--)
            System.out.print("|"+ gameBoard[i].tileSymbol+"|");
        System.out.println();*/
    }
/*    public static void printLine(int len,boolean isLower){
        if(isLower) for(int i=0;i<len;i++) System.out.print("_");
        else  for(int i=0;i<len;i++) System.out.print("~");
        System.out.println();
    }*/

}
