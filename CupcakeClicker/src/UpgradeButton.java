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

public class UpgradeButton extends JButton{
    private Upgrade upg;
    private JLabel icon, name, descripion, cost;
    
    public UpgradeButton(Upgrade upg) {
        this.upg = upg;
        setMaximumSize(new Dimension(Dimensions.MIDDLE_PANEL_WIDTH, 64));
        setLayout(new BorderLayout());
        setBackground(new Color(200, 170, 140));
        setBorder(BorderFactory.createLineBorder(new Color(210, 180, 150)));

        icon = new JLabel(new ImageIcon(upg.getIconPath()), SwingConstants.LEFT);
        icon.setBounds(0, 0, 64, 64);
        add(icon, BorderLayout.WEST);

        JPanel middle = new JPanel(new BorderLayout());
        middle.setBackground(new Color(0, 0,  0, 0));

        name = new JLabel(upg.getName(), SwingConstants.CENTER);
        name.setSize(100, 20);
        middle.add(name, BorderLayout.NORTH);

        descripion = new JLabel(upg.getDescription(), SwingConstants.CENTER);
        descripion.setSize(100, 20);
        middle.add(descripion);

        cost = new JLabel(Game.formatWithSuffix(upg.getCost()), SwingConstants.CENTER);
        cost.setSize(100, 20);
        add(cost, BorderLayout.EAST);

        add(middle, BorderLayout.CENTER);

        setVisible(true);
    }

    public Upgrade getUpg() {
        return upg;
    }

    public void buy() {
        upg.buy();
        setVisible(!upg.isBought());
    }
}
