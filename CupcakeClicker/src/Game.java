package CupcakeClicker.src;

import java.sql.Date;

public class Game {
   private Upgrade[] upgrades;
   private Generator[] generators;
   private double cupcakes;
   private double cupcakesPerSecond;
   private double prestigeMultiplier;
   private double prestigeCupkakes;
   private Date lastTimePlayed;

   public Game() {
    loadFromTXT();
    calculateOfflineincome();
   }
    
    public void saveToTXT() {
    //TODO
   }

   public void loadFromTXT() {
    //TODO
   }

   private void calculateOfflineincome() {
    // TODO Auto-generated method stub
    //long offlineSeconds = System.getSystemMillis() - lastTimePlayed;
   }
}
