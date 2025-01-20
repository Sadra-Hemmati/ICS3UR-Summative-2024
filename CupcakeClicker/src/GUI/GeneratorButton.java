package CupcakeClicker.src.GUI;

import javax.swing.*;

import CupcakeClicker.src.Constants;
import CupcakeClicker.src.Game.Game;
import CupcakeClicker.src.Game.Generator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

/**
 * The GeneratorButton class represents a button for managing a generator.
 */
public class GeneratorButton extends JButton implements NeedsUpdates {
    private Generator gen;
    private JLabel icon, name, level, cost;
    private final Image cupcakeSmall = new ImageIcon("CupcakeClicker/Assets/Cupcake.png").getImage()
            .getScaledInstance(30, 30, Image.SCALE_SMOOTH);

    /**
     * Constructs a new GeneratorButton for the specified generator.
     * 
     * @param gen The generator associated with this button.
     */
    public GeneratorButton(Generator gen) {
        this.gen = gen;
        setMaximumSize(new Dimension(Constants.Dimensions.RIGHT_PANEL_WIDTH, 64));
        setLayout(new BorderLayout());
        setBackground(new Color(200, 170, 140));
        setBorder(BorderFactory.createLineBorder(new Color(210, 180, 150)));

        icon = new JLabel(new ImageIcon(gen.getIconPath()), SwingConstants.LEFT);
        icon.setBounds(0, 0, 64, 64);
        add(icon, BorderLayout.WEST);

        JPanel middle = new JPanel(new BorderLayout());
        middle.setBackground(new Color(0, 0, 0, 0));

        name = new JLabel(gen.getName() + ": " + Game.formatWithSuffix(gen.getProductionPerSecond()),
                SwingConstants.CENTER);
        name.setFont(Constants.Fonts.GENERATOR_NAME);
        name.setBounds(4, 2, 100, 20);
        middle.add(name, BorderLayout.NORTH);

        JPanel costPanel = new JPanel(new FlowLayout());
        costPanel.setBounds(4, 40, 60, 20);
        costPanel.setBackground(new Color(0, 0, 0, 0));

        cost = new JLabel(gen.getButtonDisplayCost(), SwingConstants.CENTER);
        cost.setFont(Constants.Fonts.GENERATOR_TEXT);
        cost.setSize(80, 40);

        costPanel.add(cost);
        costPanel.add(new JLabel(new ImageIcon(cupcakeSmall), SwingConstants.LEFT));

        middle.add(costPanel, BorderLayout.SOUTH);

        add(middle, BorderLayout.CENTER);

        level = new JLabel(Game.formatWithSuffix((double) gen.getLevel()));
        level.setBounds((int) getSize().getWidth() - 60, 2, 60, 60);
        level.setFont(Constants.Fonts.GENERATOR_TEXT);
        add(level, BorderLayout.EAST);

        setVisible(true);
    }

    /**
     * Returns the generator associated with this button.
     * 
     * @return Generator The generator associated with this button.
     */
    public Generator getGen() {
        return gen;
    }

    /**
     * Levels up the generator by the specified number of levels.
     * 
     * @param levels The number of levels to increase.
     */
    public void levelUp(int levels) {
        gen.levelUp(levels);
    }

    /**
     * Updates the button's display with new values.
     */
    @Override
    public void update() {
        setVisible(gen.isUnlocked());
        name.setText(gen.getName() + ": " + Game.formatWithSuffix(gen.getProductionPerSecond()) + " CPS");
        level.setText(Game.formatWithSuffix((double) gen.getLevel()));
        cost.setText(gen.getButtonDisplayCost());
        repaint();
    }

}
