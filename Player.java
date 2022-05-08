import java.rmi.NoSuchObjectException;
import java.util.NoSuchElementException;

public class Player extends Character implements Runnable, Movable {
    public Player(Subject[] subjects) {
        super(subjects);
        
    }

    private static Player instance;
    private Player(Subject[] subjects,String name, Location currentLocation, Integer health, Integer inventorySize) {
        super(subjects,name,currentLocation,health, inventorySize);
        Thread th = new Thread(this);
        th.start();
        
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
            if(Objectives.foundWatch==true){
                vt = new ViewTimeWatch();
            }
            //else vt = new ViewTimeNoWatch();
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

   public synchronized void checkHealth()
   {
       
    
       if (this.health <= 30)
       {
           UI.print("Your health is reducing, please increase it by eating or sleeping");
       }
       if(this.health <= 0)
       {
           UI.print("You died!\n GAME OVER");
           System.exit(0);
        
       }
       if(this.health>fullHealth)
       {
           this.health=fullHealth;//Normalizes health;
           
       }
   }

   public void checkInventory()
   {
        UI.print(this.name + "'s Inventory:");
    UI.printArrayList(inventory);
   }

   public void viewHealth()
   {
       UI.print(this.name + "'s health is: " + this.health + "/" + this.fullHealth);
   }

    public void sleep()
    {   
        if(Objectives.BuiltHouse==true){
            Message m = new Message(this,"sleep","");
            publishMessage(m);
        }
        else UI.print("You need to build a house to sleep!");
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
        UI.printNormal("look"+
        "\ninspect <object>"+
        "\nacquire <object>"+
        "\ntake <object>"+
        "\nbattle <character>"+
        "\ncut <object>"+
        "\neat <object>"+
        "\ntravel <location>"+
        "\ninventory"+
        "\nhealth"+
        "\nmap lol");

    }

    public void interact(String object){
        for(int i=0;i<currentLocation.currentlyPlacedObjects.size();i++)
        {
            if(currentLocation.currentlyPlacedObjects.get(i).name.equalsIgnoreCase(object))
            {
               //UI.print("before tcp client");
               //TCP_Client t = new TCP_Client("192.168.0.195",52855,0);
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
                    this.checkHealth();
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
                    UI.print(o.name + " was added to your inventory");

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

   public void battle(String character)
   {
       try{
           Character theLoser = currentLocation.getCharacter(character);
           this.registerObserver(theLoser);
           theLoser.attack(this);
            return;
    
        }
       catch(NoSuchElementException e)
       {
            
       }
       try{
        Animal theLoser = currentLocation.getAnimal(character);
        this.registerObserver(theLoser);
        theLoser.attack(this);
 
        }
        catch(NoSuchElementException e)
        {
             UI.print(character + " is not available for battle!");
        }
   }

   public void takeItem(String s) //syntax: take watch from vikram kumar
   {
       String[] commands = s.split(" ");
       String ObjName = commands[1];
       String CharName = commands[3];
       for(int i=4; i<commands.length; i++){
           CharName += " " +commands[i] ;
       }
       
       ObjName=ObjName.toLowerCase();
       CharName=CharName.toLowerCase();
       for(int i=0; i<currentLocation.currentlyPlacedCharacters.size(); i++){
            if(CharName.equals(currentLocation.currentlyPlacedCharacters.get(i).name.toLowerCase())){
               
                Character x=currentLocation.currentlyPlacedCharacters.get(i);
                if(x.health==0){
                    for(int j=0; j<inventorySize; j++){
                       
                        if(ObjName.equals(x.inventory.get(j).name.toLowerCase())){
                            inventory.add(x.inventory.get(j)); // add to our inventory
                            UI.print(x.inventory.get(j).name + " was added to your inventory.");
                            x.inventory.remove(x.inventory.get(j)); //remove from their inventory
                        }
                        
                    }
                }
                else UI.print("You need to fight" + x.name + "to take from them");
            }
            
        }
       
   }
   
   public void buildHouse(){
       for(int i=0; i<inventory.size();i++){
           if(inventory.get(i).name.toLowerCase().equals("wood")){
               Objects o1 = inventory.get(i);
              for(int j=0; j<inventorySize;j++){
                if(inventory.get(j).name.toLowerCase().equals("leaves")){
                    Objects o2 = inventory.get(j);
                    House house = new House();
                    house.craftItem();
                    inventory.remove(o1);
                    inventory.remove(o2);
                    Message m = new Message(this, "Objective", "BuiltHouse");
                    publishMessage(m);
                    return;
                }
              }
           }
       }
       UI.print("You do not have the required items to make this!");
   }

   public void makeFire() {
    for(int i=0; i<inventory.size();i++){
        if(inventory.get(i).name.toLowerCase().equals("wood")){
            Objects o1 = inventory.get(i);
           for(int j=0; j<inventory.size();j++){
             if(inventory.get(j).name.toLowerCase().equals("leaves")){
                Objects o2 = inventory.get(j);
                 for(int k =0; k<inventorySize;k++){
                    if(inventory.get(k).name.toLowerCase().equals("stones")){
                        Objects o3 = inventory.get(k);
                        inventory.remove(o1);
                        inventory.remove(o2);
                        inventory.remove(o3);
                        Fire fire = new Fire();
                        fire.craftItem();
                        Message m = new Message(this, "Objective", "MadeFire");
                        publishMessage(m);
                        return;
                    }
                 }
             }
           }
        }
    }
    UI.print("You do not have the required items to make this!");
}

   
   public void showmap(Location currentLocation){
    if(currentLocation.name.equalsIgnoreCase("Island North"))
    {
        UI.printNormal("\n                                                                                "+
        "\n                                                                                "+
        "\n                                                    ███████                     "+
        "\n    ████            ███                        █████       ██                   "+
        "\n    █  █ █       ████ ██                    ███             ██                  "+
        "\n    █  █████  ███       ██               ████                ██                 "+
        "\n    █      ███           ████████████████                     █                 "+
        "\n    █                                                          █                "+
        "\n    ██                     Island North                        █                "+
        "\n     █                                                         █                "+
        "\n     ██                      (X)  <- You are here               ██               "+
        "\n      █                                                         ███             "+
        "\n      ██                                                          ████          "+
        "\n       ██                                                            ██         "+
        "\n        █                                                             ██        "+
        "\n        █                                                              ██       "+
        "\n         █                                                              █       "+
        "\n         █ Island East                                    Island West    █      "+
        "\n         █                                                               █      "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n        █                                                                 ██    "+
        "\n       ██                                                                 █     "+
        "\n       █                                                                ██      "+
        "\n      ██                                                               ██       "+
        "\n      █                                                              ███        "+
        "\n      █                        Island South                        ███          "+
        "\n      ██                                                         ███            "+
        "\n       ██                             ██████                 ████               "+
        "\n         ██                   █████████    ██████████████████                   "+
        "\n           █████         ██████                                                 "+
        "\n                █████████                                                       "+
        "\n                                                                                ");
    }
    else if(currentLocation.name.equalsIgnoreCase("Island East"))
    {
        UI.printNormal("\n                                                                                "+
        "\n                                                                                "+
        "\n                                                    ███████                     "+
        "\n    ████            ███                        █████       ██                   "+
        "\n    █  █ █       ████ ██                    ███             ██                  "+
        "\n    █  █████  ███       ██               ████                ██                 "+
        "\n    █      ███           ████████████████                     █                 "+
        "\n    █                                                          █                "+
        "\n    ██                     Island North                        █                "+
        "\n     █                                                         █                "+
        "\n     ██                                                         ██               "+
        "\n      █                                                         ███             "+
        "\n      ██                                                          ████          "+
        "\n       ██                                                            ██         "+
        "\n        █                                                             ██        "+
        "\n        █                                                              ██       "+
        "\n         █                                                              █       "+
        "\n         █ Island East                                    Island West    █      "+
        "\n         █  (X)  <- You are here                                          █      "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n        █                                                                 ██    "+
        "\n       ██                                                                 █     "+
        "\n       █                                                                ██      "+
        "\n      ██                                                               ██       "+
        "\n      █                                                              ███        "+
        "\n      █                        Island South                        ███          "+
        "\n      ██                                                         ███            "+
        "\n       ██                             ██████                 ████               "+
        "\n         ██                   █████████    ██████████████████                   "+
        "\n           █████         ██████                                                 "+
        "\n                █████████                                                       "+
        "\n                                                                                ");
    }

    else if(currentLocation.name.equalsIgnoreCase("Island West"))
    {
        UI.printNormal("\n                                                                                "+
        "\n                                                                                "+
        "\n                                                    ███████                     "+
        "\n    ████            ███                        █████       ██                   "+
        "\n    █  █ █       ████ ██                    ███             ██                  "+
        "\n    █  █████  ███       ██               ████                ██                 "+
        "\n    █      ███           ████████████████                     █                 "+
        "\n    █                                                          █                "+
        "\n    ██                     Island North                        █                "+
        "\n     █                                                         █                "+
        "\n     ██                                                         ██               "+
        "\n      █                                                         ███             "+
        "\n      ██                                                          ████          "+
        "\n       ██                                                            ██         "+
        "\n        █                                                             ██        "+
        "\n        █                                                              ██       "+
        "\n         █                                                              █       "+
        "\n         █ Island East                                    Island West    █      "+
        "\n         █                                      You are here ->  (X)      █      "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n        █                                                                 ██    "+
        "\n       ██                                                                 █     "+
        "\n       █                                                                ██      "+
        "\n      ██                                                               ██       "+
        "\n      █                                                              ███        "+
        "\n      █                        Island South                        ███          "+
        "\n      ██                                                         ███            "+
        "\n       ██                             ██████                 ████               "+
        "\n         ██                   █████████    ██████████████████                   "+
        "\n           █████         ██████                                                 "+
        "\n                █████████                                                       "+
        "\n                                                                                ");
    }

    else if(currentLocation.name.equalsIgnoreCase("Island South"))
    {
        UI.printNormal("\n                                                                                "+
        "\n                                                                                "+
        "\n                                                    ███████                     "+
        "\n    ████            ███                        █████       ██                   "+
        "\n    █  █ █       ████ ██                    ███             ██                  "+
        "\n    █  █████  ███       ██               ████                ██                 "+
        "\n    █      ███           ████████████████                     █                 "+
        "\n    █                                                          █                "+
        "\n    ██                     Island North                        █                "+
        "\n     █                                                         █                "+
        "\n     ██                                                         ██               "+
        "\n      █                                                         ███             "+
        "\n      ██                                                          ████          "+
        "\n       ██                                                            ██         "+
        "\n        █                                                             ██        "+
        "\n        █                                                              ██       "+
        "\n         █                                                              █       "+
        "\n         █ Island East                                    Island West    █      "+
        "\n         █                                                               █      "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n         █                                                                █     "+
        "\n        █                                                                 ██    "+
        "\n       ██                                                                 █     "+
        "\n       █                                                                ██      "+
        "\n      ██                                                               ██       "+
        "\n      █                                                              ███        "+
        "\n      █                  You are here ->  (X)                       ███        "+
        "\n      █                        Island South                        ███          "+
        "\n      ██                                                         ███            "+
        "\n       ██                             ██████                 ████               "+
        "\n         ██                   █████████    ██████████████████                   "+
        "\n           █████         ██████                                                 "+
        "\n                █████████                                                       "+
        "\n                                                                                ");
    }
   
}

    @Override
    public void run() 
    {
        while(true)
        {
            if(Objectives.foundLVBag==true){
                inventorySize=20; //inventory size increases when we find the lv bag
            }
            this.checkHealth();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    
  






}
