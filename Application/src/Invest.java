import java.util.ArrayList;
import java.util.Scanner;

public class Invest {
    int cashInvested=0;

    /**
     * Initiates the specific actions for the {@code Investment} tile
     * @param player The current player placed on the tile
     */
    public void invest(Player player){
        cashInvested=0;
        checkEvilPlan(player);
        if(!player.isBot) investMenu(player);
        else investBot(player);

    }

    private void investBot(Player player){
        if(Application.throwDice(0,1)==1){
            ArrayList<ArrayList<String>> companies=new ArrayList<>();
            setCompanies(companies);
            for (ArrayList<String> company : companies) {
                if (player.cash >= Integer.parseInt(company.get(1)) && Application.throwDice(1, 10) > 4) {
                    cashInvested = Application.throwDice(Integer.parseInt(company.get(1)), player.cash);
                    player.cash-=cashInvested;
                    ArrayList<String> tempComp = new ArrayList<>();
                    tempComp.add(Integer.toString(cashInvested));
                    tempComp.add(company.get(2));
                    tempComp.add(company.get(3));
                    tempComp.add(company.get(4));
                    player.investedCompanies.add(tempComp);
                    cashInvested = 0;
                }
            }
        }

    }

    private void investMenu(Player player){
        ArrayList<ArrayList<String>> companies=new ArrayList<>();
            setCompanies(companies);
        while (true){
            int id=makeDecision(companies,player);
            if(id==0) return;
        }

    }

    /**
     * Prompts the Player to insert a number within a range until the number is correct
     * @return The correctly inserted number
     */
    private int makeDecision(ArrayList<ArrayList<String>> companies,Player player){
        int lowerBound=0;
        int upperBound=3;

        Scanner scanner = new Scanner(System.in);
        boolean correctInput = true;
        int option=0;
        while (correctInput){
            displayMenu(companies);

            option=scanner.nextInt();
            correctInput= Application.isNumberInRange(option,lowerBound,upperBound);
            if(option==0) return 0;
            if(correctInput) makeInvestment(companies.get(option-1),player);
        }
        return option;
    }

    private void makeInvestment(ArrayList<String> company, Player player){
        while (true) {
            System.out.println("Колко парички ще инвестираш?");
            Scanner scanner = new Scanner(System.in);
            cashInvested = scanner.nextInt();
            if(player.cash<Integer.parseInt(company.get(1))) return;
            if (cashInvested >=Integer.parseInt(company.get(1)) && cashInvested<= player.cash){
                player.cash-=cashInvested;
                ArrayList<String> tempComp = new ArrayList<>();
                tempComp.add(Integer.toString(cashInvested));
                tempComp.add(company.get(2));
                tempComp.add(company.get(3));
                tempComp.add(company.get(4));
                player.investedCompanies.add(tempComp);
                return;
            }
        }
    }

    private void setCompanies(ArrayList<ArrayList<String>> companies){
        for(int i=0;i<3;i++){
            int companyID = Application.throwDice(1,6);
            companies.add(getOptions(companyID));
        }
    }

    private void displayMenu(ArrayList<ArrayList<String>> companies){
        System.out.println("(0) Не ми се инвестира.");
        for(int i=0;i<companies.size();i++){
            String companyName = companies.get(i).get(0);
            int minInvestment = Integer.parseInt(companies.get(i).get(1));
            float returnMultiplier = Float.parseFloat(companies.get(i).get(2));
            System.out.println(String.format("(%d) %s | min: %d | risk/reward : %f",i+1,companyName,minInvestment,returnMultiplier));
        }
    }

    private ArrayList<String> getOptions(int ID){
        return Reader.read("Invest",ID,5);
    }



    /**
     * Activates an Evil plan if the player has it and is active
     * @param player The current player placed on the tile
     */
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==2) {
            System.out.println("Зъл план активиран от "+ player.getFullPlayerType());
            player.cash += 100;
            String desc= Reader.read("Steal",2,3).get(2);
            System.out.println(desc);
        }
    }
}
