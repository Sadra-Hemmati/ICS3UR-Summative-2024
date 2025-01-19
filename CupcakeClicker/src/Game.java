package CupcakeClicker.src;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

public class Game { 
    private static double cupcakes;
    private static double cupcakesPerSecond;
    private static double cupcakesPerClick = 0.1;
    private static double prestigeCupkakes;
    private static double prestigeMultiplier = 1;
    private static long lastTimePlayed;
    private static boolean displayScientific;

    public static void initialize() {
        intializeGens();
        initializeUpgrades();
        loadFromTXT();
    }

    public static void saveToTXT() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("CupcakeClicker\\Save.txt"))) {
            writer.println(cupcakes);
            writer.println(prestigeCupkakes);
            writer.println(prestigeMultiplier);
            for(Generator gen : Generator.getGenerators()){
                writer.println(gen.getLevel());
            }
            for(Upgrade upg : Upgrade.getUpgrades()) {
                writer.println(upg.isBought());
            }
            writer.println(System.currentTimeMillis());

        } catch (IOException e) {
            System.err.println("Error loading: " + e.getMessage());
        }
        System.out.println("Game saved");
    }

    public static void loadFromTXT() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CupcakeClicker\\Save.txt"))) {
            cupcakes = Double.parseDouble(reader.readLine());
            prestigeCupkakes = Double.parseDouble(reader.readLine());
            prestigeMultiplier = Double.parseDouble(reader.readLine());
            for(Generator gen : Generator.getGenerators()){
                gen.setLevel(Integer.parseInt(reader.readLine()));
            }
            for(Upgrade upg : Upgrade.getUpgrades()) {
                upg.setBought(Boolean.valueOf(reader.readLine()));
            }
            lastTimePlayed = Long.parseLong(reader.readLine());

        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
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

   
    //delta is the elapsed time in milliseconds since the last frame, returns the calculated cupcakes for the calculateOfflineIncome to work properly
    public static double calculateIncomePerFrame(long delta) {
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
        return cupcakesPerSecond * delta / 1000;
    }

    public static double calculateOfflineincome() {
        long offlineMilliSeconds = (System.currentTimeMillis() - lastTimePlayed);
        return calculateIncomePerFrame(offlineMilliSeconds);
    }

    public static double getPrestigeMultiplierGain() {
        double prestigeMultiplierGain = Math.log(prestigeCupkakes - 1000000)/Math.log(100);
        return prestigeMultiplierGain > 0.01? prestigeMultiplierGain:0;
    }

    public static void prestige() {

        prestigeMultiplier += getPrestigeMultiplierGain();

        prestigeCupkakes = 0;
        cupcakes = 0;
        for(Generator gen : Generator.getGenerators()){
            gen.setLevel(0);
            gen.setUnlocked(false);
        }
        for(Upgrade upg : Upgrade.getUpgrades()) {
            upg.setBought(false);
        }
    }

    public static double getCupcakes() {
        return cupcakes;
    }

    public static double getPrestigeCupkakes() {
        return prestigeCupkakes;
    }

    public static double getPrestigeMultiplier() {
        return prestigeMultiplier;
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

    public static double getCupcakesPerClick() {
        return cupcakesPerClick;
    }
}
