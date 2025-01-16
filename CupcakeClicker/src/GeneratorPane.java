package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class GeneratorPane extends JScrollPane implements ActionListener{
    private JPanel generators;
    private GeneratorButton[] genBTNs;

    public GeneratorPane() {
        setBackground(new Color(0, 0, 0, 0));

        generators = new JPanel();
        BoxLayout layout = new BoxLayout(generators, BoxLayout.Y_AXIS);
        generators.setLayout(layout);
        generators.setSize(500, 1000);

        genBTNs = new GeneratorButton[Game.getGenerators().length];
        for(int i = 0; i < Game.getGenerators().length; i++) {
            genBTNs[i] = new GeneratorButton(Game.getGenerators()[i]);
            genBTNs[i].addActionListener(this);
            generators.add(genBTNs[i], JButton.CENTER);
            generators.setComponentZOrder(genBTNs[i], i);
        }

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setViewportView(generators);
        setVisible(true);
    }



    public void actionPerformed(ActionEvent e){
        if (e.getSource() instanceof GeneratorButton) {
            GeneratorButton gen = (GeneratorButton) e.getSource();
            gen.levelUp(Game.getLvlsPerClick());
        }
    }
}
