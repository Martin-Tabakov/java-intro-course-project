import java.util.Scanner;

public class Player {

    int id;
    int companyId=0;
    int pos;
    int cash=1000;
    int income;
    int profit;
    int expenditure;
    int cycle=1;
    int planId=0;
    int[] trapsID = new int[6];
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

    public String getFullPlayerType(){
        return String.format("%s %d",getPlayerType(),id);
    }
    public int makeDecision(String message){
        boolean correctInput = false;
        int option=0;
        while (!correctInput){

            System.out.println(message);
            System.out.println("0-5");
            option=scanner.nextInt();
            correctInput= Application.isNumberInRange(option,0,5);
        }
        return option;
    }
    public void calculateCash(){
        computeDeBuffs();
        System.out.println("The start of an adventure for" +getPlayerType()+id);
    }

    private void computeDeBuffs(){
        int dice =Application.throwDice(1,10);
        if(trapsID[2]>0 && profit>0 && (dice==2 || dice==8)) profit=0;

        for(int i=1;i< trapsID[1];i++) profit-=profit/10;
    }

    public void assignEvilPlan(){
        planId= Application.throwDice(1,3);
        isPlanActive=false;
    }

}
