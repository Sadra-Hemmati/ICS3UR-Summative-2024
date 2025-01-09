package CupcakeClicker.src;
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
   
   //upgrade effect takes in the value affected by the upgrade, and return an upgraded value
   public Upgrade(String name, String iconPath, String description, double cost, DoubleUnaryOperator upgradeEffect){
      this.name = name;
      this.iconPath = iconPath;
      this.description = description;
      this.cost = cost;
      this.upgradeEffect = upgradeEffect;
   }

   public boolean isBought(){
      return isBought;
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
