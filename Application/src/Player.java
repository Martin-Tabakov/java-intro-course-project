public class Player {

    int id;
    boolean isBot;
    int pos;
    int cash=1000;
    public Player(int id,boolean isBot,int pos) {
        this.id = id;
        this.isBot = isBot;
        this.pos = pos;
    }
    public void makeTurn(){
        int posToMove = Application.throwDice(1,2);
        pos+=posToMove;
    }

}
