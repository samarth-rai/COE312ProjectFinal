import java.util.ArrayList;

//TODO - implement singleton for player class

public abstract class Character extends AbstractObserverSubject implements Movable{

    public String name;
    public Integer inventorySize = 3;
    public Integer health = 100; //default health for all characters
    ArrayList<Objects> inventory = new ArrayList<Objects>();
    Location currentLocation = new Location();

    public Character(Subject[] subjects) {
        super(subjects);
    }

    public Character(Subject[] subjects, String name, Location spawnLocation, Integer health, Integer inventorySize) {
        super(subjects);
        this.currentLocation = spawnLocation;
        this.health = health;
        this.inventorySize = inventorySize;
    }

    public Character(String name) {
        this.name = name;
    }

    public void inspect()
    {
        UI.print(name);
        UI.print("This character has:");
        UI.printArrayList(inventory);

    }

    @Override
    public void travelTo(Location l, Boolean SILENT) {
       Message m = new Message(this,"Travel Request",l.name);
       if(!SILENT) UI.print(this.name + " has arrived at " + l.name);
       publishMessage(m);
    }

    public void update(Message m)
    {
        switch(m.topic.toLowerCase())
        {
            case "travel": 
            {
                currentLocation= (Location) m.origin;
                break;
            }
            
            case "time":{
                //what to do when char gets this message? this happens every min btw
                break;
            }

            case "day":{
                //what to do when char gets this message? this happens every day btw
                break;
            }

            case "attack":{
                this.health -= Integer.parseInt(m.payload);
                UI.print("Your health has decreased by" + m.payload + "points." );
            }
            
        }
    }

   
    

}