/**
 * Displays CreditView information
 *
 * @author David St-Pierre
 **/
package display;

import javax.swing.*;
import java.awt.*;

public class CreditView extends JFrame
{
    public CreditView()
    {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());

        Font dFault = new Font("Mono", Font.PLAIN, 12);
        Font big = new Font("Mono", Font.BOLD, 30);

        JLabel title = new JLabel("Credits");
        title.setFont(big);
        cp.add(title);

        JLabel names = new JLabel("Authors: Derek Pham and David St-Pierre");
        names.setFont(dFault);
        cp.add(names);

        JLabel date = new JLabel("Last Update: Currenly worked on");
        date.setFont(dFault);
        cp.add(date);

        JLabel use = new JLabel("<HTML><br><br><br><br><br>This code is provided as is,<br> use any and all program features<br>at your own risk<br>we do no guarantee fact accuracy.<br> Feel free to add, change,<br>or modify any of this code.<br>It is opensource!!</HTML>");
        use.setFont(dFault);
        cp.add(use);


        setResizable(false);
        setTitle("Credits");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
