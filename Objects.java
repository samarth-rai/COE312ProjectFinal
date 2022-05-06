import java.util.ArrayList;

// Package not detected, please report project structure on CodeTogether's GitHub Issues


public class Objects extends AbstractObserver{
    //likely objects would observe the character and act upon it
    //hence all objects can be constructor
	
	String name;
	String description;
	public Objects(Subject[] subject, String name, String description) {
		super(subject);
		this.name = name;
		this.description = description;
	}
	public Objects(String name, String description) {
		this.name = name;
		this.description = description;
	}
	@Override
	public void update(Message m) {
		// TODO Auto-generated method stub
		
	}

    public void inspect() {

		UI.print(name + "\n");
    }
    

}
