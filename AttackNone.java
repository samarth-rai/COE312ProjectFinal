public class AttackNone extends AttackType{


    public AttackNone(Object s) {
        super(s);
    }

    @Override
    void Attack() {
        
        if(attacker.getClass().getSimpleName().equals("Animal"))
        {
            Animal animal = (Animal) attacker; 
            UI.print(animal + " is friendly!");
        }

        else if(attacker.getClass().getSimpleName().equals("Tribal"))
        {
            Tribals tribal = (Tribals) attacker; 
            UI.print(tribal + " is friendly!");
        }
        else
        {
        Character attackingCharacter = (Character) attacker;
        UI.print(attackingCharacter + " is friendly!");
        }
        
    }
    



}
