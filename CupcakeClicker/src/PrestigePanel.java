package CupcakeClicker.src;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PrestigePanel extends JPanel implements NeedsUpdates, ActionListener {

    JLabel prestigeInfo;
    JButton prestigeButton;
    boolean buttonClickedOnce;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Dimensions.MIDDLE_PANEL_WIDTH, Dimensions.MIDDLE_PANEL_HEIGHT/2);
    }

    public PrestigePanel() {
        buttonClickedOnce = false;

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Prestige", SwingConstants.LEFT);
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(title, BorderLayout.NORTH);

        prestigeInfo = new JLabel(
            "<html>Prestige Cupcakes: " + Game.formatWithSuffix(Game.getPrestigeCupkakes()) +
            "<br/>Total Prestige Multiplier: " + Game.formatWithSuffix(Game.getPrestigeMultiplier()) +
            "<br/>This Prestige Multiplier: "+ Game.formatWithSuffix(Game.getPrestigeMultiplierGain()), SwingConstants.LEFT
        );
        prestigeInfo.setFont(new Font("Arial", Font.BOLD, 20));
        add(prestigeInfo, BorderLayout.CENTER);

        prestigeButton = new JButton("Prestige?");
        add(prestigeButton, BorderLayout.SOUTH);
        prestigeButton.addActionListener(this);
        
        setVisible(true);
    }

    @Override
    public void update() {
            prestigeInfo.setText(
            "<html>Prestige Cupcakes: " + Game.formatWithSuffix(Game.getPrestigeCupkakes()) +
            "<br/>Total Prestige Multiplier: " + Game.formatWithSuffix(Game.getPrestigeMultiplier()) +
            "<br/>This Prestige Multiplier: "+ Game.formatWithSuffix(Game.getPrestigeMultiplierGain())
        );
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prestigeButton) {
            if (buttonClickedOnce) {
                Game.prestige();
            }
            else{
                prestigeButton.setText("Are You Sure?");
                buttonClickedOnce = true;
                Timer mainLoop = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        prestigeButton.setText("Prestige");
                        buttonClickedOnce = false;
                    }
                });
                mainLoop.setRepeats(false);
                mainLoop.start();
            }
        }
    }
}
