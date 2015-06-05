package sample;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import java.util.*;

/**
 * Created by Nate on 5/28/15.
 */
public class TankModel {
    @FXML private int width;
    @FXML private int height;
    @FXML private Color tankCol;
    @FXML private Color borderColor;

    private Random randomNumberGenerator;
    private int startXCor;
    private int xCor;
    private int yCor;
    private TerrainModel terrainModel;
    private int power;
    private int fuel;
    private int health;
    private int nozzleXCor;
    private int nozzleYCor;
    private int nozzleLength;
    private int nozzleHeight;
    private int nozzleAngle;
    private int shotIntensity;

    final int DEFAULT_WIDTH = 30;
    final int DEFAULT_HEIGHT = 20;
    final Color[] DEFAULT_TANKCOL = {Color.DARKORANGE, Color.BLUEVIOLET};
    final Color DEFAULT_BORDER = Color.BLACK;
    final int DEFAULT_X = 0;
    final int DEFAULT_Y = 0;
    final int DEFAULT_POWER = 50;
    final int DEFAULT_FUEL = 100;
    final int DEFAULT_HEALTH = 100;

    public TankModel(int team){
        this.randomNumberGenerator = new Random();
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.tankCol = DEFAULT_TANKCOL[team];
        this.borderColor = DEFAULT_BORDER;
        this.startXCor = DEFAULT_X;
        this.xCor = startXCor;
        this.yCor = DEFAULT_Y;
        this.power = DEFAULT_POWER;
        this.health = DEFAULT_HEALTH;
        this.nozzleXCor = startXCor;
        this.nozzleYCor = DEFAULT_Y;
        this.nozzleLength = 20;
        this.nozzleHeight = 3;
        this.nozzleAngle = 180;
        this.fuel = DEFAULT_FUEL;
    }

    public void setTerrainModel(TerrainModel newTerrainModel)
        { this.terrainModel = newTerrainModel; }

    public int getPower() {
        return this.power;
    }

    public int getStartXCor(){ return this.startXCor; }

    public int getWidth(){ return this.width; }

    public int getHeight(){ return this.height; }

    public Color getTankCol(){ return this.tankCol; }

    public Color getBorderColor(){ return this.borderColor; }

    public int getNozzleX() { return this.nozzleXCor; }

    public void setNozzleY(int newY) {this.nozzleYCor = newY;}

    public void setNozzleX(int newX) {this.nozzleXCor=newX;}

    public int getNozzleY() { return this.nozzleYCor; }

    public int getNozzleLength() { return this.nozzleLength; }

    public int getNozzleHeight() { return this.nozzleHeight; }

    public int getNozzleAngle() { return this.nozzleAngle; }

    public void setNozzleAngle(int newAn) { this.nozzleAngle = newAn; }

    /* Places the tank on top of the terrain given an x coordinate */
    public void setPositionByX (int newX) {
        this.xCor = newX;
        this.yCor = this.terrainModel.getYPos(this.xCor + 15) - 15; }

    public int getX() { return this.xCor; }

    public void setY(int newY) {this.yCor = newY;}

    public int getY() { return this.yCor; }

    public double getHealth() {return this.health;}

    public int getFuel() {return this.fuel;}

    public void setShotIntensity(int shotIntensity)
        { this.shotIntensity = shotIntensity;}

    public int getShotIntensity() { return this.shotIntensity;}

    public void takeDamage(double damage) {
        this.health -= damage;
    }

    /* Checks to see that the tank is within the bounds of the screen,
    * and if it is, sets the tank's x coordinate to be itself minus five
    * and the y coordinate to be the y coordinate of the terrain at that
    * point (offset by fifteen to account for the width of the tank)
    */
    public void moveLeft() {
        if (this.xCor > 5) {
            this.xCor -= 5;
            this.yCor = this.terrainModel.getYPos(this.xCor) - 15;
            this.fuel--;
        }
    }

    /* Checks to see that the tank is within the bounds of the screen,
    * and if it is, sets the tank's x coordinate to be itself plus five
    * and the y coordinate to be the y coordinate of the terrain at that
    * point (offset by fifteen to account for the width of the tank)
    */
    public void moveRight() {
        if (this.xCor < 1170) {
            this.xCor += 5;
            this.yCor = this.terrainModel.getYPos(this.xCor) - 15;
            this.fuel--;
        }
    }

    /*
    * Updates the health of a tank if it is hit by a projectile. This is called
    * whenever a tank is hit.
    */
    public void takeHit(int projXCor, int radius, int damage) {
        if (Math.abs(this.getX()-projXCor) <= radius) {
            drop();
            loseHealth(damage);
        }
    }

    private void loseHealth(int damage) {
        this.health -= damage;
    }

    /* Drops the tank when the terrain sinks. */
    private void drop() {
        int droppedY = this.terrainModel.getYPos(this.xCor + 15) - 15;
        this.setY(droppedY);
    }
}
