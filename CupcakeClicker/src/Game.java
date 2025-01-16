package CupcakeClicker.src;

import java.awt.desktop.SystemSleepEvent;
import java.sql.Date;
import java.text.DecimalFormat;

public class Game {
    private static int LvlsPerClickIndex;
    private static int[] LvlsPerClickOptions = {1, 10, 50, -1}; //-1 indicates Max lvls 
    private static Upgrade[] clickUpgrades = {new Upgrade("Double CPC", "CupcakeClicker\\Assets\\Cupcake.png", "", 1, (e) -> 2*e)};
    private static Upgrade[][] generatorsUpgrades = {{}, {}};
    private static Upgrade[] prestigeUpgrades = {};
    private static Generator[] generators = {new Generator("Generator", "CupcakeClicker\\Assets\\Cursor.png", 0, 0, (e) -> 0.1, 1),
                                            new Generator("Generator 2", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1, 1)
                                                };
    private static double cupcakes;
    private static double cupcakesPerSecond = 1;
    private static double cupcakesPerClick = 1;
    private static double prestigeCupkakes;
    private static double prestigeMultiplier = 1;
    private static Date lastTimePlayed;
    private static boolean displayScientific;

    public Game() {
        loadFromTXT();
        //calculateOfflineincome(); requires loadFromTxt() to work
        LvlsPerClickIndex = 0;
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

    public static int getLvlsPerClick() {
        return LvlsPerClickOptions[LvlsPerClickIndex];
    }

    public static void toggleLvlsPerClick() {
        LvlsPerClickIndex = (LvlsPerClickIndex+1)%LvlsPerClickOptions.length;
    }

    public static String formatWithSuffix(double value) {
        String[] suffixes = {"", "k", "M", "B", "T", "Qa", "Qu", "S", "Sp", "Oc", "N", "Dc", "UDc", "DDc", "TDc", "QaDc", "QuDc", "SDc", "SpDc", "ODc", "NDc", "Vg"};
        int index = 0;
        double tempValue = value;
        while (tempValue >= 1000 && index < suffixes.length - 1) {
            tempValue /= 1000;
            index++;
        }

        if (tempValue > 1000) {
            return formatScientific(value);
        }
        
        DecimalFormat df = new DecimalFormat("#.##"); // Two decimal places
        return df.format(tempValue) + suffixes[index];
    }

    public static String formatScientific(double value){
        DecimalFormat df = new DecimalFormat("0.##E0"); // Scientific notation
        return df.format(value);
    }


    public static String getCupckakesString() {
        if (displayScientific) {
            return formatScientific(getCupcakes());
        }
        else {
            return formatWithSuffix(getCupcakes());
        }
    }

    public static String getCupckakesPerSecondString() {
        if (displayScientific) {
            return formatScientific(getCupcakesPerSecond());
        }
        else {
            return formatWithSuffix(getCupcakesPerSecond());
        }
    }
}
