import java.util.Random;

public class Tribals extends Character implements Runnable
{
    String name;
    String tribe;
    String type = "enemy";
    AttackType aType;
    Boolean readyToFight=false;

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

    public boolean deadYet(){
        if(this.health<0){
            //drop();
            return true;
        }
        else return false;
    }

    @Override
    public void run() {
        Random r = new Random();
        Integer x = r.nextInt(2);
        while(true){
         while(type.equals("enemy") && deadYet()==false && readyToFight==true)
         {
            aType = new AttackSword(this);
            aType.Attack();
             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                
             }
            
         }
        }
        
    }
    
}