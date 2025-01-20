package CupcakeClicker.src.GUI;

import javax.swing.*;

import CupcakeClicker.src.Constants;
import CupcakeClicker.src.Game.Game;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

/**
 * The GUI class represents the main graphical user interface for the Cupcake Clicker game.
 */
public class GUI extends JFrame {
    private LeftPanel leftPanel;
    private MiddlePanel middlePanel;
    private RightPanel rightPanel;
    private long previousTimeMillis;
    private long currentTimeMillis;
    private WindowListener windowListener;

    /**
     * Constructs a new GUI and initializes its components.
     */
    public GUI() {

        windowListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Game.saveToTXT();
                System.exit(0);
            }
        };

        addWindowListener(windowListener);

        // JFrame setup
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setTitle("Cupcake Clicker");
        setLayout(new BorderLayout());
        setSize(Constants.Dimensions.DEFAULT_WIDTH, Constants.Dimensions.DEFAULT_HEIGHT);
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

        // main game loop
        // periodically calculates passive income, passing in the time elapsed since the
        // last call as a parameter
        previousTimeMillis = System.currentTimeMillis();
        Timer mainLoop = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent frame) {
                currentTimeMillis = System.currentTimeMillis();
                long delta = currentTimeMillis - previousTimeMillis;

                Game.calculateIncomePerFrame(delta);
                previousTimeMillis = currentTimeMillis;

                update();
            }
        });
        mainLoop.start();
    }

    /**
     * Updates the GUI components on the EDT
     */
    private void update() {
        SwingUtilities.invokeLater(() -> update(this));
        revalidate();
        repaint();
    }

    /**
     * Recursively searches the component tree and updates components that implement the NeedsUpdates interface.
     * 
     * @param root The root container to start the search from.
     */
    private void update(Container root) {
        synchronized (root.getTreeLock()) {
            for (Component component : root.getComponents()) {
                if (component instanceof NeedsUpdates) {
                    NeedsUpdates needsUpdates = (NeedsUpdates) component;
                    needsUpdates.update();
                }
                if (component instanceof Container) {
                    update((Container) component);
                }
            }
        }
    }
}
