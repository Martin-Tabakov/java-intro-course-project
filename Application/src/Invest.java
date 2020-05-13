public class Invest {
    int companyId=1;
    String companyName="Company1";
    int minimumInvestment = 500;
    double returnRatio =0.2;
    int minRiskRatio = -5;
    int maxRiskRatio = 100;

    /**
     * Initiates the specific actions for the {@code Investment} tile
     * @param player The current player placed on the tile
     */
    public void invest(Player player){
        checkEvilPlan(player);
        System.out.println("Време е за инвестиция от " +player.getFullPlayerType());

    }

    private void assignCompany(Player player){
        player.companyId=0;
    }

    /**
     * Activates an Evil plan if the player has it and is active
     * @param player The current player placed on the tile
     */
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==2) {
            System.out.println("Зъл план активиран от "+ player.getFullPlayerType());
            player.cash += 100;
        }
    }
}
