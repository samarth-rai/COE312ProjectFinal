public class Fire extends Craftable
{
    public boolean PlayerUsingStones(){
        return true;
    }
	@Override
	void RubbingStones() {
		// TODO Auto-generated method stub
		UI.print("*sparks* *sparks*");
	}

    @Override
	void Completed() {
		// TODO Auto-generated method stub
		UI.print("Fire is made!");
	}
    
}