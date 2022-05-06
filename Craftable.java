public abstract class Craftable{
    final void craftItem(){
    UseWood(); //common
    UseLeaves();//common
    if(UsingStones()){
        RubbingStones();
    }
    
// for creating fire -> place wood -> placeLeaf-> rub stones (sensor) (blow on the fire to make it going, use mic)
// for creating house -> place wood -> place roof

    protected void UseWood() 
    { 
        UI.print("")
    }s

    protected void UseLeave() 
    { 
    }

    protected void RubbingStones() 
    { 
    }
    abstract Rubbing

}