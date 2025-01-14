package CupcakeClicker.src;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.security.AlgorithmConstraints;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class GeneratorButton extends JButton{
    Generator gen;
    JLabel icon, name, level, cost;
    
    public GeneratorButton(Generator gen) {
        this.gen = gen;
        setMaximumSize(new Dimension(Dimensions.RIGHT_PANEL_WIDTH, 64));
        setLayout(new BorderLayout());
        setBackground(new Color(200, 170, 140));

        icon = new JLabel(new ImageIcon(gen.getIconPath()), SwingConstants.LEFT);
        icon.setBounds(0, 0, 64, 64);
        add(icon, BorderLayout.WEST);

        name = new JLabel(gen.getName(), SwingConstants.CENTER);
        name.setBounds(68, 2, 100, 20);
        add(name, BorderLayout.NORTH);

        cost = new JLabel(String.valueOf(gen.getCost()), SwingConstants.CENTER);
        cost.setBounds(68, 40, 100, 20);
        add(cost, BorderLayout.SOUTH);

        level = new JLabel(String.valueOf(gen.getLevel()));
        level.setBounds((int)getSize().getWidth() - 60, 2, 60, 60);
        add(level, BorderLayout.EAST);

        setVisible(gen.isUnlocked());
    }

    public Generator getGen() {
        return gen;
    }

    public void levelUp(int levels) {
        gen.levelUp(levels);
    }

    public void update() {
        setVisible(gen.isUnlocked());
        level.setText(String.valueOf(gen.getLevel()));
        cost.setText(String.valueOf(gen.getCost()));
    }

}
