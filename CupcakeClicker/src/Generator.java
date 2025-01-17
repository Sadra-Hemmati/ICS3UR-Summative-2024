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

     public String getButtonDisplayCost() {
        
        double costSum = 0;

        //Max buy
        if (Game.getLvlsPerClick() == -1){

            int i = 0;
            while(Game.getCupcakes() - costSum - costCalulation.applyAsDouble(level + i) > 0) {

                //if more than 500 upgrades can be bought, quit the algorithm to not lag the program
                if(i > 500) {
                    System.out.println("Max");
                    return "Max";
                }

                costSum += costCalulation.applyAsDouble(level + i);
                i++;
            }
        }

        else{
            for (int i = 0; i < Game.getLvlsPerClick(); i++) {
                costSum += costCalulation.applyAsDouble(level + i);
            }
        }
        return String.valueOf(costSum);
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
