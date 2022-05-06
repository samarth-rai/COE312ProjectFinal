public class Animal extends AbstractObserverSubject {
    String name;
    String color;
    String skinType; // skinType for example fur or wool

    Integer health = 30; //default health for all animals

    Animal(String AName, String Acolor, String AskinType) {
        name = AName;
        color = Acolor;
        skinType = AskinType;
    }



    Animal(String AName, String Acolor, String AskinType, Integer health) {
        name = AName;
        color = Acolor;
        skinType = AskinType;
        this.health = health;
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
                UI.print("Your health has decreased by" + m.payload + "points." );
            }
            
        }
    }
}
