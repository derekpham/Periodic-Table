/**
 * Group class
 *
 * @author Derek Pham
 * something like (H2SO4)2
 **/
package core;

import java.util.ArrayList;

public class Group implements Component
{
    private ArrayList<Single> elements;
    private int index;
    private double mass;

    public Group(ArrayList<Single> elements, int index)
    {
        this.elements = elements;
        this.index = index;
        mass = initMass();
    }

    private double initMass()
    {
        double atomicMass = 0;
        for (Single element : elements)
            atomicMass += element.getMass();
        return atomicMass * index;
    }

    public double getMass()
    {
        return mass;
    }

    public String toString()
    {
        String str = "(";
        for (Single single : elements)
            str += single.toString();
        str += ")";
        if (index != 1)
            str += index;
        return str;
    }
}
