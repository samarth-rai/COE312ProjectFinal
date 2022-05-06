import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

public class Player extends Character implements Runnable {
    public Player(Subject[] subjects) {
        super(subjects);
        
    }

    private static Player instance;
    private Player(Subject[] subjects,String name, Location currentLocation, Integer health, Integer inventorySize) {
        super(subjects,name,currentLocation,health, inventorySize);
        
    }

    public static Player getInstance(Subject[] subjects,String name, Location currentLocation, Integer health, Integer inventorySize)
    {
        if(instance==null)
        {
            instance = new Player(subjects,name,currentLocation,health, inventorySize);
        }
        return instance;
    }

    ViewTimeBehavior vt = new ViewTimeNoWatch(); //player starts off with viewing time without watch - Strategy Pattern
    public void performViewTime(){
        vt.ViewTime();
    }

    public void setViewTimeBehavior(ViewTimeBehavior fb) { //use this to change the viewtime functionality when the watch is discovered just call setViewTimeBehavior(new ViewTimeWatch())  
        vt = fb;
    }
    public void look(Location l)
    {
        l.lookAround();
        if(l.name=="IslandNorth")
        {
            Message m = new Message(this,"Objective","discoverVikram");
            publishMessage(m);
        }

    }

    public void inspect(String s)
    {
        Integer flag = 0;

        try{
            currentLocation.getAnimal(s).inspect();
        }
        catch(NoSuchElementException e)
        {
            flag++;
        }

        try{
            currentLocation.getObjects(s).inspect();
        }
        catch(NoSuchElementException e)
        {
            flag++;
        }

        try{
            currentLocation.getCharacter(s).inspect();
        }
        catch(NoSuchElementException e)
        {
            flag++;
        }

        if(flag==3)
        {
            UI.print(s + " is not available here.");
        }
        
    }

   public void acquire(Objects o){
       inventory.add(o);
       currentLocation.currentlyPlacedObjects.remove(o);
   }

   public void takeItem(String s)
   {
       String[] commands = s.split(" ");
        

   }

    @Override
    public void run() {
        
        
        
    }

  






}
