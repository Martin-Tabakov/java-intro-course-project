import java.util.Scanner;

public class Player {

    int id;
    int companyId=0;
    int pos;
    int cash=1000;
    int cycle=1;
    int planId=0;
    boolean isPlanActive;
    boolean isBot;
    Scanner scanner = new Scanner(System.in);

    // TODO: 11-May-20 Add human player Interface
    public Player(int id,boolean isBot,int pos) {
        this.id = id;
        this.isBot = isBot;
        this.pos = pos;
    }
    public void makeTurn(){
        int posToMove = Application.throwDice(1,2);
        pos+=posToMove;
    }
    public String getPlayerType(){
        return (isBot)? "Bot":"Player";
    }

    public int makeDecision(String message){
        System.out.println(message);
        System.out.println("0-5");
        return scanner.nextInt();
    }
    public void calculateCash(){

        System.out.println("The start of an adventure for" +getPlayerType()+id);
    }

    public void assignEvilPlan(){
        planId= Application.throwDice(1,3);
        isPlanActive=false;
    }

}
