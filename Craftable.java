public abstract class Craftable //IMPLEMENTS TEMPLATE PATTERN
{
    final void craftItem()
    {
        UseWood(); //common
        UseLeaves();//common
        if(PlayerUsingStones()) //bool func
        {
            RubbingStones();
        }
        Completed();    //abstract func
    }
    
// for creating fire -> place wood -> placeLeaf-> rub stones (sensor) (blow on the fire to make it going, use mic)
// for creating house -> place wood -> place roof


	protected void UseWood() 
    { 
        UI.print("placing wood");
    }

    protected void UseLeaves() 
    { 
        UI.print("using leaves");
    }
    private boolean PlayerUsingStones(){
        return false;
    }

    abstract void Completed();
    abstract void RubbingStones();
}