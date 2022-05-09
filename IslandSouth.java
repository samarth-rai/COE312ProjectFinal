public class IslandSouth extends Location
{
    private static IslandSouth instance;
    private IslandSouth(String x, String y) {
    super(x,y);
    }

    public static synchronized IslandSouth getInstance(String x, String y)
    {
        if(instance == null)
        {
            instance = new IslandSouth(x,y);
        }
        return instance;
    }
}