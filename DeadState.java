public class DeadState implements State{

    @Override
    public void prev(Context context) {
       context.setState(new AliveState());
       Character character = (Character)context;
       character.health = character.fullHealth;
    }

    @Override
    public void next(Context context) throws NoSuchMethodError {
        
        context.setState(new UndeadState());
        Character character = (Character)context;
        character.health = 30;

    }

    public void printStatus(Context context) {
       Character character = (Character)context;
       UI.print(character.name + " has died.");
        
    }
    
}
