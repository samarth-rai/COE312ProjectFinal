public class MorningState implements State{

    Integer hour = 6;

    @Override
    public void prev(Context context) {
        context.setState(new NightState());
        
    }

    @Override
    public void next(Context context) {
        context.setState(new AfternoonState());
        
    }

    @Override
    public void printStatus(Context context) {
      
        
    }

    
}
