/**
 * ConversionView stuff
 * Need to corporate this into PeriodicTableView and somehow
 * makes use of the buttons in PeriodicTableView to put in chemical formula
 *
 * @author Derek Pham, David St-Pierre
 **/
package display;

import core.ChemicalFormula;
import core.PT;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.DecimalFormat;

public class ConversionView extends JFrame
{
    private Container cp;
    private JLabel formLabel;
    private JTextField formText;
    private JButton set;
    private ChemicalFormula formula;
    private JButton buttons[];
    private JLabel moleLabel;
    private JTextField moleText;
    private JLabel massLabel;
    private JTextField massText;

    private JCheckBox checkBox = new JCheckBox("View Periodic Table"); // an abomination right now lol

    private boolean view = false;    //Default is to not see the periodic table

    public ConversionView() throws IOException
    {
        cp = getContentPane();
        cp.setLayout(new FlowLayout());

        formLabel = new JLabel("Chemical Formula");
        formText = new JTextField("", 10);
        add(formLabel);
        add(formText);

        massLabel = new JLabel("Mass (g)");
        massText = new JTextField("", 10);
        add(massLabel);
        add(massText);

        // TODO: make this button and can reverse when clicked
        add(new JLabel("\u25BA"));

        moleLabel = new JLabel("Mole (mol)");
        moleText = new JTextField("", 10);
        add(moleLabel);
        add(moleText);
        set = new JButton("Calculate");
        add(set);
        // TODO: move this to the PT library instead here
        set.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    DecimalFormat df = new DecimalFormat("0.00");
                    ChemicalFormula form = ChemicalFormula.makeFormula(formText.getText());
                    // add another try catch for parseDouble too?
                    double mass = Double.parseDouble(massText.getText());
                    double mol = mass / form.getMass();
                    String outputMole = df.format(mol);
                    moleText.setText(outputMole);
                } catch (Exception ex)
                {
                    System.err.println("Error");
                    System.err.println(ex.toString());
                }

            }
        });

        //Add checkbox
        checkBox.setLocation(100, 100);
        add(checkBox);

        checkBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                view = !view;
                buttons = new JButton[118];
                cp.setLayout(null);
                try
                {
                    viewTable();
                } catch (Exception IOException)
                {
                    System.out.println("IOExcetipion in ConversionView.java");
                }
            }
        });

        // right now it is actually just mole-mass conversion
        setTitle("Unit ConversionView");
        setSize(1000, 200);
        setResizable(true);
        setVisible(true);
        setAlwaysOnTop(true);
        //setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent event)
            {
                InstanceCount cnt = new InstanceCount();
                cnt.decrementConversion();
                dispose();
            }
        });
    }

    // just for testing
    public static void main(String[] args) throws IOException
    {
        new ConversionView();
    }

    //Makes mini-table visible based on radio box
    private void viewTable() throws IOException
    {
        if (view == true)
        {
            setSize(800, 300);
            for (int i = 1; i <= buttons.length; i++)
            {
                buttons[i - 1] = new JButton();
                buttons[i - 1].setForeground(Color.BLACK);

                //Set the color of the button to it's family
                String elementType = PT.getElement(i).getFamily();
                if (elementType.equals("Alkali")) buttons[i - 1].setBackground(Color.YELLOW);
                else if (elementType.equals("Noble Gas")) buttons[i - 1].setBackground(Color.RED);
                else if (elementType.equals("Alkaline Earth")) buttons[i - 1].setBackground(Color.ORANGE);
                else if (elementType.equals("Metalloid")) buttons[i - 1].setBackground(Color.GREEN);
                else if (elementType.equals("Non Metal")) buttons[i - 1].setBackground(Color.BLUE);
                else if (elementType.equals("Halogen")) buttons[i - 1].setBackground(Color.PINK);
                else if (elementType.equals("Transition")) buttons[i - 1].setBackground(Color.MAGENTA);
                else buttons[i - 1].setBackground(Color.BLACK);       //Error color

                buttons[i - 1].setMargin(new Insets(0, 0, 0, 0));
                buttons[i - 1].setFont(new Font("Mono", Font.PLAIN, 10));
                buttons[i - 1].setText(PT.getElement(i).getSymbol());

                //Set button border
                Border thinBorder = new LineBorder(Color.BLACK, 2);
                buttons[i - 1].setBorder(thinBorder);

                //Add the buttons to the content pane
                cp.add(buttons[i - 1]);


                //Create an action listener for each button
                final int a = i;
                buttons[i - 1].addActionListener(new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e)
                    {
                        addText(PT.getElement(a).getSymbol());
                    }
                });
            }

            //Place element buttons in specific locations
            ElementLocation loc = new ElementLocation();
            for (int i = 0; i < 118; i++)
            {
                buttons[i].setBounds(loc.getX(i) / 2, loc.getY(i) / 2 + 40, 24, 24);
            }
        } else
        {
            setSize(800, 40);
        }
        viewExtraButtons();
    }

    private void viewExtraButtons()
    {
        JButton extraButtons[] = new JButton[2];
        JButton numButtons[] = new JButton[10];
        for (int i = 0; i < extraButtons.length; i++)
        {
            extraButtons[i] = new JButton(i + "(");
            extraButtons[i].setBackground(Color.lightGray);
            extraButtons[i].setMargin(new Insets(0, 0, 0, 0));
            extraButtons[i].setFont(new Font("Mono", Font.PLAIN, 10));
            cp.add(extraButtons[i]);
        }

        extraButtons[0].setText("(");
        extraButtons[0].setBounds(520, 210, 30, 30);
        extraButtons[1].setText(")");
        extraButtons[1].setBounds(560, 210, 30, 30);

        for (int i = 0; i < extraButtons.length; i++)
        {
            final String a = extraButtons[i].getText();
            extraButtons[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    addText(a);
                }
            });
        }

        for (int i = 0; i < numButtons.length; i++)
        {
            numButtons[i] = new JButton(i + "");
            numButtons[i].setBackground(Color.lightGray);
            numButtons[i].setMargin(new Insets(0, 0, 0, 0));
            numButtons[i].setFont(new Font("Mono", Font.PLAIN, 10));
            final int a = i;
            numButtons[i].addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    addText(a + "");
                }
            });
            cp.add(numButtons[i]);
        }
        numButtons[1].setBounds(500, 50, 30, 30);
        numButtons[2].setBounds(540, 50, 30, 30);
        numButtons[3].setBounds(580, 50, 30, 30);
        numButtons[4].setBounds(500, 90, 30, 30);
        numButtons[5].setBounds(540, 90, 30, 30);
        numButtons[6].setBounds(580, 90, 30, 30);
        numButtons[7].setBounds(500, 130, 30, 30);
        numButtons[8].setBounds(540, 130, 30, 30);
        numButtons[9].setBounds(580, 130, 30, 30);
        numButtons[0].setBounds(540, 170, 30, 30);

    }

    //Adds text to the textField
    public void addText(String text)
    {
        formText.setText(formText.getText() + text);
    }

    // use regex here
    private ChemicalFormula makeFormula(String name)
    {
        return null;
    }
}
