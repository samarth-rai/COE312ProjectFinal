import java.rmi.NoSuchObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import javax.management.modelmbean.DescriptorSupport;

//This class is for locations, it is runnable to publish messages

public class Location extends AbstractObserverSubject implements Runnable
{

    public String name;
	public String description;
	public Boolean visited = false;
	public ArrayList<Objects> currentlyPlacedObjects = new ArrayList<Objects>(); 
	public ArrayList<Animal> currentlyPlacedAnimals = new ArrayList<Animal>();
	public ArrayList<Character> currentlyPlacedCharacters = new ArrayList<Character>();
	// This arraylist contains all the objects present at the current location
	
    

		
	public Location(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public void run() {
		
		
	}


	public Animal getAnimal(String s) throws NoSuchElementException
	{
		for(int i=0;i<currentlyPlacedAnimals.size();i++)
		{
			if(currentlyPlacedAnimals.get(i).name.equalsIgnoreCase(s))
				return currentlyPlacedAnimals.get(i);
		}

		throw new NoSuchElementException();
	}

	public Character getCharacter(String s) throws NoSuchElementException
	{
		for(int i=0;i<currentlyPlacedCharacters.size();i++)
		{
			if(currentlyPlacedCharacters.get(i).name.equalsIgnoreCase(s))
				return currentlyPlacedCharacters.get(i);
		}

		throw new NoSuchElementException();
	}

	public Objects getObjects(String s) throws NoSuchElementException
	{
		for(int i=0;i<currentlyPlacedObjects.size();i++)
		{
			if(currentlyPlacedObjects.get(i).name.equalsIgnoreCase(s))
				return currentlyPlacedObjects.get(i);
		}

		throw new NoSuchElementException();
	}

	public void lookAround()
	{
		UI.print(name);
		UI.print("Characters currently here:");
		UI.printArrayList(currentlyPlacedCharacters);
		UI.print("Animals currently here:");
		UI.printArrayList(currentlyPlacedAnimals);
		UI.print("\nObjects currently here:");
		UI.printArrayList(currentlyPlacedObjects);
	}

	@Override
	public void update(Message m) {
        switch(m.topic.toLowerCase())
        {
            case "travel": 
            {
                if(m.payload.equalsIgnoreCase(this.name))
				{
					Player c = (Player)m.origin;
					c.currentLocation=this;
				}
            }
            break;
        }
	}

	

	

}