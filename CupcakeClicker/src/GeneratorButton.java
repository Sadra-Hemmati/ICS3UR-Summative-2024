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

public class GeneratorButton extends JButton implements NeedsUpdates{
    private Generator gen;
    private JLabel icon, name, level, cost;
    
    public GeneratorButton(Generator gen) {
        this.gen = gen;
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 64));
        setLayout(new BorderLayout());
        setBackground(new Color(200, 170, 140));
        setBorder(BorderFactory.createLineBorder(new Color(210, 180, 150)));

        icon = new JLabel(new ImageIcon(gen.getIconPath()), SwingConstants.LEFT);
        icon.setBounds(0, 0, 64, 64);
        add(icon, BorderLayout.WEST);

        JPanel middle = new JPanel(new BorderLayout());
        middle.setBackground(new Color(0, 0,  0, 0));

        name = new JLabel(gen.getName(), SwingConstants.CENTER);
        name.setBounds(4, 2, 100, 20);
        middle.add(name, BorderLayout.NORTH);

        cost = new JLabel(gen.getButtonDisplayCost(), SwingConstants.CENTER) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(100,  getHeight());
            }
        };
        cost.setBounds(4, 40, 100, 20);
        middle.add(cost, BorderLayout.SOUTH);

        add(middle, BorderLayout.CENTER);
        
        level = new JLabel(Game.formatWithSuffix((double)gen.getLevel()));
        level.setBounds((int)getSize().getWidth() - 60, 2, 60, 60);
        add(level, BorderLayout.EAST);

        setVisible(true);
    }

    public Generator getGen() {
        return gen;
    }

    public void levelUp(int levels) {
        gen.levelUp(levels);
    }

    @Override
    public void update() {
        setVisible(gen.isUnlocked());
        level.setText(Game.formatWithSuffix((double)gen.getLevel()));
        cost.setText(gen.getButtonDisplayCost());
    }

}
