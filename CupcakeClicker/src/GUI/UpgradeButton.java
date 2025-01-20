package CupcakeClicker.src.GUI;

import javax.swing.*;

import CupcakeClicker.src.Constants;
import CupcakeClicker.src.Game.Game;
import CupcakeClicker.src.Game.Upgrade;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

/**
 * The UpgradeButton class represents a button for purchasing an upgrade.
 */
public class UpgradeButton extends JButton implements NeedsUpdates {
    private Upgrade upg;
    private JLabel icon, name, descripion, cost;
    private final Image cupcakeSmall = new ImageIcon("CupcakeClicker/Assets/Cupcake.png").getImage()
            .getScaledInstance(20, 20, Image.SCALE_SMOOTH);

    /**
     * Constructs a new UpgradeButton for the specified upgrade.
     * 
     * @param upg The upgrade associated with this button.
     */
    public UpgradeButton(Upgrade upg) {
        this.upg = upg;
        setMaximumSize(new Dimension(Constants.Dimensions.MIDDLE_PANEL_WIDTH, 64));
        setLayout(new BorderLayout());
        setBackground(new Color(200, 170, 140));
        setBorder(BorderFactory.createLineBorder(new Color(210, 180, 150)));

        icon = new JLabel(new ImageIcon(upg.getIconPath()), SwingConstants.LEFT);
        icon.setBounds(0, 0, 64, 64);
        add(icon, BorderLayout.WEST);

        JPanel middle = new JPanel(new BorderLayout());
        middle.setBackground(new Color(0, 0, 0, 0));

        name = new JLabel(upg.getName(), SwingConstants.CENTER);
        name.setSize(100, 20);
        name.setFont(Constants.Fonts.UPGRADE_NAME);
        middle.add(name, BorderLayout.NORTH);

        descripion = new JLabel(upg.getDescription(), SwingConstants.CENTER);
        descripion.setFont(Constants.Fonts.UPGRADE_TEXT);
        descripion.setSize(100, 20);
        middle.add(descripion);

        JPanel costPanel = new JPanel(new FlowLayout());
        costPanel.setBounds(4, 40, 60, 20);
        costPanel.setBackground(new Color(0, 0, 0, 0));

        cost = new JLabel(Game.formatWithSuffix(upg.getCost()), SwingConstants.CENTER);
        cost.setFont(Constants.Fonts.UPGRADE_TEXT);
        cost.setSize(40, 20);

        costPanel.add(cost);
        costPanel.add(new JLabel(new ImageIcon(cupcakeSmall), SwingConstants.LEFT));

        cost.setSize(100, 20);
        add(costPanel, BorderLayout.EAST);

        add(middle, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Returns the upgrade associated with this button.
     * 
     * @return Upgrade The upgrade associated with this button.
     */
    public Upgrade getUpg() {
        return upg;
    }

    /**
     * Purchases the upgrade associated with this button.
     */
    public void buy() {
        upg.buy();
    }

    /**
     * Updates the visibility of the button based on whether the upgrade is bought.
     */
    @Override
    public void update() {
        setVisible(!upg.isBought());
    }
}
