public class PounceAttack extends AttackType
{

	public PounceAttack(Object s) {
		super(s);
		//TODO Auto-generated constructor stub
	}

	
	void Attack() {
		Animal attackingAnimal = (Animal) attacker;
		UI.print(attackingAnimal.name + " pounced on you!");
        Message m = new Message(attackingAnimal,"attack","5");
		attackingAnimal.publishMessage(m);
	}
    
}