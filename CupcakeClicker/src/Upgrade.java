package CupcakeClicker.src;
import java.util.ArrayList;
import java.util.Comparator;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.function.DoubleUnaryOperator;

/**
 *
 * @author shemm1
 */

public class Upgrade {
   private String name, iconPath, description;
   private double cost;
   private boolean isBought;
   private DoubleUnaryOperator upgradeEffect;
   private UpgradeType type;
   private static ArrayList<Upgrade> upgrades = new ArrayList<Upgrade>();
   private static ArrayList<GeneratorUpgrade> genUpgrades = new ArrayList<GeneratorUpgrade>();
   

   //upgrade effect takes in the value affected by the upgrade, and return an upgraded value
   public Upgrade(String name, String iconPath, String description, double cost, DoubleUnaryOperator upgradeEffect, UpgradeType type){
      this.name = name;
      this.iconPath = iconPath;
      this.description = description;
      this.cost = cost;
      this.upgradeEffect = upgradeEffect;
      this.type = type;
      isBought = false;

      upgrades.add(this);

      if (this instanceof GeneratorUpgrade) {
         genUpgrades.add((GeneratorUpgrade) this);
      }

   }

   public static void sortUpgrades(){
      Comparator<Upgrade> c = 
      new Comparator<Upgrade>() {
         @Override
         public int compare(Upgrade o1, Upgrade o2) {
            return (int)(o1.getCost() - o2.getCost());
         }
      };

      Upgrade temp;
        for (int i = 1; i < upgrades.size(); i++) {
            for (int j = i; j > 0; j--) {
                if (c.compare(upgrades.get(j), upgrades.get(j-1)) < 0) {
                    temp = upgrades.get(j);
                    upgrades.set(j, upgrades.get(j-1));
                    upgrades.set(j-1, temp);
                }
                else {
                    break;
                }
            }
        }
   }

   public static ArrayList<Upgrade> getUpgrades() {
       return upgrades;
   }


   public static ArrayList<GeneratorUpgrade> getGenUpgrades() {
       return genUpgrades;
   }

   public void buy(){
      if (Game.getCupcakes() >= cost) {
         Game.subtractCupcakes(cost);
         isBought = true;
      }
   }

   public UpgradeType getType() {
      return type;
   }

   public boolean isBought(){
      return isBought;
   }

   public void setBought(boolean isBought) {
       this.isBought = isBought;
   }

   public double applyUpgrade(double operand){
      return upgradeEffect.applyAsDouble(operand);
   }

   public double getCost() {
      return cost;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public String getIconPath() {
      return iconPath;
   }
}

