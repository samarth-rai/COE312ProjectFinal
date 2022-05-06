public abstract class Craftable{
    final void craftItem()
    {
        UseWood(); //common
        UseLeaves();//common
        if(UsingStones()){
            RubbingStones();
        }
        Completed();
        
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
    private boolean UsingStones(){
        return false;
    }

    abstract void Completed();
    abstract void RubbingStones();
}