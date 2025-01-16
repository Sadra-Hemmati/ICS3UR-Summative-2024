package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class RightPanel extends JPanel implements ActionListener{
    private GeneratorPane genPane;
    private JPanel titlePanel;
    private JLabel title;
    private JButton toggleBuy;
    private JButton multiBuyToggle;

    public RightPanel() {
        setLayout(new BorderLayout());
        setBackground(new Color(210, 180, 150));

        titlePanel = new JPanel(new BorderLayout());

        title = new JLabel("Generators");
        
        toggleBuy = new JButton(String.valueOf(Game.getLvlsPerClick()));
        toggleBuy.addActionListener(this);

        titlePanel.add(title, BorderLayout.WEST);
        titlePanel.add(toggleBuy, BorderLayout.EAST);

        genPane = new GeneratorPane();

        add(titlePanel, BorderLayout.NORTH);
        add(genPane, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toggleBuy) {
            Game.toggleLvlsPerClick();
            if (Game.getLvlsPerClick() == -1) {
                toggleBuy.setText("Max");
            }
            else {
                toggleBuy.setText(String.valueOf(Game.getLvlsPerClick()));
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(Dimensions.RIGHT_PANEL_WIDTH, Dimensions.RIGHT_PANEL_HEIGHT);
    }

}
