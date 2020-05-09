public class Party {
    public void startParty(Player player){
        System.out.println("Party started! Host is Player "+player.id);
        partyHard(player);
    }
    private void partyHard(Player player){
        player.cash-=25;
    }
}
