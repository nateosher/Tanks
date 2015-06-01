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
    private double health;
    private int fuel;

    final int DEFAULT_WIDTH = 30;
    final int DEFAULT_HEIGHT = 20;
    final Color[] DEFAULT_TANKCOL = {Color.DARKORANGE, Color.BLUEVIOLET};
    final Color DEFAULT_BORDER = Color.BLACK;
    final int DEFAULT_X = 0;
    final int DEFAULT_Y = 0;
    final int DEFAULT_MOVE = 5;
    final int DEFAULT_POWER = 50;
    final double DEFAULT_HEALTH = 90;
    final int DEFAULT_FUEL = 100;

    public TankModel(int team){
        this.randomNumberGenerator = new Random();
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.tankCol = DEFAULT_TANKCOL[team];
        this.borderColor = DEFAULT_BORDER;
        // this.startXCor = randomNumberGenerator.nextInt(1200);
        this.startXCor = DEFAULT_X;
        this.xCor = startXCor;
        this.yCor = DEFAULT_Y;
        this.power = DEFAULT_POWER;
        this.health = DEFAULT_HEALTH;
        this.fuel = DEFAULT_FUEL;
    }

    public void setTerrainModel(TerrainModel newTerrainModel) { this.terrainModel = newTerrainModel; }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPower() {
        return this.power;
    }

    public int getStartXCor(){ return this.startXCor; }

    public int getWidth(){ return this.width; }

    public int getHeight(){ return this.height; }

    public Color getTankCol(){ return this.tankCol; }

    public Color getBorderColor(){ return this.borderColor; }

    public void setX(int newX){ this.xCor = newX; }

    public void setPositionByX (int newX) {
        this.xCor = newX;
        this.yCor = this.terrainModel.getYPos(this.xCor) - 15; }

    public int getX() { return this.xCor; }

    public void setY(int newY) {this.yCor = newY;}

    public int getY() { return this.yCor; }

    public double getHealth() {return this.health;}

    public int getFuel() {return this.fuel;}

    public void takeDamage(double damage) {
        System.out.println("ow");
        this.health -= damage;
    }

    public void moveLeft() {
        if (this.xCor > 5) {
            this.xCor -= 5;
            this.yCor = this.terrainModel.getYPos(this.xCor) - 15;
            this.fuel--;
        }
    }
    public void moveRight() {
        if (this.xCor < 1170) {
            this.xCor += 5;
            this.yCor = this.terrainModel.getYPos(this.xCor) - 15;
            this.fuel--;
        }
    }
}
