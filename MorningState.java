public class MorningState implements State{

    

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
      UI.print("It is now the morning!");
        
    }

    
}
