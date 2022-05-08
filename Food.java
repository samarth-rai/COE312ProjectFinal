public class Food extends Consumables{

    Integer energy;
    
    public Food(Player player,String name, String description, Integer energy, Location spawnLocation) {
        super(name, description,spawnLocation);
        this.energy = energy;
        registerObserver(player);
    }

    @Override
    public void consume()
    {
        Message m = new Message(this,"food",energy.toString());
        publishMessage(m);
        currentLocation.currentlyPlacedObjects.remove(this);
    }

    

    
}
