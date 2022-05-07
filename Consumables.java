import java.util.ArrayList;

public class Consumables extends Objects {

    ArrayList<Objects> dropObjects = new ArrayList<Objects>();
    Location currentLocation;
    public Consumables(Subject[] subject, String name, String description, Location spawnLocation) {
        super(subject,name,description);
        currentLocation = spawnLocation;
    }
    public Consumables(String name, String description,Location spawnLocation) {
		super(name,description);
        currentLocation = spawnLocation;
	}

    public Consumables(String name, String description,Location spawnLocation, Boolean canCarry) {
		super(name,description,canCarry);
        currentLocation = spawnLocation;
	}

	
	public void consume(){
        if(dropObjects.size()>0){
            UI.print("Your action may have dropped some items!");
        }
        for(int i=0;i<dropObjects.size();i++)
        {
            currentLocation.currentlyPlacedObjects.add(dropObjects.get(i));
        }
        currentLocation.currentlyPlacedObjects.remove(this);
	}
    

}
