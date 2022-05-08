public class Fire extends Craftable
{
	Boolean actionRequired()
	{
		return true;
	}
	@Override
	void placeMaterials()
	{
		UI.print("Stones have been placed");
	}


    @Override
	 void Completed() {
		GameMaster.t.removeObsever(this);
		UI.print("Fire has been made!");
	}

	@Override
	void performAction()
	{
		UI.print("Hold your phone vertically, rub it like sticks and stones and blow into it to create a fire.");
		GameMaster.t.registerObserver(this);
	}


	@Override
	public void update(Message m) {
		if(m.topic.equals("fireIssues"))
		{
			UI.print(m.payload);
		}
		if(m.topic.equals("fire"))
		{
			UI.print("*spark* *spark*");			
			Completed();

		}
		
	}
    
}