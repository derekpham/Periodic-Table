/**
 * ChemicalFormula Class
 *
 * @author Derek Pham
 * Chemical Formula
 **/

package core;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChemicalFormula
{
    private ArrayList<Component> comp;
    private double mass;

    /**
     * Private constructor for the ChemicalFormula class
     **/
    private ChemicalFormula(ArrayList<Component> components)
    {
        this.comp = components;
        this.mass = initMass();
    }

    /**
     * returns <code>ChemicalFormula</code> using the <code>ChemicalFormula</code> class's private constructor
     *
     * @param formula the input formula in string form
     * @return a <code>ChemicalFormula</code> if input string is in correct format and contains actual elements
     * null otherwise
     **/
    public static ChemicalFormula makeFormula(String formula)
    {
        ArrayList<Component> comp = new ArrayList<Component>();
        // regex for to find (ABC)n kinda stuff
        String parenthesisRegex = "\\(.+?\\)[0-9]*";
        Matcher parenMatcher = Pattern.compile(parenthesisRegex).matcher(formula);
        // start index
        int start = 0;
        //stop index before finds ()
        int stop = formula.length();
        while (parenMatcher.find())
        {
            // index of the ( character
            stop = parenMatcher.start();
            // add stuff of anything before the parentheses match
            // if formula invalid, append null to the end
            System.out.println(start);
            System.out.println(stop);
            if (stop > start)
                breakDownNonGroup(formula.substring(start, stop), comp);
            if (comp.size() != 0 && comp.get(comp.size() - 1) == null)
                return null;
            breakDownGroup(parenMatcher.group(), comp);
            if (comp.size() != 0 && comp.get(comp.size() - 1) == null)
                return null;
            start = parenMatcher.end();
        }

        if (start < formula.length())
            breakDownNonGroup(formula.substring(start), comp);
        if (comp.size() != 0 && comp.get(comp.size() - 1) == null)
            return null;
        return new ChemicalFormula(comp);
    }

    private static void breakDownNonGroup(String formula, ArrayList<Component> comp)
    {
        String regex = "[A-Z][a-z]{0,2}[0-9]*";

        if (formula.split(regex).length != 0)
        {
            comp.add(null);
            System.out.println("problem");
            return;
        }
        Matcher m = Pattern.compile(regex).matcher(formula);
        while (m.find())
        {
            String str = m.group();
            String elementStr = "";
            int i = 0;
            while (i < str.length() && !Character.isDigit(str.charAt(i)))
            {
                elementStr += str.charAt(i);
                i++;
            }
            Element element = PT.searchBySymbol(elementStr);
            if (element == null)
            {
                comp.add(null);
                return;
            }
            int index;
            if (i >= str.length())
                index = 1;
            else
                index = Integer.parseInt(str.substring(i));
            // choosing a variable name can be really hard sometimes
            Component fun = new Single(element, index);
            comp.add(fun);
        }
    }

    private static void breakDownGroup(String formula, ArrayList<Component> comp)
    {
        // find the ")" character
        int endParenIndex = 2;
        while (formula.charAt(endParenIndex) != ')')
            endParenIndex++;
        int index;
        if (endParenIndex >= formula.length() - 1)
            index = 1;
        else
            index = Integer.parseInt(formula.substring(endParenIndex + 1));
        String inside = formula.substring(1, endParenIndex);
        ArrayList<Single> group = new ArrayList<Single>();
        String regex = "[A-Z][a-z]{0,2}[0-9]*";
        if (inside.split(regex).length != 0)
        {
            comp.add(null);
            return;
        }

        Matcher m = Pattern.compile(regex).matcher(inside);
        while (m.find())
        {
            String str = m.group();
            String elementStr = "";
            int i = 0;
            while (i < str.length() && !Character.isDigit(str.charAt(i)))
            {
                elementStr += str.charAt(i);
                i++;
            }
            Element element = PT.searchBySymbol(elementStr);
            if (element == null)
            {
                comp.add(null);
                return;
            }
            int index1;
            if (i >= str.length())
                index1 = 1;
            else
                index1 = Integer.parseInt(str.substring(i));
            // choosing a variable name can be really hard sometimes
            Single fun = new Single(element, index1);
            group.add(fun);
        }

        Component bigGroup = new Group(group, index);
        comp.add(bigGroup);
    }

    private double initMass()
    {
        double atomicMass = 0;
        for (Component compo : comp)
            atomicMass += compo.getMass();
        return atomicMass;
    }

    public double getMass()
    {
        return mass;
    }

    public String toString()
    {
        String str = "";
        for (Component compo : comp)
            str += compo.toString();
        return str;
    }
}
