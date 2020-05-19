import java.util.ArrayList;
import java.util.Scanner;

public class Invest {
    int cashInvested = 0;

    /**
     * Initiates the specific actions for the {@code Investment} tile
     *
     * @param player The current player placed on the tile
     */
    public void invest(Player player) {
        cashInvested = 0;
        checkEvilPlan(player);
        if (!player.isBot) playerInvestHandler(player);
        else investBot(player);

    }

    /**
     * Makes the bot decide if he wants to invest,in which companies and how much
     *
     * @param player Current Bot
     */
    private void investBot(Player player) {
        if (Application.RNG(0, 1) == 1) {
            ArrayList<ArrayList<String>> companies = new ArrayList<>();
            setCompanies(companies);
            for (ArrayList<String> company : companies) {
                int minCashReq = Integer.parseInt(company.get(1));
                if (player.cash >= minCashReq && Application.RNG(1, 10) > 4) {

                    cashInvested = Application.RNG(minCashReq, player.cash);
                    player.cash -= cashInvested;
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

    /**
     * Handles the Human player Investment logic
     *
     * @param player Current Human player
     */
    private void playerInvestHandler(Player player) {
        ArrayList<ArrayList<String>> companies = new ArrayList<>();
        setCompanies(companies);
        while (true) {
            int id = makeDecision(companies, player);
            if (id == 0) return;
        }

    }

    /**
     * Prompts the Player to insert a number within a range until the number is correct
     *
     * @return The correctly inserted number
     */
    private int makeDecision(ArrayList<ArrayList<String>> companies, Player player) {
        int lowerBound = 0;
        int upperBound = 3;

        Scanner scanner = new Scanner(System.in);
        boolean correctInput = true;
        int option = 0;
        while (correctInput) {
            displayMenu(companies);

            option = scanner.nextInt();
            correctInput = Application.isNumberInRange(option, lowerBound, upperBound);
            if (option == 0) return 0;
            if (correctInput) makeInvestment(companies.get(option - 1), player);
        }
        return option;
    }

    /**
     * Prompts the player to insert how much to invest in the selected company.
     * Afterwards The company is added to his list of investments
     *
     * @param company The company of interest
     * @param player  The current Human player
     */
    private void makeInvestment(ArrayList<String> company, Player player) {
        while (true) {
            System.out.println("Колко парички ще инвестираш?");
            Scanner scanner = new Scanner(System.in);
            cashInvested = scanner.nextInt();
            if (player.cash < Integer.parseInt(company.get(1))) return;
            if (cashInvested >= Integer.parseInt(company.get(1)) && cashInvested <= player.cash) {
                player.cash -= cashInvested;
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

    /**
     * Sets the three random companies for the players to invest into
     *
     * @param companies Empty list of companies that will be filled
     */
    private void setCompanies(ArrayList<ArrayList<String>> companies) {
        for (int i = 0; i < 3; i++) {
            int companyID = Application.RNG(1, 6);
            companies.add(getOptions(companyID));
        }
    }

    /**
     * Displays the menu containing the information a player will need to make an investment
     *
     * @param companies List of randomly selected companies
     */
    private void displayMenu(ArrayList<ArrayList<String>> companies) {
        System.out.println("(0) Не ми се инвестира.");
        for (int i = 0; i < companies.size(); i++) {
            String companyName = companies.get(i).get(0);
            int minInvestment = Integer.parseInt(companies.get(i).get(1));
            float returnMultiplier = Float.parseFloat(companies.get(i).get(2));
            System.out.println(String.format("(%d) %s | min: %d | risk/reward : %f", i + 1, companyName, minInvestment, returnMultiplier));
        }
    }

    /**
     * Reads the data of a company from a Data file
     *
     * @param ID Id of the wanted company
     * @return List of the selected company data
     */
    private ArrayList<String> getOptions(int ID) {
        return Reader.read("Invest", ID, 5);
    }


    /**
     * Activates an Evil plan if the player has it and is active
     *
     * @param player The current player placed on the tile
     */
    private void checkEvilPlan(Player player) {
        if (player.isPlanActive && player.planId == 2) {
            System.out.println("Зъл план активиран от " + player.getFullPlayerType());
            player.cash += 100;
            String desc = Reader.read("Steal", 2, 3).get(2);
            System.out.println(desc);
        }
    }
}
