package CupcakeClicker.src.GUI;

import javax.swing.*;

import CupcakeClicker.src.Game.Upgrade;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * The UpgradePane class represents a scrollable pane containing upgrade buttons.
 */
public class UpgradePane extends JScrollPane implements ActionListener {
    private JPanel upgrades;
    private UpgradeButton[] upgBTNs;

    /**
     * Constructs a new UpgradePane and initializes its components.
     */
    public UpgradePane() {

        setBackground(new Color(0, 0, 0, 0));

        upgrades = new JPanel();
        BoxLayout layout = new BoxLayout(upgrades, BoxLayout.Y_AXIS);
        upgrades.setLayout(layout);
        upgrades.setSize(500, Integer.MAX_VALUE);

        upgBTNs = new UpgradeButton[Upgrade.getUpgrades().size()];
        for (int i = 0; i < Upgrade.getUpgrades().size(); i++) {
            upgBTNs[i] = new UpgradeButton(Upgrade.getUpgrades().get(i));
            upgBTNs[i].addActionListener(this);
            upgrades.add(upgBTNs[i], JButton.CENTER);
            upgrades.setComponentZOrder(upgBTNs[i], i);
        }
        upgrades.revalidate();

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setViewportView(upgrades);
        setVisible(true);
    }

    /**
     * Buys upgrades when the corresponding buttons are clicked
     * 
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof UpgradeButton) {
            UpgradeButton upg = (UpgradeButton) e.getSource();
            upg.buy();
            repaint();
        }
    }
}
