/**
 * Reads in the element button locations and stores them in an array
 *
 * @author David St-Pierre
 **/
//import java.util.Scanner;
package display;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ElementLocation
{
    private List<Integer> location = new ArrayList<Integer>();

    //Load the list into an arrayList
    public ElementLocation() throws IOException
    {
        String fileName = "ElementLocationList.txt";
        File f = new File(fileName);
        String fileLoc;
        if (existsA(fileName)) fileLoc = "display/" + fileName;
        else fileLoc = fileName;
        BufferedReader inFile = new BufferedReader(new FileReader(fileLoc));
        if (!inFile.ready())
        {
            System.out.println("Can't find ElementLocationList.txt");
            System.exit(1);
        }
        for (int i = 0; i < 118; i++)
        {
            String line = inFile.readLine();
            StringTokenizer tokens = new StringTokenizer(line, ",");
            int posX = Integer.parseInt(tokens.nextToken());
            int posY = Integer.parseInt(tokens.nextToken());
            location.add(posX);
            location.add(posY);
        }
    }

    // Checks if the file exists
    private static boolean existsA(String fileName)
    {
        String path1 = "PeriodicTable/" + fileName;
        File f1 = new File(path1);
        return (f1.exists() && !f1.isDirectory());
    }

    private static boolean existsB(String fileName)
    {
        String path2 = fileName;
        File f2 = new File(path2);
        return (f2.exists() && !f2.isDirectory());
    }

    //Returns the x location of the element
    public int getX(int elementNum)
    {
        return (location.get(elementNum * 2));
    }

    //Returns the y location of the element
    public int getY(int elementNum)
    {
        return (location.get((elementNum * 2) + 1));
    }

}

