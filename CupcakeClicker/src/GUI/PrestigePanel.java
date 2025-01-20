package CupcakeClicker.src.GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import CupcakeClicker.src.Constants;
import CupcakeClicker.src.Game.Game;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The PrestigePanel class represents the panel that displays prestige-related information and actions.
 */
public class PrestigePanel extends JPanel implements NeedsUpdates, ActionListener {

    JLabel prestigeCupcakes, prestigeMultiplier, thisPrestigeMultiplier;
    JButton prestigeButton;
    boolean buttonClickedOnce;

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.Dimensions.MIDDLE_PANEL_WIDTH, Constants.Dimensions.MIDDLE_PANEL_HEIGHT / 2);
    }

    /**
     * Constructs a new PrestigePanel and initializes its components.
     */
    public PrestigePanel() {
        buttonClickedOnce = false;

        setLayout(new BorderLayout());
        JLabel title = new JLabel("Prestige", SwingConstants.LEFT);
        title.setFont(Constants.Fonts.PRESTIGE_TITLE);
        title.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(title, BorderLayout.NORTH);

        JPanel prestigeInfo = new JPanel();
        prestigeInfo.setLayout(new BoxLayout(prestigeInfo, BoxLayout.Y_AXIS));

        JPanel prestigeCupcakesPanel = new JPanel(new BorderLayout());
        JLabel prestigeCupcakesTitle = new JLabel("Prestige Cupcakes: ", SwingConstants.LEFT);
        prestigeCupcakesTitle.setFont(Constants.Fonts.PRESTIGE_TEXT);
        prestigeCupcakes = new JLabel(Game.formatWithSuffix(Game.getPrestigeCupkakes()), SwingConstants.CENTER);
        prestigeCupcakes.setFont(Constants.Fonts.PRESTIGE_TEXT);
        prestigeCupcakesPanel.add(prestigeCupcakesTitle, BorderLayout.NORTH);
        prestigeCupcakesPanel.add(prestigeCupcakes, BorderLayout.CENTER);

        JPanel prestigeMultiplierPanel = new JPanel(new BorderLayout());
        JLabel prestigeMultiplierTitle = new JLabel("Total Prestige Multiplier: ", SwingConstants.LEFT);
        prestigeMultiplierTitle.setFont(Constants.Fonts.PRESTIGE_TEXT);
        prestigeMultiplier = new JLabel(Game.formatWithSuffix(Game.getPrestigeMultiplier()), SwingConstants.CENTER);
        prestigeMultiplier.setFont(Constants.Fonts.PRESTIGE_TEXT);
        prestigeMultiplierPanel.add(prestigeMultiplierTitle, BorderLayout.NORTH);
        prestigeMultiplierPanel.add(prestigeMultiplier, BorderLayout.CENTER);

        JPanel thisPrestigeMultiplierPanel = new JPanel(new BorderLayout());
        JLabel thisPrestigeMultiplierTitle = new JLabel("This Prestige Multiplier: ", SwingConstants.LEFT);
        thisPrestigeMultiplierTitle.setFont(Constants.Fonts.PRESTIGE_TEXT);
        thisPrestigeMultiplier = new JLabel(Game.formatWithSuffix(Game.getPrestigeMultiplierGain()),
                SwingConstants.CENTER);
        thisPrestigeMultiplier.setFont(Constants.Fonts.PRESTIGE_TEXT);
        thisPrestigeMultiplierPanel.add(thisPrestigeMultiplierTitle, BorderLayout.NORTH);
        thisPrestigeMultiplierPanel.add(thisPrestigeMultiplier, BorderLayout.CENTER);

        prestigeInfo.add(prestigeCupcakesPanel, BorderLayout.NORTH);
        prestigeInfo.add(prestigeMultiplierPanel, BorderLayout.CENTER);
        prestigeInfo.add(thisPrestigeMultiplierPanel, BorderLayout.SOUTH);

        add(prestigeInfo, BorderLayout.CENTER);

        prestigeButton = new JButton("Prestige?");
        prestigeButton.setFont(Constants.Fonts.BUTTON_FONT);
        add(prestigeButton, BorderLayout.SOUTH);
        prestigeButton.addActionListener(this);

        setVisible(true);
    }

    /**
     * Updates the prestige-related information displayed on the panel.
     */
    @Override
    public void update() {
        prestigeCupcakes.setText(Game.formatWithSuffix(Game.getPrestigeCupkakes()));
        prestigeMultiplier.setText(Game.formatWithSuffix(Game.getPrestigeMultiplier()) + "x");
        thisPrestigeMultiplier.setText(Game.formatWithSuffix(Game.getPrestigeMultiplierGain()) + "x");
    }

    /**
     * Handles Prestige confirmation when the Prestige button is clicked. If the button is clicked twice within 5 seconds, triggers a prestige
     * 
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prestigeButton) {
            if (buttonClickedOnce) {
                Game.prestige();
            } else {
                prestigeButton.setText("Are You Sure?");
                prestigeButton.setFont(Constants.Fonts.BUTTON_FONT_BOLD);
                buttonClickedOnce = true;
                Timer mainLoop = new Timer(3000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        prestigeButton.setText("Prestige");
                        prestigeButton.setFont(Constants.Fonts.BUTTON_FONT);
                        buttonClickedOnce = false;
                    }
                });
                mainLoop.setRepeats(false);
                mainLoop.start();
            }
        }
    }
}
