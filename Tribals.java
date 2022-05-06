

public class Tribals extends Character
{
    String name;
    String tribe;

    public Tribals(String name) {
		super(name);
		//TODO Auto-generated constructor stub
	}

    public void inspect()
    {
        UI.print( "A " + this.getClass().getSimpleName() + " whose tribe is " + tribe);
    }
    
}