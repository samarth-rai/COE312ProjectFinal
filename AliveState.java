public class AliveState implements State {

    @Override
    public void prev(Context context) {
        context.setState(new UndeadState());
        
    }

    @Override
    public void next(Context context) throws NoSuchMethodError{
         
        if(context.getClass().getSimpleName()=="Undead")
        throw new NoSuchMethodError();
        else
        context.setState(new DeadState());

        
    }

    @Override
    public void printStatus(Context context) {
        
        Character character = (Character)context;
        UI.print(character.name + " has come alive!");
        
    }
    
}
