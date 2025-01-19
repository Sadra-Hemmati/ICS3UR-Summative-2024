package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

public class RightPanel extends JPanel implements ActionListener{
    private GeneratorPane genPane;
    private JPanel titlePanel;
    private JLabel title;
    private JButton toggleBuy, toggleSort;

    public RightPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(210, 180, 150));

        genPane = new GeneratorPane();

        titlePanel = new JPanel(new BorderLayout());

        title = new JLabel("Generators");
        
        toggleBuy = new JButton(String.valueOf(GeneratorPane.getLvlsPerClick()));
        toggleBuy.addActionListener(this);

        toggleSort = new JButton(genPane.getSortModeString());
        toggleSort.addActionListener(this);

        titlePanel.add(title, BorderLayout.WEST);
        titlePanel.add(toggleBuy, BorderLayout.EAST);

        add(titlePanel, BorderLayout.NORTH);
        add(genPane, BorderLayout.CENTER);
        add(toggleSort, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleBuy) {
            GeneratorPane.toggleLvlsPerClick();
            if (GeneratorPane.getLvlsPerClick() == -1) {
                toggleBuy.setText("Max");
            }
            else {
                toggleBuy.setText(String.valueOf(GeneratorPane.getLvlsPerClick()));
            }
        }
        if (e.getSource() == toggleSort) {
            genPane.toggleSort();
            genPane.resort();
            toggleSort.setText(genPane.getSortModeString());
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Dimensions.RIGHT_PANEL_WIDTH, Dimensions.RIGHT_PANEL_HEIGHT);
    }

}
