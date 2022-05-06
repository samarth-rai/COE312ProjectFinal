public class UndeadAttack extends AttackType{

	
    public UndeadAttack(Object s) {
        super(s);
    }

    void Attack(){
        Undead undead = (Undead) attacker;
        UI.print(undead.name + " is performing a zombie attack!");
        Message m = new Message(attacker, "attack", "15");
        undead.publishMessage(m);
    }



}