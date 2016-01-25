/**
 * @author Derek Pham @ David St-Pierre
 * Element class
 * for an element on the periodic table
 **/
package core;

public class Element implements Comparable<Element>
{
    private String name;
    private String symbol;
    private int atomicNum;
    private double atomicMass;
    private String type;
    private String family;

    /**
     * Explicit constructor for the Element class
     *
     * @param name       the full name of the element
     * @param symbol     the symbol of the element
     * @param atomicNum  the atomic number of the element
     * @param atomicMass the atomic mass of the element
     * @param type       the type of the element (metal, nonmetal, or metalloid)
     * @param family     the family of the element (ex: akali metal)
     **/
    public Element(String name, String symbol, int atomicNum, double atomicMass, String type, String family)
    {
        this.name = name;
        this.symbol = symbol;
        this.atomicNum = atomicNum;
        this.atomicMass = atomicMass;
        this.type = type;
        this.family = family;
    }

    /**
     * return the name of the element
     *
     * @return the name of the element
     **/
    public String getName()
    {
        return name;
    }

    /**
     * return the symbol of the element
     *
     * @return the symbol of the element
     **/
    public String getSymbol()
    {
        return symbol;
    }

    /**
     * return the atomic number of the element
     *
     * @return the atomic number of the element
     **/
    public int getAtomicNumber()
    {
        return atomicNum;
    }

    /**
     * return the atomic mass of the element
     *
     * @return the atomic mass of the element
     **/
    public double getAtomicMass()
    {
        return atomicMass;
    }

    /**
     * return the type of the element (metal, nonmetal, metalloid)
     *
     * @return the type of the element
     **/
    public String getType()
    {
        return type;
    }

    /**
     * return the family of the element (ex: akali metal)
     *
     * @return the family of the element
     **/
    public String getFamily()
    {
        return family;
    }

    /**
     * compare one element to another by name
     *
     * @param other the other element to be compared to
     * @return 0 if two elements have the same name
     * a negative number if <code>this</code>'s name is smaller than <code>other</other>'s
     * a positive number otherwise
     **/
    public int compareTo(Element other)
    {
        return this.symbol.compareTo(other.symbol);
    }

    /**
     * returns information of the element
     *
     * @return the information of the element in <code>String</code>
     **/
    public String toString()
    {
        return "Name: " + name + "\nSymbol: " + symbol + "\nAtomic Number: " + atomicNum + "\nAtomic Mass: " + atomicMass + "\ntype: " + type + "\nFamily: " + family;
    }
}
