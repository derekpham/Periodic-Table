/**
 * GUI to show element specifics
 *
 * @author David St-Pierre
 **/
package display;

import core.PT;

import javax.swing.*;
import java.awt.*;

public class ElementGUI extends JFrame
{
    public ElementGUI(int elementNum)
    {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 4 - this.getSize().width / 2, dim.height / 4 - this.getSize().height / 2);
        //Container and layout
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(7, 1));
        cp.setBackground(Color.WHITE);

        String elementType = PT.getElement(elementNum).getFamily();
        Color eColor;
        if (elementType.equals("Alkali")) eColor = Color.YELLOW;
        else if (elementType.equals("Noble Gas")) eColor = Color.RED;
        else if (elementType.equals("Alkaline Earth")) eColor = Color.ORANGE;
        else if (elementType.equals("Metalloid")) eColor = Color.GREEN;
        else if (elementType.equals("Non Metal")) eColor = Color.BLUE;
        else if (elementType.equals("Halogen")) eColor = Color.PINK;
        else if (elementType.equals("Transition")) eColor = Color.MAGENTA;
        else eColor = Color.BLACK;       //Error color
        //cp.setBackground(eColor);

        Font dFault = new Font("Mono", Font.PLAIN, 15);
        Font big = new Font("Mono", Font.BOLD, 30);

        JLabel title = new JLabel(PT.getElement(elementNum).getName(), SwingConstants.CENTER);
        title.setForeground(eColor);
        title.setFont(big);
        cp.add(title);

        JLabel symbol = new JLabel("Symbol: " + PT.getElement(elementNum).getSymbol(), SwingConstants.CENTER);
        symbol.setFont(dFault);
        symbol.setForeground(Color.BLACK);
        cp.add(symbol);

        JLabel atomicNum = new JLabel("Atomic Num: " + elementNum, SwingConstants.CENTER);
        atomicNum.setFont(dFault);
        atomicNum.setForeground(Color.BLACK);
        cp.add(atomicNum);

        JLabel atomicMass = new JLabel("Atomic Mass: " + PT.getElement(elementNum).getAtomicMass(), SwingConstants.CENTER);
        atomicMass.setFont(dFault);
        atomicMass.setForeground(Color.BLACK);
        cp.add(atomicMass);

        JLabel type = new JLabel("Type: " + PT.getElement(elementNum).getType(), SwingConstants.CENTER);
        type.setFont(dFault);
        type.setForeground(Color.BLACK);
        cp.add(type);

        JLabel family = new JLabel("Family: " + PT.getElement(elementNum).getFamily(), SwingConstants.CENTER);
        family.setFont(dFault);
        family.setForeground(Color.BLACK);
        cp.add(family);

        setSize(300, 400);
        setResizable(false);
        setTitle(PT.getElement(elementNum).getName());
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}







