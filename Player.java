import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

public class Player extends Character implements Runnable, Movable {
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

   public void checkHealth()
   {
       if (health <= 30)
       {
           UI.print("Your health is reducing, please increase it by eating or sleeping");
       }
       if(health == 0)
       {
           UI.print("You died!\n GAME OVER");
           System.exit(0);
        
       }
   }

   public void checkInventory()
   {
        UI.print(this.name + "'s Inventory:");
    UI.printArrayList(inventory);
   }

    public void sleep()
    {   
        Message m = new Message(this,"sleep","");
        publishMessage(m);
        //Changes the state of clock to the next state;
    }

    public void travelTo(String destination)
    {
        this.travelTo(destination,false);
    }

    
    public void travelTo(String destination, Boolean SILENT) {
       
      try{ Location des = GameMaster.findLocation(destination);
        Message m = new Message(this,"travel",des.name);
       if(!SILENT) UI.print(this.name + " has arrived at " + des.name);
       publishMessage(m);}
       
      catch(NoSuchObjectException e){
          UI.print(destination + " does not exist!");
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
        catch(IndexOutOfBoundsException f)
        {

        }

        try{
            currentLocation.getObjects(s).inspect();
        }
        catch(NoSuchElementException e)
        {
            flag++;
        }
        catch(IndexOutOfBoundsException f)
        {

        }

        try{
            currentLocation.getCharacter(s).inspect();
        }
        catch(NoSuchElementException e)
        {
            flag++;
        }
        catch(IndexOutOfBoundsException f)
        {

        }
        if(flag>=3)
        {
            UI.print(s + " is not available here.");
        }
        
    }

    public void help(){
        UI.print("help func runnning");

    }

    public void interact(String object){
        for(int i=0;i<currentLocation.currentlyPlacedObjects.size();i++)
        {
            if(currentLocation.currentlyPlacedObjects.get(i).name.equalsIgnoreCase(object))
            {
               Consumables f = (Consumables) currentLocation.currentlyPlacedObjects.get(i);
               f.consume();
               return;
            }
        }
    }
    public void eat(String object){
        for(int i=0;i<currentLocation.currentlyPlacedObjects.size();i++)
        {
            if(currentLocation.currentlyPlacedObjects.get(i).name.equalsIgnoreCase(object))
            {
               if(currentLocation.currentlyPlacedObjects.get(i).getClass().getSimpleName().equalsIgnoreCase("Food"))
                {
                    Consumables f = (Consumables) currentLocation.currentlyPlacedObjects.get(i);
                    f.consume();
                }
                else
                {
                    UI.print("This item is not food!");
                }
               return;
            }
        }
    }

   public void acquire(String object) {

    for(int i=0;i<currentLocation.currentlyPlacedObjects.size();i++)
        {
            if(currentLocation.currentlyPlacedObjects.get(i).name.equalsIgnoreCase(object))
            {
                Objects o = currentLocation.currentlyPlacedObjects.get(i);
                if(o.canCarry==true)
                {
                    inventory.add(o);
                    currentLocation.currentlyPlacedObjects.remove(o);
                }
                else
                {
                    UI.print("You cannot carry this item!");
                }
                return;
            }
        }

        UI.print(object + " is not available here!");
      
   }

   public void takeItem(String s)
   {
       String[] commands = s.split(" ");
        

   }

    @Override
    public void run() 
    {
        checkHealth();

    }

  






}
