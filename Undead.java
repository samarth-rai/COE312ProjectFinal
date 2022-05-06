public class Undead extends Character implements Context{

    private State state = new DeadState();

    public Undead(Subject[] subjects) {
        super(subjects);
    }

    public Undead(String name) {
        super(name);
    }

    @Override
    public void previousState() {
        state.prev(this);
    }

    @Override
    public void nextState() {
       state.next(this);
        
    }

    @Override
    public void printStatus() {
      state.printStatus(this);
        
    }

    @Override
    public void setState(State state) {
       this.state = state;
        
    }



    
}
