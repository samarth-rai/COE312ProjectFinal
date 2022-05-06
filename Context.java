public interface Context {
    
    public void previousState();
    public void nextState();
    public void printStatus();
    public void setState(State state);

}
