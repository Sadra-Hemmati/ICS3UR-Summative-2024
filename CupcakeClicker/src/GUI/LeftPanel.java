package CupcakeClicker.src.GUI;

import javax.swing.*;
import CupcakeClicker.src.Constants;
import CupcakeClicker.src.Game.Game;

import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

/**
 * The LeftPanel class represents the left section of the game's GUI, containing the main cupcake button and stats.
 */
public class LeftPanel extends JPanel implements ActionListener, NeedsUpdates {
    private JButton cupcakeButton;
    private JLabel cupcakes, cupcakesPerSecond, cupcakesPerClick;

    /**
     * Paints the background image of the panel.
     * 
     * @param g The Graphics object to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the background image.
        g.drawImage(new ImageIcon("CupcakeClicker/Assets/LeftPanelBg.jpg").getImage(), 0, 0, this);
    }

    /**
     * Constructs a new LeftPanel and initializes its components.
     */
    public LeftPanel() {

        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Constants.Dimensions.LEFT_PANEL_WIDTH, Constants.Dimensions.LEFT_PANEL_HEIGHT));
        setBackground(new Color(0, 0, 0, 0));

        // Cupcake button
        Image cupcake = new ImageIcon("CupcakeClicker/Assets/Cupcake.png").getImage();

        cupcakeButton = new JButton(new ImageIcon(cupcake));
        cupcakeButton.setRolloverIcon(new ImageIcon(cupcake.getScaledInstance(530, 530, Image.SCALE_SMOOTH)));
        cupcakeButton.setSize(300, 300);
        cupcakeButton.setOpaque(false);
        cupcakeButton.setContentAreaFilled(false);
        cupcakeButton.setBorder(null);
        cupcakeButton.setFocusPainted(false);
        cupcakeButton.addActionListener(this);
        add(cupcakeButton, BorderLayout.CENTER);

        // cupcakes and CPS display

        cupcakes = new JLabel("Cupcakes: " + Game.getCupckakesString(), SwingConstants.CENTER);
        cupcakes.setFont(Constants.Fonts.LEFT_PANEL);
        cupcakes.setSize(100, 20);
        add(cupcakes, BorderLayout.NORTH);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        bottomPanel.setAlignmentX(SwingConstants.CENTER);
        bottomPanel.setOpaque(false);

        cupcakesPerSecond = new JLabel("CPS: " + Game.getCupckakesPerSecondString(), SwingConstants.CENTER);
        cupcakesPerSecond.setFont(Constants.Fonts.LEFT_PANEL);
        cupcakesPerSecond.setSize(100, 20);
        bottomPanel.add(cupcakesPerSecond, BorderLayout.WEST);

        cupcakesPerClick = new JLabel("CPC: " + Game.getCupckakesPerClickString(), SwingConstants.CENTER);
        cupcakesPerClick.setFont(Constants.Fonts.LEFT_PANEL);
        cupcakesPerClick.setSize(100, 20);
        bottomPanel.add(cupcakesPerClick, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Updates the cupcake-related stats displayed on the panel.
     */
    @Override
    public void update() {
        cupcakes.setText("Cupcakes: " + Game.getCupckakesString());
        cupcakesPerSecond.setText("CPS: " + Game.getCupckakesPerSecondString());
        cupcakesPerClick.setText("CPC: " + Game.getCupckakesPerClickString());
    }

    /**
     * Generates cupcakes when the cupcake button is clicked.
     * 
     * @param e The action event.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cupcakeButton) {
            Game.calculateIncomePerClick();
        }
    }
}
