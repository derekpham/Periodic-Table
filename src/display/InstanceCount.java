/**
 * Used as a simple counter to count the number of instances active
 *
 * @author David St-Pierre
 **/
package display;

public class InstanceCount
{
    private static int instanceCntConversion = 0;
    private static int instanceCntSearch = 0;

    public InstanceCount()
    {
    }

    //Add one to count
    public void incrementConversion()
    {
        instanceCntConversion++;
    }

    //Subtract one from count
    public void decrementConversion()
    {
        instanceCntConversion--;
    }

    //Return cnt
    public int getCountConversion()
    {
        return (instanceCntConversion);
    }

    //Add one to count
    public void incrementSearch()
    {
        instanceCntSearch++;
    }

    //Subtract one from count
    public void decrementSearch()
    {
        instanceCntSearch--;
    }

    //Return cnt
    public int getCountSearch()
    {
        return (instanceCntSearch);
    }
}
