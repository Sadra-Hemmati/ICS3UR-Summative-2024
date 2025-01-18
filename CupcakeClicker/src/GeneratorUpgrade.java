package CupcakeClicker.src;

import java.util.function.DoubleUnaryOperator;

public class GeneratorUpgrade extends Upgrade {
    private int genID;

    GeneratorUpgrade(String name, String iconPath, String description, double cost, DoubleUnaryOperator upgradeEffect, int genID){
        super(name, iconPath, description, cost, upgradeEffect, UpgradeType.GENERATOR);
        this.genID = genID;
    }

    public int getGenID() {
        return genID;
    }
}
