package CupcakeClicker.src.Game;

import java.util.ArrayList;
import java.util.function.DoubleUnaryOperator;

/**
 * Represents an upgrade in the game.
 */
public class Upgrade {
   private String name, description;
   protected String iconPath;
   private double cost;
   private boolean isBought;
   private DoubleUnaryOperator upgradeEffect;
   private UpgradeType type;
   private static ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
   private static ArrayList<GeneratorUpgrade> genUpgrades = new ArrayList<GeneratorUpgrade>();

   /**
    * Constructs a new Upgrade.
    * 
    * @param name the name of the upgrade
    * @param description the description of the upgrade
    * @param cost the cost of the upgrade
    * @param upgradeEffect the effect of the upgrade
    * @param type the type of the upgrade
    */
   public Upgrade(String name, String description, double cost, DoubleUnaryOperator upgradeEffect,
         UpgradeType type) {
      this.name = name;
      this.description = description;
      this.cost = cost;
      this.upgradeEffect = upgradeEffect;
      this.type = type;
      isBought = false;

      iconPath = switch(type){
      case CLICK -> "CupcakeClicker/Assets/Click_Upgrade.png";
      case CPS -> "CupcakeClicker/Assets/CPS_Upgrade.png";
      case PRESTIGE -> "CupcakeClicker/Assets/Prestige_Upgrade.png";
      default -> "";
      };

      upgrades.add(this);

      if (this instanceof GeneratorUpgrade) {
         genUpgrades.add((GeneratorUpgrade) this);
      }

   }

   /**
    * Sorts the upgrades by cost in ascending order.
    */
   public static void sortUpgrades() {
      Upgrade temp;
      for (int i = 1; i < upgrades.size(); i++) {
         for (int j = i; j > 0; j--) {
            if (upgrades.get(j).getCost() - upgrades.get(j - 1).getCost() < 0) {
               temp = upgrades.get(j);
               upgrades.set(j, upgrades.get(j - 1));
               upgrades.set(j - 1, temp);
            } else {
               break;
            }
         }
      }
   }

   /**
    * Returns the list of all upgrades.
    * 
    * @return the list of upgrades, as an {@code ArrayList<Upgrade>}
    */
   public static ArrayList<Upgrade> getUpgrades() {
      return upgrades;
   }

   /**
    * Returns the list of generator upgrades.
    * 
    * @return the list of generator upgrades as an {@code ArrayList<GeneratorUpgrade>}
    */
   public static ArrayList<GeneratorUpgrade> getGenUpgrades() {
      return genUpgrades;
   }

   /**
    * Buys the upgrade if the player has enough cupcakes.
    */
   public void buy() {
      if (Game.getCupcakes() >= cost) {
         Game.subtractCupcakes(cost);
         isBought = true;
      }
   }

   /**
    * Returns the type of the upgrade.
    * 
    * @return the type of the upgrade
    */
   public UpgradeType getType() {
      return type;
   }

   /**
    * Checks if the upgrade is bought.
    * 
    * @return true if the upgrade is bought, false otherwise
    */
   public boolean isBought() {
      return isBought;
   }

   /**
    * Sets the bought status of the upgrade, should only be used when prestiging, otherwise use {@code buy()}
    * 
    * @param isBought the new bought status
    */
   public void setBought(boolean isBought) {
      this.isBought = isBought;
   }

   /**
    * Applies the upgrade effect to the given value.
    * 
    * @param operand the value to be upgraded
    * @return the upgraded value
    */
   public double applyUpgrade(double operand) {
      return upgradeEffect.applyAsDouble(operand);
   }

   /**
    * Returns the cost of the upgrade.
    * 
    * @return the cost of the upgrade
    */
   public double getCost() {
      return cost;
   }

   /**
    * Returns the name of the upgrade.
    * 
    * @return the name of the upgrade
    */
   public String getName() {
      return name;
   }

   /**
    * Returns the description of the upgrade.
    * 
    * @return the description of the upgrade
    */
   public String getDescription() {
      return description;
   }

   /**
    * Returns the icon path of the upgrade.
    * 
    * @return the icon path of the upgrade
    */
   public String getIconPath() {
      return iconPath;
   }
}
