public class NightState implements State {

    
    @Override
    public void prev(Context context) {
        context.setState(new EveningState());
        
    }

    @Override
    public void next(Context context) {
       context.setState(new MorningState());
        
    }

    @Override
    public void printStatus(Context context) {
        UI.print("It is now the night!");
        
    }
    
}
