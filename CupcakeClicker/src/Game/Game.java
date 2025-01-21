package CupcakeClicker.src.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import CupcakeClicker.src.Constants;

/**
 * The main game class that handles game logic and state.
 */
public class Game {
    private static double cupcakes;
    private static double cupcakesPerSecond;
    private static double cupcakesPerClick;
    private static double prestigeCupkakes;
    private static double prestigeMultiplier = 1;
    private static long lastTimePlayed;
    private static boolean displayScientific;

    /**
     * Initializes the game by setting up generators, upgrades, loading saved data, and calculating offline gains.
     */
    public static void initialize() {
        intializeGens();
        initializeUpgrades();
        loadFromTXT();
        calculateOfflineIncome();
    }

    /**
     * Saves the current game state to a text file.
     */
    public static void saveToTXT() {

        try (PrintWriter writer = new PrintWriter(new FileWriter("CupcakeClicker/src/Saves/Save.txt"))) {
            writer.println(cupcakes);
            writer.println(prestigeCupkakes);
            writer.println(prestigeMultiplier);
            for (Generator gen : Generator.getGenerators()) {
                writer.println(gen.getLevel());
            }
            for (Upgrade upg : Upgrade.getUpgrades()) {
                writer.println(upg.isBought());
            }
            writer.println(System.currentTimeMillis());

        } catch (IOException e) {
            System.err.println("Error loading: " + e.getMessage());
        }
        System.out.println("Game saved");
    }

    /**
     * Loads the game state from a text file.
     */
    public static void loadFromTXT() {
        try (BufferedReader reader = new BufferedReader(new FileReader("CupcakeClicker/src/Saves/Save.txt"))) {
            cupcakes = Double.parseDouble(reader.readLine());
            prestigeCupkakes = Double.parseDouble(reader.readLine());
            prestigeMultiplier = Double.parseDouble(reader.readLine());
            for (Generator gen : Generator.getGenerators()) {
                gen.setLevel(Integer.parseInt(reader.readLine()));
            }
            for (Upgrade upg : Upgrade.getUpgrades()) {
                upg.setBought(Boolean.valueOf(reader.readLine()));
            }
            lastTimePlayed = Long.parseLong(reader.readLine());

        } catch (IOException e) {
            System.err.println("Error saving: " + e.getMessage());
        }
    }

    /**
     * Initializes the generators in the game.
     */
    private static void intializeGens() {
        System.out.println();
        new Generator("Auto Clicker", "CupcakeClicker/Assets/Cursor.png", 0, 0, 
                (e) -> 10 * Math.pow(1.05, e), 0.1);
        new Generator("Oven", "CupcakeClicker/Assets/Oven.png", 0, 50, 
                (e) -> 200 * Math.pow(1.06, e), 10);
        new Generator("Kitchen", "CupcakeClicker/Assets/Kitchen.png", 0, 1000, (e) -> 5000 * Math.pow(1.07, e), 200);
        new Generator("Sugar Plantation", "CupcakeClicker/Assets/Sugar_Plantation.png", 0, 20000,
                (e) -> 50000 * Math.pow(1.08, e), 1200);
        new Generator("Cupcake Farm", "CupcakeClicker/Assets/Farm.png", 0, 250000,
                (e) -> 500000 * Math.pow(1.09, e), 10000);
        new Generator("Cupcake Factory", "CupcakeClicker/Assets/Factory.png", 0, 1000000,
                (e) -> 3000000 * Math.pow(1.1, e), 50000);

        System.out.println("Gens initialized");
    }

    /**
     * Initializes the upgrades in the game.
     */
    private static void initializeUpgrades() {
        System.out.println();
        new Upgrade("Get to baking!",
                "Gain 0.1 cupcakes every time you click the cupcake",
                0,
                e -> (e + 0.1),
                UpgradeType.CLICK);
        new GeneratorUpgrade("Faster Clickers", "2x Auto Clicker CPS",
                100,
                autoClickerCPS -> 2 * autoClickerCPS, Constants.GeneratorID.AUTO_CLICKER);
        new Upgrade("Golden Clickers",
                "+0.1 CPC per Auto Clicker",
                500,
                CPC -> CPC + Generator.getGenerators().get(Constants.GeneratorID.AUTO_CLICKER).getLevel() * 0.1,
                UpgradeType.CLICK);
        new GeneratorUpgrade("Hotter Ovens",
                "2x Oven CPS",
                2000,
                ovenCPS -> 2 * ovenCPS,
                Constants.GeneratorID.OVEN);
        new Upgrade("Golden Ovens",
                "+1 CPC per Oven",
                15000,
                CPC -> CPC + Generator.getGenerators().get(Constants.GeneratorID.OVEN).getLevel() * 0.1,
                UpgradeType.CLICK);
        new Upgrade("Bigger Cupcake Trays",
                "Double CPS", 15000,
                CPS -> 2 * CPS,
                UpgradeType.CPS);
        new GeneratorUpgrade("Sweeter Plantations",
                "2x Plantation CPS",
                100000,
                plantationCPS -> 2 * plantationCPS, Constants.GeneratorID.PLANTATION);
        new Upgrade("Generated clicks",
                "CPC +10% of CPS",
                150000,
                CPC -> CPC + Game.getCupcakesPerSecond() * 0.1, UpgradeType.CLICK);
        new Upgrade("Golden Plantations",
                "+10 CPC per Plantation",
                200000,
                CPC -> CPC + Generator.getGenerators().get(Constants.GeneratorID.PLANTATION).getLevel() * 0.1,
                UpgradeType.CLICK);
        new GeneratorUpgrade("Fertile Farms",
                "2x Farm CPS",
                3000000,
                farmCPS -> 2 * farmCPS,
                Constants.GeneratorID.FARM);
        new Upgrade("Prestigious prestiging", "50% more Prestige Multiplier gain",
                5000000, e -> 1.5 * e, UpgradeType.PRESTIGE);
        new Upgrade("Golden Farms", "+100 CPC per Farm", 5000000,
                e -> e + Generator.getGenerators().get(Constants.GeneratorID.FARM).getLevel() * 0.1, UpgradeType.CLICK);
        new GeneratorUpgrade("Efficient Factories", "2x Factory CPS", 20000000,
                e -> 2 * e, Constants.GeneratorID.FACTORY);
        new Upgrade("Golden Factories", "+1000 CPC per Factory", 30000000,
                e -> e + Generator.getGenerators().get(Constants.GeneratorID.FACTORY).getLevel() * 0.1,
                UpgradeType.CLICK);
        new Upgrade("Even Bigger Cupcake Trays", "Double CPS", 15000, e -> 2 * e,
                UpgradeType.CPS);

        Upgrade.sortUpgrades();
        System.out.println("Upgrades initialized");
    }

    /**
     * Adds the specified number of cupcakes to the player's total, and to prestigeCupcakes.
     * 
     * @param newCupcakes the number of cupcakes to add
     */
    public static void addCupcakes(double newCupcakes) {
        cupcakes += newCupcakes;
        prestigeCupkakes += newCupcakes;
    }

    /**
     * Subtracts the specified number of cupcakes from the player's total.
     * 
     * @param cupcakesSpent the number of cupcakes to subtract
     */
    public static void subtractCupcakes(double cupcakesSpent) {
        cupcakes -= cupcakesSpent;
    }

    /**
     * Calculates the income per click based on the bought upgrades, then adds that amount of cupcakes.
     */
    public static void calculateIncomePerClick() {
        double baseCupcakesPerClick = 0.0;
        for (Upgrade upg : Upgrade.getUpgrades()) {
            if (upg.getType() == UpgradeType.CLICK && upg.isBought()) {
                baseCupcakesPerClick = upg.applyUpgrade(baseCupcakesPerClick);
            }
        }
        cupcakesPerClick = baseCupcakesPerClick * prestigeMultiplier;
        addCupcakes(cupcakesPerClick);
    }

    /**
     * Calculates the income per frame based on the elapsed time, then adds that amount of cupcakes.
     * 
     * @param delta the elapsed time in milliseconds since the last frame
     */
    public static void calculateIncomePerFrame(long delta) {
        double tempCupcakesPerSecond = 0;
        for (Generator gen : Generator.getGenerators()) {
            tempCupcakesPerSecond += gen.getProductionPerSecond();
        }

        for (Upgrade upg : Upgrade.getUpgrades()) {
            if (upg.getType() == UpgradeType.CPS && upg.isBought()) {
                tempCupcakesPerSecond = upg.applyUpgrade(tempCupcakesPerSecond);
            }
        }

        cupcakesPerSecond = tempCupcakesPerSecond * prestigeMultiplier;
        addCupcakes(cupcakesPerSecond * delta / 1000);
    }

    /**
     * Calculates the offline income based on the time elapsed since the last play.
     */
    private static void calculateOfflineIncome() {
        long offlineMilliSeconds = (System.currentTimeMillis() - lastTimePlayed);
        calculateIncomePerFrame(offlineMilliSeconds);
    }

    /**
     * Returns the prestige multiplier gain based on the prestige cupcakes and prestige upgrade.
     * 
     * @return the prestige multiplier gain
     */
    public static double getPrestigeMultiplierGain() {
        double prestigeMultiplierGain = Math.log(prestigeCupkakes - 1000000) / Math.log(100);
        for (Upgrade upg : Upgrade.getUpgrades()) {
            if (upg.getType() == UpgradeType.PRESTIGE && upg.isBought()) {
                prestigeMultiplierGain = upg.applyUpgrade(prestigeMultiplierGain);
            }
        }
        return prestigeMultiplierGain > 0.01 ? prestigeMultiplierGain : 0;
    }

    /**
     * Resets the game state for a prestige, increasing the prestige multiplier.
     */
    public static void prestige() {

        prestigeMultiplier += getPrestigeMultiplierGain();

        prestigeCupkakes = 0;
        cupcakes = 0;
        for (Generator gen : Generator.getGenerators()) {
            gen.setLevel(0);
            gen.setUnlocked(false);
        }
        for (Upgrade upg : Upgrade.getUpgrades()) {
            upg.setBought(false);
        }
    }

    /**
     * Returns the current number of cupcakes.
     * 
     * @return the current number of cupcakes
     */
    public static double getCupcakes() {
        return cupcakes;
    }

    /**
     * Returns the current number of prestige cupcakes.
     * 
     * @return the current number of prestige cupcakes
     */
    public static double getPrestigeCupkakes() {
        return prestigeCupkakes;
    }

    /**
     * Returns the current prestige multiplier.
     * 
     * @return the current prestige multiplier
     */
    public static double getPrestigeMultiplier() {
        return prestigeMultiplier;
    }

    /**
     * Returns the current cupcakes per second.
     * 
     * @return the current cupcakes per second
     */
    public static double getCupcakesPerSecond() {
        return cupcakesPerSecond;
    }

    /**
     * Returns the current cupcakes per click.
     * 
     * @return the current cupcakes per click
     */
    public static double getCupcakesPerClick() {
        return cupcakesPerClick;
    }

    /**
     * Formats the given value with a suffix (e.g., k, M, B), up until 999.99 x 10^64 or 999.99 Vigintillion, at which point scientific notation will be used.
     * 
     * @param value the value to format
     * @return the formatted value with a suffix
     */
    public static String formatWithSuffix(double value) {
        String[] suffixes = { "", "k", "M", "B", "T", "Qa", "Qu", "S", "Sp", "Oc", "N", "Dc", "UDc", "DDc", "TDc",
                "QaDc", "QuDc", "SDc", "SpDc", "ODc", "NDc", "Vg" };
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

    /**
     * Formats the given value in scientific notation.
     * 
     * @param value the value to format
     * @return the formatted value in scientific notation
     */
    public static String formatScientific(double value) {
        DecimalFormat df = new DecimalFormat("0.##E0"); // Scientific notation
        return df.format(value);
    }

    /**
     * Returns the current number of cupcakes as a formatted string.
     * 
     * @return the current number of cupcakes as a formatted string
     */
    public static String getCupckakesString() {
        if (displayScientific) {
            return formatScientific(getCupcakes());
        } else {
            return formatWithSuffix(getCupcakes());
        }
    }

    /**
     * Returns the current cupcakes per second as a formatted string.
     * 
     * @return the current cupcakes per second as a formatted string
     */
    public static String getCupckakesPerSecondString() {
        if (displayScientific) {
            return formatScientific(getCupcakesPerSecond());
        } else {
            return formatWithSuffix(getCupcakesPerSecond());
        }
    }

    /**
     * Returns the current cupcakes per click as a formatted string.
     * 
     * @return the current cupcakes per click as a formatted string
     */
    public static String getCupckakesPerClickString() {
        if (displayScientific) {
            return formatScientific(getCupcakesPerClick());
        } else {
            return formatWithSuffix(getCupcakesPerClick());
        }
    }

}
