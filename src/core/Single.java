/**
 * Single class
 *
 * @author Derek Pham
 * something like H2 or C5 or Na3
 **/
package core;

public class Single implements Component
{
    private Element element;
    private int index;
    private double mass;

    public Single(Element element, int index)
    {
        this.element = element;
        this.index = index;
        mass = element.getAtomicMass() * index;
    }

    public double getMass()
    {
        return mass;
    }

    public String toString()
    {
        String str = element.getSymbol();
        if (index != 1)
            str += index;
        return str;
    }
}
