/**
 * GUI to search for element names or symbols
 *
 * @author David St-Pierre
 **/
package display;

import core.PT;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class Search extends JFrame
{

    private int bNums[];

    private JButton searchSym;
    private JButton searchNa;

    private JButton buttons[];

    private Conversion c;
    private Container cp;
    private Border thinBorder;

    private JTextField searchString;

    public Search()
    {
        cp = getContentPane();
        cp.setLayout(new GridLayout(12, 1));
        cp.setBackground(Color.WHITE);

        Font dFault = new Font("Mono", Font.PLAIN, 12);
        Font big = new Font("Mono", Font.BOLD, 30);
        thinBorder = new LineBorder(Color.BLACK, 2);

        JLabel title = new JLabel("SEARCH", SwingConstants.CENTER);
        title.setFont(big);
        cp.add(title);

        searchString = new JTextField("", 10);
        cp.add(searchString);

        searchSym = new JButton();
        searchSym.setForeground(Color.BLACK);
        searchSym.setBackground(Color.CYAN);
        searchSym.setBorder(thinBorder);
        searchSym.setText("Search for an Element Symbol");
        cp.add(searchSym);
        searchSym.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                searchSymbol(searchString.getText());
            }
        });

        searchNa = new JButton();
        searchNa.setForeground(Color.BLACK);
        searchNa.setBackground(Color.CYAN);
        searchNa.setBorder(thinBorder);
        searchNa.setText("Search for an Element Name");
        cp.add(searchNa);
        searchNa.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                searchName(searchString.getText());
            }
        });

        bNums = new int[8];
        buttons = new JButton[8];
        initButtons();


        setResizable(false);
        setTitle("Search");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
        setAlwaysOnTop(true);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent event)
            {
                InstanceCount cnt = new InstanceCount();
                cnt.decrementSearch();
                dispose();
            }
        });
    }

    public void passConversion(Conversion con)
    {
        c = con;
    }

    private void initButtons()
    {
        for (int i = 0; i < 8; i++)
        {
            buttons[i] = new JButton();
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setBackground(Color.YELLOW);
            buttons[i].setBorder(thinBorder);
            cp.add(buttons[i]);
            final int a = i;
            buttons[i].addActionListener(new ActionListener()
            {
                InstanceCount cnt = new InstanceCount();

                public void actionPerformed(ActionEvent e)
                {
                    if (cnt.getCountConversion() == 0)
                    {
                        if (bNums[a] == 0)
                            JOptionPane.showMessageDialog(new JFrame(), "That isn't an element", "Info", JOptionPane.INFORMATION_MESSAGE);
                        else new ElementGUI(bNums[a]);
                    } else if (cnt.getCountConversion() == 1)
                    {
                        if (bNums[a] == 0)
                            JOptionPane.showMessageDialog(new JFrame(), "That isn't an element", "Info", JOptionPane.INFORMATION_MESSAGE);
                        else c.addText(PT.getElement(bNums[a]).getSymbol());
                    } else System.out.println("Error, too many instance of conversion");
                }
            });
        }
    }

    private void searchSymbol(String str)
    {

        List<Integer> results = new ArrayList<Integer>();

        for (int i = 1; i <= 118; i++)
        {
            if (PT.getElement(i).getSymbol().contains(str)) results.add(i);
        }

        //Alphabetize
        for (int i = 0; i < results.size(); i++)
        {
            for (int j = i + 1; j < results.size(); j++)
            {
                if (PT.getElement(results.get(i)).getSymbol().toLowerCase().compareTo(PT.getElement(results.get(j)).getSymbol().toLowerCase()) > 0)
                {
                    int temp = results.get(i);
                    results.set(i, results.get(j));
                    results.set(j, temp);
                }
            }
        }

        //Display the results
        for (int i = 0; i < 8; i++)
        {
            if (i >= results.size())
            {
                bNums[i] = 0;
                buttons[i].setText("");
            } else
            {
                bNums[i] = results.get(i);
                buttons[i].setText(PT.getElement(bNums[i]).getSymbol());
            }
        }
    }

    private void searchName(String str)
    {

        List<Integer> results = new ArrayList<Integer>();

        for (int i = 1; i <= 118; i++)
        {
            if (PT.getElement(i).getName().toLowerCase().contains(str.toLowerCase())) results.add(i);
        }

        //Alphabetize
        for (int i = 0; i < results.size(); i++)
        {
            for (int j = i + 1; j < results.size(); j++)
            {
                if (PT.getElement(results.get(i)).getName().toLowerCase().compareTo(PT.getElement(results.get(j)).getName().toLowerCase()) > 0)
                {
                    int temp = results.get(i);
                    results.set(i, results.get(j));
                    results.set(j, temp);
                }
            }
        }

        //Display the results
        for (int i = 0; i < 8; i++)
        {
            if (i >= results.size())
            {
                bNums[i] = 0;
                buttons[i].setText("");
            } else
            {
                bNums[i] = results.get(i);
                buttons[i].setText(PT.getElement(bNums[i]).getName());
            }
        }
    }
}
