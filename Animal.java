import java.util.Random;

public class Animal extends AbstractObserverSubject implements Runnable {
    String name;
    String color;
    String skinType; // skinType for example fur or wool
    String type;
    Integer health = 30; //default health for all animals
    AttackType aType;
    
    public void attack(Character character)
    {   
        this.registerObserver(character);
        if(type.equals("domestic"))
            {   
                aType = new NoAttack(this);
                Thread th = new Thread(this);
                th.start();
            }
        else
        {
            aType = new ScratchAttack(this);
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



    Animal(String AName, String Acolor, String AskinType, Integer health, String type) {
        name = AName;
        color = Acolor;
        skinType = AskinType;
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
            }
            
        }
    }



    @Override
    public void run() {
       Random r = new Random();
       Integer x = r.nextInt(2);
        while(type.equals("wild"))
        {
            switch(x)
            {
                case 0:
                aType = new PounceAttack(this);
                break;
                case 1:
                aType = new ScratchAttack(this);
                break;
            }

            aType.Attack();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
               
            }
        }
        
    }
}
