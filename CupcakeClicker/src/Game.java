package CupcakeClicker.src;

import java.sql.Date;

public class Game {
    private Upgrade[] clickUpgrades = {};
    private Upgrade[][] generatorsUpgrades = {};
    private Upgrade[] prestigeUpgrades = {};
    private Generator[] generators = {};
    private double cupcakes;
    private double cupcakesPerSecond;
    private double cupcakesPerClick = 1;
    private double prestigeMultiplier = 1;
    private double prestigeCupkakes;
    private Date lastTimePlayed;

    public Game() {
        loadFromTXT();
        //calculateOfflineincome(); requires loadFromTxt() to work
    }

    public void saveToTXT() {
        // TODO
    }

    public void loadFromTXT() {
        // TODO
    }

    public void addCupcakes(double newCupcakes) {
        cupcakes += newCupcakes;
        prestigeCupkakes += newCupcakes;
    }

    public void subtractCupcakes(double cupcakesSpent) {
        cupcakes -= cupcakesSpent;
    }


    public void calculateIncomePerClick() {
        double tempCupcakesPerClick = 1;
        for (Upgrade upgrade : clickUpgrades) {
            if (upgrade.isBought()) {
                tempCupcakesPerClick = upgrade.applyUpgrade(tempCupcakesPerClick);
            }
        }
        cupcakesPerClick = tempCupcakesPerClick*prestigeMultiplier;
        addCupcakes(cupcakesPerClick);
    }

   
    //delta is the elapsed time in milliseconds since the last frame
    public void calculateIncomePerFrame(long delta) {
        double tempCupcakesPerSecond = 1;
        for (int i = 0; i < generators.length; i++) {
            double generatorCupckaesPerSecond = generators[i].getProductionPerSecond();
            for (Upgrade upgrade : generatorsUpgrades[i]) {
                if (upgrade.isBought()) { 
                    generatorCupckaesPerSecond = upgrade.applyUpgrade(generatorCupckaesPerSecond);
                }
            }
            tempCupcakesPerSecond += generatorCupckaesPerSecond;
        }
        cupcakesPerSecond =  tempCupcakesPerSecond*prestigeMultiplier;
        addCupcakes(cupcakesPerSecond * delta / 1000);
    }

    public void calculateOfflineincome() {
        long offlineMilliSeconds = (System.currentTimeMillis() - lastTimePlayed.getTime());
        calculateIncomePerFrame(offlineMilliSeconds);
    }

    public void prestige() {
        //TODO
    }

    public double getCupcakes() {
        return cupcakes;
    }
}
