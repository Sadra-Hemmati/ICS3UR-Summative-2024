package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class MiddlePanel extends JPanel{
    GeneratorPane genPane;

    public MiddlePanel() {
        setLayout(new BorderLayout());
        setSize(Dimensions.MIDDLE_PANEL_WIDTH, Dimensions.MIDDLE_PANEL_HEIGHT);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.BLACK);

        genPane = new GeneratorPane();

        add(genPane);

        setVisible(true);
    }

    public void update() {
        genPane.update();
    }
}
