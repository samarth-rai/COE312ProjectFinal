public class AfternoonState implements State{

    @Override
    public void prev(Context context) {
       context.setState(new MorningState());
        
    }

    @Override
    public void next(Context context) {
        context.setState(new NightState());
        
    }

    @Override
    public void printStatus(Context context) {
        
        
    }
    
}
