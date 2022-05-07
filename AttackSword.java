public class AttackSword extends AttackType{

	


	public AttackSword(Object s) {
		super(s);
	}

	void Attack() {
		
		Character attackingCharacter = (Character) attacker;
		UI.print(attackingCharacter.name + " attacked you with a sword!");
        Message m = new Message(attackingCharacter,"attack","5");
		attackingCharacter.publishMessage(m);
	}

}