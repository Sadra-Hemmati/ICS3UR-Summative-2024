package CupcakeClicker.src;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.security.AlgorithmConstraints;
import java.util.Arrays;
import java.util.Comparator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

public class GeneratorPane extends JScrollPane implements ActionListener{
    private JPanel generators;
    private GeneratorButton[] genBTNs;

    private enum SortMode {
        DEFAULT("Normal", Comparator.comparingDouble(btn -> btn.getGen().getUnlockThreshold())),
        COST("Cost", Comparator.comparingDouble(btn -> btn.getGen().getCost())),
        //TODO test more
        ALPHABETICAL("Alphabetical", new Comparator<GeneratorButton>() {
            @Override
            public int compare(GeneratorButton o1, GeneratorButton o2) {
                return o1.getGen().getName().compareTo(o2.getGen().getName());
            };
        });
    
        private final String displayName;
        private final Comparator<GeneratorButton> key;
    
        SortMode(String displayName, Comparator<GeneratorButton> key) {
            this.displayName = displayName;
            this.key = key;
        }
    
        public String getDisplayName() {
            return displayName;
        }

        public Comparator<GeneratorButton> getKey() {
            return key;
        }
    }
    
    SortMode sortMode = SortMode.DEFAULT;

    public GeneratorPane() {

        setBackground(new Color(0, 0, 0, 0));

        generators = new JPanel();
        BoxLayout layout = new BoxLayout(generators, BoxLayout.Y_AXIS);
        generators.setLayout(layout);
        generators.setSize(500, 1000);

        genBTNs = new GeneratorButton[Game.getGenerators().length];
        for(int i = 0; i < Game.getGenerators().length; i++) {
            genBTNs[i] = new GeneratorButton(Game.getGenerators()[i]);
            genBTNs[i].addActionListener(this);
            generators.add(genBTNs[i], JButton.CENTER);
            generators.setComponentZOrder(genBTNs[i], i);
        }
        generators.revalidate();

        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        setViewportView(generators);
        setVisible(true);
    }

    public void toggleSort() {
        SortMode[] modes = SortMode.values();
        int nextIndex = (sortMode.ordinal() + 1) % modes.length;
        sortMode = modes[nextIndex];
    }


    //performs insertion sort on the genBTNs array, using the supplied comparator as a key to compare values
    private void resortWithKey(Comparator<GeneratorButton> c) {
        GeneratorButton temp;
        for (int i = 1; i < genBTNs.length; i++) {
            for (int j = i; j > 0; j--) {
                if (c.compare(genBTNs[j], genBTNs[j-1]) < 0) {
                    temp = genBTNs[j];
                    genBTNs[j] = genBTNs[j-1];
                    genBTNs[j-1] = temp;
                }
                else {
                    break;
                }
            }
        }
        
        updateButtonOrder();
    }
    
    public void resort() {
        resortWithKey(sortMode.getKey());
    }

    private void updateButtonOrder() {
        for (int i = 0; i < genBTNs.length; i++) {
            generators.setComponentZOrder(genBTNs[i], i);
        }
        generators.revalidate();
        generators.repaint();
    }

    public String getSortModeString() {
        return sortMode.getDisplayName();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() instanceof GeneratorButton) {
            GeneratorButton gen = (GeneratorButton) e.getSource();
            gen.levelUp(Game.getLvlsPerClick());
            resort();
        }
    }
}
