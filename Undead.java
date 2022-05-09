public class Undead extends Character implements Context, Runnable{

    private State state = new DeadState();


    AttackType aType = new AttackNone(this);

    public Undead(Subject[] subjects) {
        super(subjects);
    }
    
    public Undead(String name, Location spawnLocation, Integer health, Integer inventorySize, String description) {
       super(name,spawnLocation,health,inventorySize,description);
       spawnLocation.currentlyPlacedCharacters.add(this);
    }

    public void attack(Character character)
    {
        if(this.state.getClass().getSimpleName().equalsIgnoreCase("DeadState"))
        {
            UI.print("You cannot fight a dead creature!");
        }
        else if(this.state.getClass().getSimpleName().equalsIgnoreCase("UndeadState"))
        {
            this.registerObserver(character);
            aType = new AttackUndead(this);
            Thread th = new Thread(this);
            th.start();
        }
    }
    
    //Methods to control state pattern
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

    @Override
    public void run() {
        while(health>0)
        {
            //
            aType.Attack();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(health<=0){
            nextState();
            //this.setState(new DeadState());
        }
        
    }



    
}
