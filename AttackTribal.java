public class AttackTribal extends AttackType{

	


	public AttackTribal(Object s) {
		super(s);
	}

	void Attack() {
		
		Character attackingCharacter = (Character) attacker;
		UI.print(attackingCharacter.name + " attacked you with a primitive stick");
        Message m = new Message(attackingCharacter,"attack","5");
		attackingCharacter.publishMessage(m);
	}

}