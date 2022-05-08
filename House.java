public class House extends Craftable
{
    
    void Completed() {
        GameMaster.player.currentLocation.currentlyPlacedObjects.add(new Objects("House", "A place to sleep at night and stay safe from monsters."));
        UI.print("House is built!");
    }

    @Override
    void placeMaterials() {
        for(int i=0; i<GameMaster.player.inventory.size();i++){
            if(GameMaster.player.inventory.get(i).name.toLowerCase().equals("wood")){
                Objects o1 = GameMaster.player.inventory.get(i);
               for(int j=0; j<GameMaster.player.inventorySize;j++){
                 if(GameMaster.player.inventory.get(j).name.toLowerCase().equals("leaves")){
                     Objects o2 = GameMaster.player.inventory.get(j);
                     GameMaster.player.inventory.remove(o1);
                     GameMaster.player.inventory.remove(o2);
                     UI.print("Wood is placed");
                     UI.print("Leaves are placed");
                     return;
                 }
               }
            }
        }
        UI.print("You do not have the required items to make this!");
       
        
    }

    @Override
    void performAction() {
        UI.print("Tying the wood together with leaves.");
    }

    @Override
    public void update(Message m) {
       
        
    }
}