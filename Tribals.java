import java.util.Random;

public class Tribals extends Character implements Runnable
{
    String name;
    String tribe;
    String type = "enemy";
    AttackType aType;
    Boolean readyToFight=false;
    Character character;

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
        if(deadYet()==false)UI.print( "A tribal whose tribe is " + tribe);
        else
        {
            UI.print("The corpse of a tribal from " + tribe); 
        }
    }

    public void attack(Character character)
    {
        readyToFight=true;
        this.character = character;
        this.registerObserver(character);
        Thread th = new Thread(this);
        th.start();
    }

    public boolean deadYet(){
        if(this.health<0){
            readyToFight=false;
            publishMessage(new Message(this,"stopFight",""));
            character.removeObsever(this);
            return true;
        }
        else return false;
    }

    @Override
    public void run() {
        while(true){
         while(type.equals("enemy") && deadYet()==false && readyToFight==true)
         {
             
            aType = new AttackTribal(this);
            aType.Attack();
             try {
                 Thread.sleep(500);
             } catch (InterruptedException e) {
                
             }
            deadYet();
         }
        }
        
    }
    
}