package CupcakeClicker.src.Game;

import java.util.function.DoubleUnaryOperator;

/**
 * The GeneratorUpgrade class represents an upgrade specific to a generator.
 */
public class GeneratorUpgrade extends Upgrade {
    private int genID;

    /**
     * Constructs a new GeneratorUpgrade.
     * 
     * @param name The name of the upgrade.
     * @param description The description of the upgrade.
     * @param cost The cost of the upgrade.
     * @param upgradeEffect The effect of the upgrade, represented as a {@code DoubleUnaryOperator}.
     * @param genID The ID of the generator this upgrade applies to.
     */
    GeneratorUpgrade(String name, String description, double cost, DoubleUnaryOperator upgradeEffect,
            int genID) {
        super(name, description, cost, upgradeEffect, UpgradeType.GENERATOR);
        iconPath = Generator.getGenerators().get(genID).getIconPath();
        this.genID = genID;
    }

    /**
     * Returns the ID of the generator this upgrade applies to.
     * 
     * @return int The ID of the generator.
     */
    public int getGenID() {
        return genID;
    }
}
