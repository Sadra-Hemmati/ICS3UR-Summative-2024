package CupcakeClicker.src;

import java.util.function.IntToDoubleFunction;

public class Generator {
    private double unlockThreshold, productionPerLevelPerSecond;
    private String name, iconPath;
    private int level;
    private IntToDoubleFunction costCalulation;
    private boolean isUnlocked;
    
    Generator(String name, String iconPath, int level, double unlockThreshold, IntToDoubleFunction costCalulation, double productionPerLevelPerSecond) {
        this.name = name;
        this.iconPath = iconPath;
        this.level = level;
        this.unlockThreshold = unlockThreshold;
        this.costCalulation = costCalulation;
        this.productionPerLevelPerSecond = productionPerLevelPerSecond;
        isUnlocked = false;
    }

    public double getProductionPerSecond() {
        return productionPerLevelPerSecond * level;
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

     //this should probably be changed, maybe game class variable should be static
     public boolean isUnlocked() {
        if (!isUnlocked) {
            isUnlocked = Game.getCupcakes() >= unlockThreshold;
        }
        return isUnlocked;
     }

     public int getLevel() {
         return level;
     }

     public void prestigeReset(){
        //TODO
    }

    public void levelUp(int levels) {
        for (int i = 0; i < levels; i++) {
            if (Game.getCupcakes() >= getCost()) {
                Game.subtractCupcakes(getCost());
                level++;
            }
        }
    }
}
