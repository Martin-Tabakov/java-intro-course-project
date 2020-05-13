public class Invest {
    int companyId=1;
    String companyName="Company1";
    int minimumInvestment = 500;
    double returnRatio =0.2;
    int minRiskRatio = -5;
    int maxRiskRatio = 100;
    public void invest(Player player){
        checkEvilPlan(player);
        System.out.println("Investment time people! Current investor" +player.getPlayerType()+player.id);

    }
    private void assignCompany(Player player){
        player.companyId=0;
    }
    private void checkEvilPlan(Player player){
        if(player.isPlanActive && player.planId==2) {
            System.out.println("Evil plan activated "+ player.getPlayerType() + player.id);
            player.cash += 100;
        }
    }
}
