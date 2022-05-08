
public class EveningState implements State{
    
    public EveningState(){
       
    }
    @Override
    public void prev(Context context) {
       context.setState(new AfternoonState());
        
    }

    @Override
    public void next(Context context) {
        context.setState(new NightState());
        
    }

    @Override
    public void printStatus(Context context) {
        UI.print("It is now the evening");
        
    }
    
}

