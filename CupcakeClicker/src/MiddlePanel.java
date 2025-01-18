package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class MiddlePanel extends JPanel{
    JPanel upgradePanel, prestigePanel;

    public MiddlePanel() {
        setLayout(new BorderLayout());
        setSize(Dimensions.MIDDLE_PANEL_WIDTH, Dimensions.MIDDLE_PANEL_HEIGHT);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.BLACK);

        upgradePanel = new JPanel(new BorderLayout());
        upgradePanel.add(new JLabel("Upgrades", SwingConstants.LEFT), BorderLayout.NORTH);
        upgradePanel.add(new UpgradePane(), BorderLayout.SOUTH);


        prestigePanel = new JPanel(new BorderLayout());
        prestigePanel.add(new JLabel("Prestige", SwingConstants.LEFT), BorderLayout.NORTH);
        //prestigePanel.add( BorderLayout.SOUTH);

        add(upgradePanel, BorderLayout.NORTH);
        add(prestigePanel, BorderLayout.SOUTH);

        setVisible(true);
    }
}
