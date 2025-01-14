package CupcakeClicker.src;

import java.sql.Date;

public class Game {
    private static Upgrade[] clickUpgrades = {};
    private static Upgrade[][] generatorsUpgrades = {{}, {}};
    private static Upgrade[] prestigeUpgrades = {};
    private static Generator[] generators = {new Generator("Generator", "CupcakeClicker\\Assets\\Cupcake.png", 0, 0, (e) -> e, 1),
                                            new Generator("Generator 2", "CupcakeClicker\\Assets\\Cupcake.png", 0, 10, (e) -> e, 1)
                                                };
    private static double cupcakes;
    private static double cupcakesPerSecond = 1;
    private static double cupcakesPerClick = 1;
    private static double prestigeCupkakes;
    private static double prestigeMultiplier = 1;
    private static Date lastTimePlayed;

    public Game() {
        loadFromTXT();
        //calculateOfflineincome(); requires loadFromTxt() to work
    }

    public static void saveToTXT() {
        // TODO
    }

    public static void loadFromTXT() {
        // TODO
    }

    public static void addCupcakes(double newCupcakes) {
        cupcakes += newCupcakes;
        prestigeCupkakes += newCupcakes;
    }

    public static void subtractCupcakes(double cupcakesSpent) {
        cupcakes -= cupcakesSpent;
    }


    public static void calculateIncomePerClick() {
        double baseCupcakesPerClick = 0.1;
        for (Upgrade upgrade : clickUpgrades) {
            if (upgrade.isBought()) {
                baseCupcakesPerClick = upgrade.applyUpgrade(baseCupcakesPerClick);
            }
        }
        cupcakesPerClick = baseCupcakesPerClick*prestigeMultiplier;
        addCupcakes(cupcakesPerClick);
    }

   
    //delta is the elapsed time in milliseconds since the last frame
    public static void calculateIncomePerFrame(long delta) {
        double tempCupcakesPerSecond = 0;
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

    public static void calculateOfflineincome() {
        long offlineMilliSeconds = (System.currentTimeMillis() - lastTimePlayed.getTime());
        calculateIncomePerFrame(offlineMilliSeconds);
    }

    public void prestige() {
        //TODO
    }

    public static double getCupcakes() {
        return cupcakes;
    }

    public static double getCupcakesPerSecond() {
        return cupcakesPerSecond;
    }

    public static Generator[] getGenerators() {
        return generators;
    }
}
