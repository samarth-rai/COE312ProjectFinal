
public class IslandEast extends Location
{
    private static IslandEast instance;
    private IslandEast(String x, String y) {
    super(x,y);
    }

    public static synchronized IslandEast getInstance(String x, String y)
    {
        if(instance == null)
        {
            instance = new IslandEast(x,y);
        }
        return instance;
    }
}
