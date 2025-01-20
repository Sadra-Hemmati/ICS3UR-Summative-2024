package CupcakeClicker.src.GUI;

import javax.swing.*;

import CupcakeClicker.src.Game.Generator;

import java.awt.event.ActionListener;
import java.util.Comparator;
import java.awt.Color;
import java.awt.event.ActionEvent;

/**
 * The GeneratorPane class represents a scrollable pane containing generator buttons.
 */
public class GeneratorPane extends JScrollPane implements ActionListener {
    private JPanel generators;
    private GeneratorButton[] genBTNs;
    private static int LvlsPerClickIndex;
    private static int[] LvlsPerClickOptions = { 1, 10, 50, -1 }; // -1 indicates Max lvls

    private static enum SortMode {
        DEFAULT("Normal", Comparator.comparingDouble(btn -> btn.getGen().getUnlockThreshold())),
        COST("Cost", Comparator.comparingDouble(btn -> btn.getGen().getCost())),
        // TODO test alpgabetical sort with more complex names
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

    /**
     * Constructs a new GeneratorPane and initializes its components.
     */
    public GeneratorPane() {
        LvlsPerClickIndex = 0;

        setBackground(new Color(0, 0, 0, 0));

        generators = new JPanel();
        BoxLayout layout = new BoxLayout(generators, BoxLayout.Y_AXIS);
        generators.setLayout(layout);
        generators.setSize(500, Integer.MAX_VALUE);

        genBTNs = new GeneratorButton[Generator.getGenerators().size()];
        for (int i = 0; i < Generator.getGenerators().size(); i++) {
            genBTNs[i] = new GeneratorButton(Generator.getGenerators().get(i));
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

    /**
     * Toggles the sorting mode of the generator buttons.
     */
    public void toggleSort() {
        SortMode[] modes = SortMode.values();
        int nextIndex = (sortMode.ordinal() + 1) % modes.length;
        sortMode = modes[nextIndex];
    }

    /**
     * Performs insertion sort on the genBTNs array using the supplied comparator as a key to compare values.
     * 
     * @param c The comparator to use for sorting.
     */
    private void resortWithKey(Comparator<GeneratorButton> c) {
        GeneratorButton temp;
        for (int i = 1; i < genBTNs.length; i++) {
            for (int j = i; j > 0; j--) {
                if (c.compare(genBTNs[j], genBTNs[j - 1]) < 0) {
                    temp = genBTNs[j];
                    genBTNs[j] = genBTNs[j - 1];
                    genBTNs[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
        updateButtonOrder();
    }

    /**
     * Resorts the generator buttons based on the current sorting mode.
     */
    public void resort() {
        resortWithKey(sortMode.getKey());
    }

    /**
     * Updates the order of the generator buttons in the panel.
     */
    private void updateButtonOrder() {
        for (int i = 0; i < genBTNs.length; i++) {
            generators.setComponentZOrder(genBTNs[i], i);
        }
        generators.revalidate();
        generators.repaint();
    }

    /**
     * Returns the display name of the current sorting mode.
     * 
     * @return String The display name of the current sorting mode.
     */
    public String getSortModeString() {
        return sortMode.getDisplayName();
    }

    /**
     * Returns the number of levels to upgrade per click.
     * 
     * @return int The number of levels to upgrade per click.
     */
    public static int getLvlsPerClick() {
        return LvlsPerClickOptions[LvlsPerClickIndex];
    }

    /**
     * Toggles the number of levels to upgrade per click.
     */
    public static void toggleLvlsPerClick() {
        LvlsPerClickIndex = (LvlsPerClickIndex + 1) % LvlsPerClickOptions.length;
    }

    /**
     * When a GeneratorButton is clicked, purchases Levels for the corresponding Generator
     * 
     * @param e The action event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof GeneratorButton) {
            GeneratorButton gen = (GeneratorButton) e.getSource();
            gen.levelUp(getLvlsPerClick());
            resort();
        }
    }
}
