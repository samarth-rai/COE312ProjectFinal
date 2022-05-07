

public class Tribals extends Character
{
    String name;
    String tribe;

    public Tribals(Subject[] subjects,String name, Location currentLocation, Integer health, Integer inventorySize, String tribeName) {
            super(subjects,name,currentLocation,health, inventorySize);
            this.tribe = tribeName;
	}

    public Tribals(String name, Location spawnLocation, int health, int inventorySize, String description, String tribe) {
        super(name, spawnLocation, health, inventorySize, description);
        this.tribe = tribe;
        spawnLocation.currentlyPlacedCharacters.add(this);
    }

    public void inspect()
    {
        UI.print( "A " + this.getClass().getSimpleName() + " whose tribe is " + tribe);
    }
    
}