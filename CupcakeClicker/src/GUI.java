package CupcakeClicker.src;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class GUI extends JFrame implements ActionListener{
    LeftPanel leftPanel;
    MiddlePanel middlePanel;
    RightPanel rightPanel;
    JButton cupcake;
    long previousTimeMillis;
    long currentTimeMillis;

    public GUI() {

        //TODO Make Swing objects for each ui element in seperate classes and combine them here using a layout manager

        // JFrame setup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(Dimensions.DEFAULT_WIDTH, Dimensions.DEFAULT_HEIGHT);
        setLocationRelativeTo(null); // Center the frame on screen
        setResizable(true);
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

                updateLabels();
            }
        });
        mainLoop.start();
    }

    private void updateLabels() {
        leftPanel.update();
        middlePanel.update();
    }

    public void actionPerformed(ActionEvent e){

    }
}
