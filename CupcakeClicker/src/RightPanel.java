package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

public class RightPanel extends JPanel{

    public RightPanel() {
        setLayout(new BorderLayout());
        setSize(Dimensions.MIDDLE_PANEL_WIDTH, Dimensions.MIDDLE_PANEL_HEIGHT);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBackground(Color.BLACK);

        setVisible(true);
    }
}
