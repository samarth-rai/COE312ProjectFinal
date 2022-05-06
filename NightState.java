public class NightState implements State {

    
    @Override
    public void prev(Context context) {
        context.setState(new AfternoonState());
        
    }

    @Override
    public void next(Context context) {
       context.setState(new MorningState());
        
    }

    @Override
    public void printStatus(Context context) {
        
        
    }
    
}
