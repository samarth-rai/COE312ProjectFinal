public abstract class Craftable extends AbstractObserver //IMPLEMENTS TEMPLATE PATTERN
{
    final void craftItem()
    {
        placeMaterials();
        performAction();
        if(!actionRequired())
        {
            Completed();
        }
        

    }
    
// for creating fire -> place wood -> placeLeaf-> rub stones (sensor) (blow on the fire to make it going, use mic)
// for creating house -> place wood -> place roof

    abstract void placeMaterials();
    abstract void performAction();
    abstract void Completed();
    Boolean actionRequired()
    {
        return false;
    }
    
}