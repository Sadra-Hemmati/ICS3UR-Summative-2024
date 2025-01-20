package CupcakeClicker.src.GUI;

import javax.swing.*;

import CupcakeClicker.src.Constants;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

/**
 * The RightPanel class represents the right section of the game's GUI, containing generator-related controls.
 */
public class RightPanel extends JPanel implements ActionListener {
    private GeneratorPane genPane;
    private JPanel titlePanel;
    private JLabel title;
    private JButton toggleBuy, toggleSort;

    /**
     * Constructs a new RightPanel and initializes its components.
     */
    public RightPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(210, 180, 150));

        genPane = new GeneratorPane();

        titlePanel = new JPanel(new BorderLayout());

        title = new JLabel("Generators");
        title.setFont(Constants.Fonts.GENERATOR_TITLE);

        toggleBuy = new JButton(String.valueOf(GeneratorPane.getLvlsPerClick()));
        toggleBuy.setFont(Constants.Fonts.BUTTON_FONT);
        toggleBuy.addActionListener(this);

        toggleSort = new JButton(genPane.getSortModeString());
        toggleSort.setFont(Constants.Fonts.BUTTON_FONT);
        toggleSort.addActionListener(this);

        titlePanel.add(title, BorderLayout.WEST);
        titlePanel.add(toggleBuy, BorderLayout.EAST);

        add(titlePanel, BorderLayout.NORTH);
        add(genPane, BorderLayout.CENTER);
        add(toggleSort, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Handle toggling between various buy modes and sorting modes.
     * 
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleBuy) {
            GeneratorPane.toggleLvlsPerClick();
            if (GeneratorPane.getLvlsPerClick() == -1) {
                toggleBuy.setText("Max");
            } else {
                toggleBuy.setText(String.valueOf(GeneratorPane.getLvlsPerClick()));
            }
        }
        if (e.getSource() == toggleSort) {
            genPane.toggleSort();
            genPane.resort();
            toggleSort.setText(genPane.getSortModeString());
        }
    }

    /**
     * Returns the preferred size of the panel.
     * 
     * @return Dimension The preferred size of the panel.
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Constants.Dimensions.RIGHT_PANEL_WIDTH, Constants.Dimensions.RIGHT_PANEL_HEIGHT);
    }

}
