

public class Tribals extends Character
{
    String name;
    String tribe;

    public Tribals(Subject[] subjects,String name, Location currentLocation, Integer health, Integer inventorySize) {
            super(subjects,name,currentLocation,health, inventorySize);
	}

    public void inspect()
    {
        UI.print( "A " + this.getClass().getSimpleName() + " whose tribe is " + tribe);
    }
    
}