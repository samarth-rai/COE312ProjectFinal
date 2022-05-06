public class DeadState implements State{

    @Override
    public void prev(Context context) {
       context.setState(new AliveState());
        
    }

    @Override
    public void next(Context context) throws NoSuchMethodError {
        
        throw new NoSuchMethodError();

    }

    public void printStatus(Context context) {
       Character character = (Character)context;
       UI.print(character.name + " has died.");
        
    }
    
}
