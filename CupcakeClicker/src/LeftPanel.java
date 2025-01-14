package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

public class LeftPanel extends JPanel implements ActionListener{
    private JButton cupcakeButton;
    private JLabel cupcakes, cupcakesPerSecond;

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    
        // Draw the background image.
        g.drawImage(new ImageIcon("CupcakeClicker\\Assets\\LeftPanelBg.jpg").getImage(), 0, 0, this);
    }

    public LeftPanel() {

        setLayout(new BorderLayout());
        setSize(Dimensions.LEFT_PANEL_WIDTH, Dimensions.LEFT_PANEL_HEIGHT);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Cupcake button
        Image cupcake = new ImageIcon("CupcakeClicker\\Assets\\Cupcake.png").getImage();

        cupcakeButton = new JButton(new ImageIcon(cupcake));
        cupcakeButton.setRolloverIcon(new ImageIcon(cupcake.getScaledInstance(530, 530, Image.SCALE_DEFAULT)));
        cupcakeButton.setSize(300, 300);
        cupcakeButton.setOpaque(false);
        cupcakeButton.setContentAreaFilled(false);
        cupcakeButton.setBorder(null);
        cupcakeButton.setFocusPainted(false);
        cupcakeButton.addActionListener(this);
        add(cupcakeButton, BorderLayout.CENTER);

        //cupcakes and CPS display
        Font labelFont = new Font("Arial", Font.BOLD, 50);

        cupcakes = new JLabel("Cupcakes: " + String.format("%.2f", Game.getCupcakes()), SwingConstants.CENTER);
        cupcakes.setFont(labelFont);
        cupcakes.setSize(100, 20);
        add(cupcakes, BorderLayout.NORTH);

        cupcakesPerSecond = new JLabel("CPS: " + String.format("%.2f", Game.getCupcakesPerSecond()), SwingConstants.CENTER);
        cupcakesPerSecond.setFont(labelFont);
        cupcakesPerSecond.setSize(100, 20);
        add(cupcakesPerSecond, BorderLayout.SOUTH);

        setVisible(true);
    }
   
    public void update() {
        cupcakes.setText("Cupcakes: " + String.format("%.2f", Game.getCupcakes()));
        cupcakesPerSecond.setText("CPS: " + String.format("%.2f", Game.getCupcakesPerSecond()));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cupcakeButton) {
            Game.calculateIncomePerClick();
        }
    }
}
