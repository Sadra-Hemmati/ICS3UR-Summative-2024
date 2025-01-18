package CupcakeClicker.src;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

public class Game { 
    private static double cupcakes;
    private static double cupcakesPerSecond = 1;
    private static double cupcakesPerClick = 1;
    private static double prestigeCupkakes;
    private static double prestigeMultiplier = 1;
    private static Date lastTimePlayed;
    private static boolean displayScientific;

    public static void initialize() {
        intializeGens();
        initializeUpgrades();
        loadFromTXT();
        //calculateOfflineincome(); requires loadFromTxt() to work
    }

    public static void saveToTXT() {
        // TODO
    }

    public static void loadFromTXT() {
        // TODO
    }

    private static void intializeGens() {
        System.out.println();
        new Generator("Generator", "CupcakeClicker\\Assets\\Cursor.png", 0, 0, (e) -> 0.1*e, 1);
        new Generator("Generator 2", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 3", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 4", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 5", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 6", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 7", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 8", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 9", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 10", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 11", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 12", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        new Generator("Generator 13", "CupcakeClicker\\Assets\\Cursor.png", 0, 10, (e) -> 0.1*e, 1);
        System.out.println("Gens initialized");
    }

    private static void initializeUpgrades() {
        System.out.println();
        new Upgrade("Upgrade 1", "CupcakeClicker\\Assets\\Cursor.png", "2x CPS", 2, e -> 2*e, UpgradeType.CPS);
        new Upgrade("Upgrade 2", "CupcakeClicker\\Assets\\Cursor.png", "2x CPC", 5, e -> 2*e, UpgradeType.CLICK);
        new GeneratorUpgrade("Upgrade 3", "CupcakeClicker\\Assets\\Cursor.png", "2x auto clicker CPS", 1 , e -> 2*e, 0);
        new Upgrade("Upgrade 4", "CupcakeClicker\\Assets\\Cursor.png", "+0.1 CPC per Auto Clicker", 9, e -> e + Generator.getGenerators().get(GeneratorID.AUTO_CLICKER).getLevel()*0.1, UpgradeType.CLICK);
        Upgrade.sortUpgrades();
        System.out.println("Upgrades initialized");
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
        for (Upgrade upg : Upgrade.getUpgrades()) {
            if (upg.getType() == UpgradeType.CLICK && upg.isBought()) {
                baseCupcakesPerClick = upg.applyUpgrade(baseCupcakesPerClick);
            }
        }
        cupcakesPerClick = baseCupcakesPerClick*prestigeMultiplier;
        System.out.println(cupcakesPerClick);
        addCupcakes(cupcakesPerClick);
    }

   
    //delta is the elapsed time in milliseconds since the last frame
    public static void calculateIncomePerFrame(long delta) {
        double tempCupcakesPerSecond = 0;
        for (Generator gen : Generator.getGenerators()) {
            tempCupcakesPerSecond += gen.getProductionPerSecond();            
        }

        for (Upgrade upg : Upgrade.getUpgrades()) {
            if(upg.getType() == UpgradeType.CPS && upg.isBought()){
                tempCupcakesPerSecond = upg.applyUpgrade(tempCupcakesPerSecond);
            }
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
