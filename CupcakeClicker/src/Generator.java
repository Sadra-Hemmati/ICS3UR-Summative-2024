package CupcakeClicker.src;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.IntToDoubleFunction;

public class Generator {
    private double unlockThreshold, productionPerLevelPerSecond;
    private String name, iconPath;
    private int level;
    private IntToDoubleFunction costCalulation;
    private boolean isUnlocked;
    private static ArrayList<Generator> generators = new ArrayList<Generator>();
    private int id;

    Generator(String name, String iconPath, int level, double unlockThreshold, IntToDoubleFunction costCalulation, double productionPerLevelPerSecond){
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

    public static ArrayList<Generator> getGenerators() {
        return generators;
    }

    public int getId() {
        return id;
    }

    public double getProductionPerSecond() {
        double productionPerSecond = productionPerLevelPerSecond * level;
        for (GeneratorUpgrade upg : Upgrade.getGenUpgrades()) {
            productionPerSecond = upg.applyUpgrade(productionPerSecond);
        }
        return productionPerSecond;
    }

    public double getCost() {
        return costCalulation.applyAsDouble(level);
    }
  
     public String getName() {
        return name;
     }
  
     public String getIconPath() {
        return iconPath;
     }

     public String getButtonDisplayCost() {
        
        double costSum = 0;

        //Max buy
        if (GeneratorPane.getLvlsPerClick() == -1){

            int i = 0;
            while(Game.getCupcakes() - costSum - costCalulation.applyAsDouble(level + i) > 0) {

                //if more than 500 upgrades can be bought, quit the algorithm to not lag the program
                if(i > 500) {
                    return "Max";
                }

                costSum += costCalulation.applyAsDouble(level + i);
                i++;
            }
        }

        else{
            for (int i = 0; i < GeneratorPane.getLvlsPerClick(); i++) {
                costSum += costCalulation.applyAsDouble(level + i);
            }
        }
        return Game.formatWithSuffix(costSum);
     }
     
     public boolean isUnlocked() {
        if (!isUnlocked) {
            isUnlocked = Game.getCupcakes() >= unlockThreshold;
        }
        return isUnlocked;
     }

     public int getLevel() {
         return level;
     }

     public double getUnlockThreshold() {
         return unlockThreshold;
     }

     public void prestigeReset(){
        //TODO
    }

    public void levelUp(int levels) {

        //Max buy
        if (levels == -1) {
            while (true) {
                if (Game.getCupcakes() >= getCost()) {
                    Game.subtractCupcakes(getCost());
                    level++;
                }
                else{
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
