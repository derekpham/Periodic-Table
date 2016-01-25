/**
 * PeriodicTable library
 *
 * @author Derek Pham
 **/

///import java.util.Arrays;
package core;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class PT
{
    private static final ArrayList<Element> PeriodicTable = loadDatabase();

    private static final ArrayList<Element> sortedPT = copyAndSort(PeriodicTable);

    /* This function reads, analyze and load a bunch of elements in PeriodicTable
     */
    private static ArrayList<Element> loadDatabase()
    {
        ArrayList<Element> PT = new ArrayList<Element>();
        String fileLocation = "Database/chemdb";
        try
        {
            FileInputStream fstream = new FileInputStream(fileLocation);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String str;
            while ((str = br.readLine()) != null)
            {
                String[] parts = str.split(":");
                PT.add(new Element(parts[0], parts[1], Integer.parseInt(parts[2]), Double.parseDouble(parts[3]), parts[4], parts[5]));
            }
        /*
	      String name = br.readLine();
	      String symbol = br.readLine();
	      int atomicNum = Integer.parseInt(br.readLine());
	      double atomicMass = Double.parseDouble(br.readLine());
	      String type = br.readLine();
	      String family = br.readLine();
	      PT.add(new Element(name, symbol, atomicNum, atomicMass, type, family));*/
        } catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
            System.out.println("Check database file maybe?");
        }
        return PT;
    }

    // Checks if the file exists
    private static boolean existsA(int fileName)
    {
        String path1 = "PeriodicTable/Database/" + fileName;
        File f1 = new File(path1);
        return (f1.exists() && !f1.isDirectory());
    }

    private static boolean existsB(int fileName)
    {
        String path2 = "Database/" + fileName;
        File f2 = new File(path2);
        return (f2.exists() && !f2.isDirectory());
    }

    /** generate a copy list of a given list and sort it
     **/
    private static ArrayList<Element> copyAndSort(ArrayList<Element> list)
    {
        ArrayList<Element> temp = new ArrayList<Element>();
        for (Element fun : list)
            temp.add(fun);
        Collections.sort(temp);
        return temp;
    }

    /** finds the element in the periodic table
     @param symbol the symbol of the element to find
     @return the element if found, otherwise null
     **/
    public static Element searchBySymbol(String symbol)
    {
        return searchHelper(symbol, 0, sortedPT.size() - 1);
    }

    /** recursive search helper for the public search function
     **/
    private static Element searchHelper(String symbol, int lo, int hi)
    {
        if (lo > hi)
            return null;
        Element found = sortedPT.get((lo + hi) / 2);
        int compare = found.getSymbol().compareTo(symbol);
        if (compare == 0)
            return found;
        if (compare < 0)
            return searchHelper(symbol, (lo + hi) / 2 + 1, hi);
        return searchHelper(symbol, lo, (lo + hi) / 2 - 1);
    }

    /** returns the element according to the atomic number given
     @param atomicNumber the atomic number to find
     @return the element if atomic number is correct. Otherwise null.
     **/
    public static Element getElement(int atomicNumber)
    {
        if (atomicNumber < 1 || atomicNumber > 118)
            return null;
        return PeriodicTable.get(atomicNumber - 1);
    }

    /** returns the size of the PeriodicTable
     *  @return the number of elements on the PeriodicTable
     **/
    public static int size()
    {
        return PeriodicTable.size();
    }

    /** searches element by name (INEFFICIENT!!!!!!!!!) Need some thinking here
     Possible idea but know how to: sort the table by name, but this takes extra memory and too much work to rewrite the sort function... still haven't learned about quick sort...
     @param name the name to search for
     @return the element if found, null otherwise
     **/
    public static Element searchByName(String name)
    {
        for (Element fun : PeriodicTable)
            if (fun.getName().equals(name))
                return fun;
        return null;
    }

    /** searches element by mass
     *  @param mass the mass to search for
     *  @param decimalPlace the number of decimal places that searcher wants to go to
     *  @return the closest element to the given mass
     **/
    public static Element searchByMass(double mass, int decimalPlace)
    {
        mass = round(mass, decimalPlace);
        for (Element element : PeriodicTable)
            if (round(element.getAtomicMass(), decimalPlace) == mass)
                return element;
        return null;
    }

    // Round a number
    // maxium of 5 decimal place
    private static double round(double mass, int decimalPlace)
    {
        if (decimalPlace > 5)
            decimalPlace = 5;
        double copyMass = mass;
        for (int i = 0; i < decimalPlace; i++)
            copyMass *= 10;
        copyMass += 0.5;
        copyMass = (int) copyMass;
        for (int i = 0; i < decimalPlace; i++)
            copyMass /= 10;
        return copyMass;
    }


}
