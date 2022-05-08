public class Fire extends Craftable
{
	Boolean actionRequired()
	{
		return true;
	}
	@Override
	void placeMaterials()
	{
		for(int i=0; i<GameMaster.player.inventory.size();i++){
			if(GameMaster.player.inventory.get(i).name.toLowerCase().equals("wood")){
				Objects o1 = GameMaster.player.inventory.get(i);
			   for(int j=0; j<GameMaster.player.inventory.size();j++){
				 if(GameMaster.player.inventory.get(j).name.toLowerCase().equals("leaves")){
					Objects o2 = GameMaster.player.inventory.get(j);
					 for(int k =0; k<GameMaster.player.inventorySize;k++){
						if(GameMaster.player.inventory.get(k).name.toLowerCase().equals("stones")){
							Objects o3 = GameMaster.player.inventory.get(k);
							GameMaster.player.inventory.remove(o1);
							UI.print("Leaves have been placed");
							GameMaster.player.inventory.remove(o2);
							UI.print("Stones have been placed");
							GameMaster.player.inventory.remove(o3);
							UI.print("Wood has been placed");
							return;
						}
					 }
				 }
			   }
			}
		}
		UI.print("You do not have the required items to make this!");
	}


    @Override
	 void Completed() {
		GameMaster.t.removeObsever(this);
		GameMaster.player.currentLocation.currentlyPlacedObjects.add(new Objects("Fire", "A nice warm flame."));
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