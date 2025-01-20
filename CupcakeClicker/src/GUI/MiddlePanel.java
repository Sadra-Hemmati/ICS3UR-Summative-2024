package CupcakeClicker.src.GUI;

import javax.swing.*;

import CupcakeClicker.src.Constants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

/**
 * The MiddlePanel class represents the middle section of the game's GUI, containing upgrade and prestige panels.
 */
public class MiddlePanel extends JPanel {
    JPanel upgradePanel;

    /**
     * Constructs a new MiddlePanel and initializes its components.
     */
    public MiddlePanel() {
        setLayout(new BorderLayout());
        setSize(Constants.Dimensions.MIDDLE_PANEL_WIDTH, Constants.Dimensions.MIDDLE_PANEL_HEIGHT);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.BLACK);

        upgradePanel = new JPanel(new BorderLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(Constants.Dimensions.MIDDLE_PANEL_WIDTH,
                        Constants.Dimensions.MIDDLE_PANEL_HEIGHT / 2 - 40);
            }
        };
        JLabel upgradeTitle = new JLabel("Upgrades", SwingConstants.LEFT);
        upgradeTitle.setFont(Constants.Fonts.UPGRADE_TITLE);

        upgradePanel.add(upgradeTitle, BorderLayout.NORTH);
        upgradePanel.add(new UpgradePane(), BorderLayout.CENTER);

        add(upgradePanel, BorderLayout.NORTH);
        add(new PrestigePanel(), BorderLayout.SOUTH);

        setVisible(true);
    }
}
