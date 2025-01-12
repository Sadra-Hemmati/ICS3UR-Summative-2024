package CupcakeClicker.src;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

public class LeftPanel extends JPanel implements ActionListener{
    JButton cupcake;
    Game game;

    public LeftPanel(Game game) {

        this.game = game;
        setLayout(new BorderLayout());
        setSize(600, 750);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        cupcake = new JButton(new ImageIcon("CupcakeClicker\\Assets\\Cupcake.png"));
        cupcake.setSize(500, 500);
        cupcake.addActionListener(this);
        add(cupcake, BorderLayout.CENTER);

        setVisible(true);
    }
   
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cupcake) {
            game.calculateIncomePerClick();
        }
    }
}
