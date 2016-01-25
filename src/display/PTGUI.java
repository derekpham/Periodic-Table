/**
 * GUI to display the grid of elements
 *
 * @author David St-Pierre
 **/
package display;

import core.PT;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PTGUI extends JFrame
{

    private Container cp;
    private Conversion c;
    private Search s;

    private JMenuBar menuBar;

    /**
     * Default constructor.  Creates the Periodic Table GUI
     *
     * @throws IOException
     */
    public PTGUI() throws IOException
    {
        cp = getContentPane();
        cp.setLayout(null);
        cp.setBackground(Color.CYAN);

        initButtons();

        menuBar = new JMenuBar();
        initMenuBar();

        initKey();

        //Set up row and column labels
        initRC();

        JLabel label = new JLabel();
        label.setIcon(new ImageIcon("Images/logo.png"));
        cp.add(label);
        Dimension size = label.getPreferredSize();
        label.setBounds(300 + 40, 30 + 40, size.width, size.height);

        //Size/Title/Size/Menu bar/Close Operation/Visibility
        setResizable(true);
        setTitle("Interactive Periodic Table 2015");
        setSize(960, 700);
        setSize(950, 700);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * initButtons sets an array of buttons to the name of an element and a specific location
     * using the <code> PT </code> and <code> ElementLocation </code> classes
     *
     * @throws IOException
     */
    private void initButtons() throws IOException
    {
        JButton buttons[] = new JButton[118];
        ElementLocation loc = new ElementLocation(); //ElementLocation reads a file with the locations

        for (int i = 1; i <= buttons.length; i++)
        {
            buttons[i - 1] = new JButton();
            buttons[i - 1].setForeground(Color.BLACK);    //Sets text color

            //Get the element's type
            String elementType = PT.getElement(i).getFamily();

            //Set the background color according to the type of element it is
            if (elementType.equals("Alkali Metal")) buttons[i - 1].setBackground(new Color(255, 102, 255));
            else if (elementType.equals("Noble Gas")) buttons[i - 1].setBackground(new Color(255, 128, 0));
            else if (elementType.equals("Alkaline Earth Metal")) buttons[i - 1].setBackground(new Color(255, 0, 127));
            else if (elementType.equals("Metalloid")) buttons[i - 1].setBackground(new Color(51, 255, 51));
            else if (elementType.equals("Non Metal")) buttons[i - 1].setBackground(new Color(0, 102, 204));
            else if (elementType.equals("Halogen")) buttons[i - 1].setBackground(Color.YELLOW);
            else if (elementType.equals("Transition Metal")) buttons[i - 1].setBackground(new Color(102, 178, 255));
            else if (elementType.equals("Lanthanide Series")) buttons[i - 1].setBackground(new Color(204, 204, 0));
            else if (elementType.equals("Actinide Series")) buttons[i - 1].setBackground(Color.RED);
            else buttons[i - 1].setBackground(Color.BLACK);       //Error color

            buttons[i - 1].setMargin(new Insets(0, 0, 0, 0));
            buttons[i - 1].setFont(new Font("Mono", Font.BOLD, 9));
            buttons[i - 1].setText("<html><div style=\"text-align: center;\">" + PT.getElement(i).getAtomicNumber() + "<br>" + PT.getElement(i).getSymbol() + "<br>" + PT.getElement(i).getAtomicMass() + "</html>");

            //Set button border
            buttons[i - 1].setBorder(new LineBorder(Color.BLACK, 2));

            //Add the buttons to the content pane
            cp.add(buttons[i - 1]);

            //Create an action listener for each button
            final int a = i;
            buttons[i - 1].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    InstanceCount cnt = new InstanceCount();
                    if (cnt.getCountConversion() == 0)
                    {
                        new ElementGUI(a);      //Call a window to display specific element information
                    } else if (cnt.getCountConversion() == 1)
                    {
                        c.addText(PT.getElement(a).getSymbol());
                    } else System.out.println("Error, too many instances of conversion");
                }
            });
            buttons[i - 1].setBounds(loc.getX(i - 1) + 40, loc.getY(i - 1) + 40, 45, 40);
        }
    }

    /**
     * Sets up the key at the bottom of the window.  This key lets users know what each color means
     */
    private void initKey()
    {
        JLabel key[] = new JLabel[9];

        //Set text
        key[0] = new JLabel("Noble Gas", SwingConstants.CENTER);
        key[1] = new JLabel("<HTML>Alkaline<br>Earth<br>Metal<HTML>", SwingConstants.CENTER);
        key[2] = new JLabel("Alkali Metal", SwingConstants.CENTER);
        key[3] = new JLabel("Metalloid", SwingConstants.CENTER);
        key[4] = new JLabel("Non-Metal", SwingConstants.CENTER);
        key[5] = new JLabel("<HTML>Transition<br>Metal<HTML>", SwingConstants.CENTER);
        key[6] = new JLabel("Halogen", SwingConstants.CENTER);
        key[7] = new JLabel("<HTML>Lanthanide<br>Series<HTML>", SwingConstants.CENTER);
        key[8] = new JLabel("<HTML>Actinide<br>Series<HTML>", SwingConstants.CENTER);

        //Set button location
        key[0].setLocation(10 + 40, 560 + 40);
        key[1].setLocation(110 + 40, 560 + 40);
        key[2].setLocation(210 + 40, 560 + 40);
        key[3].setLocation(310 + 40, 560 + 40);
        key[4].setLocation(410 + 40, 560 + 40);
        key[5].setLocation(510 + 40, 560 + 40);
        key[6].setLocation(610 + 40, 560 + 40);
        key[7].setLocation(710 + 40, 560 + 40);
        key[8].setLocation(810 + 40, 560 + 40);
        //Set background color
        key[0].setBackground(new Color(255, 128, 0));
        key[1].setBackground(new Color(255, 0, 127));
        key[2].setBackground(new Color(255, 102, 255));
        key[3].setBackground(new Color(51, 255, 51));
        key[4].setBackground(new Color(0, 102, 204));
        key[5].setBackground(new Color(102, 178, 255));
        key[6].setBackground(Color.YELLOW);
        key[7].setBackground(new Color(204, 204, 0));
        key[8].setBackground(Color.RED);
        //Set text color/size/visibility and add the button
        for (int i = 0; i < 9; i++)
        {
            key[i].setForeground(Color.BLACK);
            key[i].setSize(90, 50);
            key[i].setOpaque(true);
            cp.add(key[i]);
        }
    }

    /**
     * <code> initRC </code> creates the rows and column labels
     **/
    private void initRC()
    {
        JLabel col[] = new JLabel[18];
        JLabel row[] = new JLabel[7];
        //Set text
        for (int i = 0; i < 18; i++)
        {
            col[i] = new JLabel(i + 1 + "");
            //	    col[i].setMargin(new Insets(0, 0, 0, 0));
            col[i].setFont(new Font("Mono", Font.BOLD, 9));
        }
        for (int i = 0; i < 7; i++)
        {
            row[i] = new JLabel(i + 1 + "");
            row[i] = new JLabel(i + 1 + "");
            //	    row[i].setMargin(new Insets(0, 0, 0, 0));
        }
        for (int i = 0; i < 18; i++)
            col[i] = new JLabel(i + 1 + "", SwingConstants.LEFT);
        for (int i = 0; i < 7; i++)
            row[i] = new JLabel(i + 1 + "", SwingConstants.LEFT);

        col[0].setLocation(60, 10);
        col[1].setLocation(100, 10);
        col[2].setLocation(150, 10);
        col[3].setLocation(200, 10);
        col[4].setLocation(250, 10);
        col[5].setLocation(300, 10);
        col[6].setLocation(350, 10);
        col[7].setLocation(400, 10);
        col[8].setLocation(450, 10);
        col[9].setLocation(500, 10);
        col[10].setLocation(550, 10);
        col[11].setLocation(600, 10);
        col[12].setLocation(650, 10);
        col[13].setLocation(700, 10);
        col[14].setLocation(750, 10);
        col[15].setLocation(800, 10);
        col[16].setLocation(850, 10);
        col[17].setLocation(900, 10);

        row[0].setLocation(10, 50);
        row[1].setLocation(10, 100);
        row[2].setLocation(10, 150);
        row[3].setLocation(10, 200);
        row[4].setLocation(10, 250);
        row[5].setLocation(10, 300);
        row[6].setLocation(10, 350);

        for (int i = 0; i < 18; i++)
        {
            col[i].setFont(new Font("Mono", Font.BOLD, 18));
            col[i].setForeground(Color.BLACK);
            col[i].setSize(50, 50);
            cp.add(col[i]);
        }
        for (int i = 0; i < 7; i++)
        {
            row[i].setFont(new Font("Mono", Font.BOLD, 18));
            row[i].setForeground(Color.BLACK);
            row[i].setSize(50, 50);
            cp.add(row[i]);
        }
    }

    /**
     * <code> initMenuBar </code> creates the menu at the top of the window and sets actionListeners for each button
     *
     * @throws IOException
     */
    private void initMenuBar() throws IOException
    {

        JMenu fileMenu;
        JMenuItem quit;
        JMenu aboutMenu;
        JMenuItem credits;
        JMenu tasksMenu;
        JMenuItem search;
        /** IMPORTANT
         PLS SERIOUSLY NOTE THAT
         THIS THING WILL BE GENERALIZED TO JUST UNIT CONVERSION
         UNTIL WE FIND A WAY TO CONVERT ONT THING TO ANOTHER
         **/
        JMenuItem massMoleConversion;

        fileMenu = new JMenu("File");
        quit = new JMenuItem("Quit");
        quit.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        fileMenu.add(quit);

        tasksMenu = new JMenu("Tasks");
        massMoleConversion = new JMenuItem("Mass-Mole Conversion");
        massMoleConversion.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    InstanceCount cnt = new InstanceCount();
                    if (cnt.getCountConversion() == 1)
                    { //Error window pop up to be added
                        JOptionPane.showMessageDialog(new JFrame(), "You can't open more than one window of Unit Conversion", "Warning", JOptionPane.WARNING_MESSAGE);
                    } else if (cnt.getCountConversion() == 0)
                    {
                        c = new Conversion();
                        cnt.incrementConversion();
                        if (cnt.getCountSearch() > 0) s.passConversion(c);
                    } else System.out.println("Conversion window error");
                } catch (Exception IOException)
                {
                    System.out.println("IOException in PTGUI.java");
                }
            }
        });

        tasksMenu.add(massMoleConversion);

        search = new JMenuItem("Search");
        search.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                s = new Search();
                InstanceCount cnt = new InstanceCount();
                cnt.incrementSearch();
                if (cnt.getCountConversion() == 1) s.passConversion(c);
            }
        });
        tasksMenu.add(search);

        aboutMenu = new JMenu("About");
        credits = new JMenuItem("Credits");
        credits.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                new Credit();
            }
        });
        aboutMenu.add(credits);

        menuBar.add(fileMenu);
        menuBar.add(tasksMenu);
        menuBar.add(aboutMenu);
    }
}
