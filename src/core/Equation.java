/**
 * Chemical Equation
 *
 * @author Derek Pham
 **/
///*** Warning: This is not perfect
//// Because an equation also needs prefixes in front of each formula
package core;

import java.util.ArrayList;

public class Equation
{
    private ArrayList<ChemicalFormula> reactors;
    private ArrayList<ChemicalFormula> products;
    private boolean balancable;
    private boolean balanced;

    public Equation(ArrayList<ChemicalFormula> reactors, ArrayList<ChemicalFormula> products)
    {
        for (ChemicalFormula formula : reactors)
            this.reactors.add(formula);
        for (ChemicalFormula formula : products)
            this.products.add(formula);
    }

    // Writing in the future
    // Whether the reactor and products side have the same elements
    public boolean isValid()
    {
        return false;
    }

    // Writing in the future
    // Whether the equation can be balanced or not
    public boolean isBalancable()
    {
        return false;
    }

    // auto-balance the equation if not balanced ==  false
    public void autoBalance()
    {

    }
}
