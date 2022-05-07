public class AttackPounce extends AttackType
{

	public AttackPounce(Object s) {
		super(s);
		//TODO Auto-generated constructor stub
	}

	
	void Attack() {
		Animal attackingAnimal = (Animal) attacker;
		UI.print(attackingAnimal.name + " pounced on you!");
        Message m = new Message(attackingAnimal,"attack","10");
		attackingAnimal.publishMessage(m);
	}
    
}