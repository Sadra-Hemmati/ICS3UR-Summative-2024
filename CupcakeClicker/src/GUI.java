package CupcakeClicker.src;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

public class GUI extends JFrame implements ActionListener{
    LeftPanel leftPanel;
    JButton cupcake;
    Game game;
    long previousTimeMillis;
    long currentTimeMillis;

    public GUI(Game game){
        this.game = game;

        //periodically calculates passive income, passing in the time elapsed since the last call as a parameter
        previousTimeMillis = System.currentTimeMillis();
        Timer generatorTimer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentTimeMillis = System.currentTimeMillis();
                long delta = currentTimeMillis - previousTimeMillis;

                game.calculateIncomePerFrame(delta);
                previousTimeMillis = currentTimeMillis;

                System.out.println(game.getCupcakes());
            }
        });
        generatorTimer.start();

        //TODO Make Swing objects for each ui element in seperate classes and combine them here using a layout manager

        // JFrame setup
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(1500, 750);
        setLocationRelativeTo(null); // Center the frame on screen
        setResizable(true);
        getContentPane().setBackground(Color.GRAY);      

        // Add components
        leftPanel = new LeftPanel(game);
        add(leftPanel, BorderLayout.WEST);

        // Make frame visible
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

    }
}
