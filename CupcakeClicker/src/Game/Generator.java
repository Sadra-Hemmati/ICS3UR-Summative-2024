package CupcakeClicker.src.Game;

import java.util.ArrayList;
import java.util.function.IntToDoubleFunction;

import CupcakeClicker.src.GUI.GeneratorPane;

/**
 * Represents a generator in the game.
 */
public class Generator {
    private double unlockThreshold, productionPerLevelPerSecond;
    private String name, iconPath;
    private int level;
    private IntToDoubleFunction costCalulation;
    private boolean isUnlocked;
    private static ArrayList<Generator> generators = new ArrayList<Generator>();
    private int id;

    /**
     * Constructs a new Generator.
     * 
     * @param name the name of the generator
     * @param iconPath the icon path of the generator
     * @param level the initial level of the generator
     * @param unlockThreshold the threshold to unlock the generator
     * @param costCalulation the function to calculate the cost of the generator, based on level
     * @param productionPerLevelPerSecond the production per level per second
     */
    Generator(String name, String iconPath, int level, double unlockThreshold, IntToDoubleFunction costCalulation,
            double productionPerLevelPerSecond) {
        this.name = name;
        this.iconPath = iconPath;
        this.level = level;
        this.unlockThreshold = unlockThreshold;
        this.costCalulation = costCalulation;
        this.productionPerLevelPerSecond = productionPerLevelPerSecond;
        isUnlocked = false;

        id = generators.size();
        generators.add(this);
        System.out.println("Gen Added: " + name);
    }

    
    /** 
     * Returns the list of all generators.
     * 
     * @return the list of generators
     */
    public static ArrayList<Generator> getGenerators() {
        return generators;
    }

    /**
     * Returns the ID of the generator.
     * 
     * @return the ID of the generator
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the level of the generator.
     * 
     * @param level the new level of the generator
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Sets the unlocked status of the generator. Should only be used when prestiging.
     * 
     * @param isUnlocked the new unlocked status
     */
    public void setUnlocked(boolean isUnlocked) {
        this.isUnlocked = isUnlocked;
    }

    /**
     * Returns the production per second of the generator.
     * 
     * @return the production per second
     */
    public double getProductionPerSecond() {
        double productionPerSecond = productionPerLevelPerSecond * level;
        for (GeneratorUpgrade upg : Upgrade.getGenUpgrades()) {
            if (upg.getGenID() == id && upg.isBought()) {
                productionPerSecond = upg.applyUpgrade(productionPerSecond);
            }
        }
        return productionPerSecond;
    }

    /**
     * Returns the cost of the generator.
     * 
     * @return the cost of the generator
     */
    public double getCost() {
        return costCalulation.applyAsDouble(level);
    }

    /**
     * Returns the name of the generator.
     * 
     * @return the name of the generator
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the icon path of the generator.
     * 
     * @return the icon path of the generator
     */
    public String getIconPath() {
        return iconPath;
    }

    /**
     * Returns the display cost of the generator button.
     * 
     * @return the display cost of the generator button
     */
    public String getButtonDisplayCost() {

        double costSum = 0;

        // Max buy
        if (GeneratorPane.getLvlsPerClick() == -1) {

            int i = 0;
            while (Game.getCupcakes() - costSum - costCalulation.applyAsDouble(level + i) > 0) {

                // if more than 500 upgrades can be bought, quit the algorithm to not lag the
                // program
                if (i > 500) {
                    return "Max";
                }

                costSum += costCalulation.applyAsDouble(level + i);
                i++;
            }
        }

        else {
            for (int i = 0; i < GeneratorPane.getLvlsPerClick(); i++) {
                costSum += costCalulation.applyAsDouble(level + i);
            }
        }
        return Game.formatWithSuffix(costSum);
    }

    /**
     * Checks if the generator is unlocked.
     * 
     * @return true if the generator is unlocked, false otherwise
     */
    public boolean isUnlocked() {
        if (!isUnlocked) {
            isUnlocked = Game.getCupcakes() >= unlockThreshold;
        }
        return isUnlocked;
    }

    /**
     * Returns the level of the generator.
     * 
     * @return the level of the generator
     */
    public int getLevel() {
        return level;
    }

    /**
     * Returns the unlock threshold of the generator.
     * 
     * @return the unlock threshold
     */
    public double getUnlockThreshold() {
        return unlockThreshold;
    }

    /**
     * Levels up the generator by the specified number of levels.
     * 
     * @param levels the number of levels to level up
     */
    public void levelUp(int levels) {

        // Max buy
        if (levels == -1) {
            while (true) {
                if (Game.getCupcakes() >= getCost()) {
                    Game.subtractCupcakes(getCost());
                    level++;
                } else {
                    break;
                }
            }
            return;
        }

        for (int i = 0; i < levels; i++) {
            if (Game.getCupcakes() >= getCost()) {
                Game.subtractCupcakes(getCost());
                level++;
            }
        }
    }
}
