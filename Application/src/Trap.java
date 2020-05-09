public class Trap {
    int pos;
    boolean isActive=false;
    public void activate(int playerId){
        if(isActive) setOff();
        else placeTrap(playerId);

    }

    private void setOff(){
        System.out.println("Trap Set Off!");
    }

    private void placeTrap(int id){
        System.out.println("PlaceTrap! Player Id"+id);
    }
}
