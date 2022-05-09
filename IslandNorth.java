//This is where the player starts out.

public class IslandNorth extends Location
{
    private static IslandNorth instance;
    private IslandNorth(String x, String y) {
    super(x,y);
    }

    public static synchronized IslandNorth getInstance(String x, String y)
    {
        if(instance == null)
        {
            instance = new IslandNorth(x,y);
        }
        return instance;
    }
}