import java.util.ArrayList;
import java.util.Random;

public class Animal extends AbstractObserverSubject implements Runnable {
    String name;
    String color;
    String skinType; // skinType for example fur or wool
    String type;
    Location location;
    Integer health = 30; //default health for all animals
    AttackType aType;
    ArrayList<Objects> dropObjects = new ArrayList<Objects>();
    
    public void attack(Character character)
    {   
        this.registerObserver(character);
        if(type.equals("domestic"))
            {   
                aType = new AttackNone(this);
                Thread th = new Thread(this);
                th.start();
            }
        else
        {
            aType = new AttackScratch(this);
            Thread th = new Thread(this);
            th.start();
        }
    }
    
    Animal(String AName, String Acolor, String AskinType, String type) {
        name = AName;
        color = Acolor;
        skinType = AskinType;
        this.type = type;
        
    }



    Animal(String AName, String Acolor, String AskinType, Location spawnLocation, Integer health, String type) {
        name = AName;
        color = Acolor;
        skinType = AskinType;
        this.location=spawnLocation;
        this.health = health;
        this.type = type;
    }

    public String toString() {
        return name;
    }

    public void inspect() {
        UI.print( "A " + this.getClass().getSimpleName() + " which is " +
                color + " in color and has a skin of " + skinType);
    }

    @Override
    public void update(Message m) {
        switch(m.topic.toLowerCase())
        {
            case "attack":{
                this.health -= Integer.parseInt(m.payload);
                UI.print(this.name + "'s health has decreased by" + m.payload + "points." );
                break;
            }
            
        }
    }
     
    public void drop(){
        if(dropObjects.size()>0){
            UI.print("Your action may have dropped some items!");
        }
        for(int i=0;i<dropObjects.size();i++)
        {
            location.currentlyPlacedObjects.add(dropObjects.get(i));
        }
    }
    public boolean deadYet(){
        if(this.health<0){
            drop();
            return true;
        }
        else return false;
    }


    @Override
    public void run() {
       Random r = new Random();
       Integer x = r.nextInt(2);
        while(type.equals("wild") && deadYet()==false)
        {
            switch(x)
            {
                case 0:
                aType = new AttackPounce(this);
                break;
                case 1:
                aType = new AttackScratch(this);
                break;
            }

            aType.Attack();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
               
            }
            deadYet();
        }
        
    }
}
