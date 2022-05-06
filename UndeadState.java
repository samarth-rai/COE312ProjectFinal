public class UndeadState implements State{

    @Override
    public void prev(Context context) throws NoSuchMethodError {
       
        throw new NoSuchMethodError();
        
    }

    @Override
    public void next(Context context) {
       
        context.setState(new AliveState());
        
    }

    @Override
    public void printStatus(Context context) {
        Character character = (Character)context;
        UI.print(character.name + " is now undead");
        
        
    }
    
}
