package CupcakeClicker.src;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.datatransfer.FlavorListener;

public class GUI extends JFrame implements ActionListener{
    private LeftPanel leftPanel;
    private MiddlePanel middlePanel;
    private RightPanel rightPanel;
    private JButton cupcake;
    private long previousTimeMillis;
    private long currentTimeMillis;

    // @Override
    // public void paintComponents(Graphics g) {
    //     super.paintComponents(g);

    //     g.drawImage(new ImageIcon("CupcakeClicker\\Assets\\LeftPanelBg.jpg").getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT), 0, 0, this);
    // }

    public GUI() {

        //TODO Make Swing objects for each ui element in seperate classes and combine them here using a layout manager

        // JFrame setup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Cupcake Clicker");
        setLayout(new BorderLayout());
        setSize(Dimensions.DEFAULT_WIDTH, Dimensions.DEFAULT_HEIGHT);
        setLocationRelativeTo(null); // Center the frame on screen
        setResizable(false);
        getContentPane().setBackground(Color.GRAY);      

        // Add components
        leftPanel = new LeftPanel();
        add(leftPanel, BorderLayout.WEST);

        middlePanel = new MiddlePanel();
        add(middlePanel, BorderLayout.CENTER);

        rightPanel = new RightPanel();
        add(rightPanel, BorderLayout.EAST);

        // Make frame visible
        setVisible(true);

        //main game loop
        //periodically calculates passive income, passing in the time elapsed since the last call as a parameter
        previousTimeMillis = System.currentTimeMillis();
        Timer mainLoop = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent frame) {
                currentTimeMillis = System.currentTimeMillis();
                long delta = currentTimeMillis - previousTimeMillis;

                Game.calculateIncomePerFrame(delta);
                previousTimeMillis = currentTimeMillis;

                System.out.println(delta);
                update();
            }
        });
        mainLoop.start();
    }

    //calls the update method with the GUI frame as a root, uses invoke later to ensure getComponents() is ran on the EDT
    private void update(){
        SwingUtilities.invokeLater(() -> update(this));
    }

    //recursively searchs the component tree, and updates when applicable
    private void update(Container root) {
        synchronized (root.getTreeLock()) {
            for (Component component : root.getComponents()) {
                if (component instanceof NeedsUpdates) {
                    NeedsUpdates needsUpdates = (NeedsUpdates) component;
                    needsUpdates.update();
                }
                if (component instanceof Container) {
                    update((Container)component);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){

    }
}
