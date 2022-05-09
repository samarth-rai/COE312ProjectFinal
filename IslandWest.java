public class IslandWest extends Location
{
    private static IslandWest instance;
    private IslandWest(String x, String y) {
    super(x,y);
    }

    public static synchronized IslandWest getInstance(String x, String y)
    {
        if(instance == null)
        {
            instance = new IslandWest(x,y);
        }
        return instance;
    }
}