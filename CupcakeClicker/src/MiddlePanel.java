package CupcakeClicker.src;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

public class MiddlePanel extends JPanel{
    JPanel upgradePanel;

    public MiddlePanel() {
        setLayout(new BorderLayout());
        setSize(Dimensions.MIDDLE_PANEL_WIDTH, Dimensions.MIDDLE_PANEL_HEIGHT);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.BLACK);

        upgradePanel = new JPanel(new BorderLayout()){
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(Dimensions.MIDDLE_PANEL_WIDTH, Dimensions.MIDDLE_PANEL_HEIGHT/2-40);
            }
        };
        upgradePanel.add(new JLabel("Upgrades", SwingConstants.LEFT), BorderLayout.NORTH);
        upgradePanel.add(new UpgradePane(), BorderLayout.CENTER);


        add(upgradePanel, BorderLayout.NORTH);
        add(new PrestigePanel(), BorderLayout.SOUTH);

        setVisible(true);
    }
}
