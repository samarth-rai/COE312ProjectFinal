public class UndeadState implements State{

    @Override
    public void prev(Context context) throws NoSuchMethodError {
       
        context.setState(new DeadState());
        Character character = (Character)context;
        character.health = 0;
        
    }

    @Override
    public void next(Context context) {
       
        context.setState(new AliveState());
        Character character = (Character)context;
        character.health = character.fullHealth;
        
    }

    @Override
    public void printStatus(Context context) {
        Character character = (Character)context;
        UI.print(character.name + " is now undead");
        
        
    }
    
}
