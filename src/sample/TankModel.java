package sample;

import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import java.util.Random;

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

    final int DEFAULT_WIDTH = 30;
    final int DEFAULT_HEIGHT = 20;
    final Color DEFAULT_TANKCOL = Color.DARKORANGE;
    final Color DEFAULT_BORDER = Color.BLACK;
    final int DEFAULT_X = 0;
    final int DEFAULT_Y = 0;

    public TankModel(){
        this.randomNumberGenerator = new Random();
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.tankCol = DEFAULT_TANKCOL;
        this.borderColor = DEFAULT_BORDER;
        // this.startXCor = randomNumberGenerator.nextInt(1200);
        // this.xCor = startXCor;
        this.startXCor = DEFAULT_X;
        this.xCor = startXCor;
        this.yCor = DEFAULT_Y;
    }

    public int getStartXCor(){ return this.startXCor; }

    public int getWidth(){ return this.width; }

    public int getHeight(){ return this.height; }

    public Color getTankCol(){ return this.tankCol; }

    public Color getBorderColor(){ return this.borderColor; }

    public void setX(int newX){ this.xCor = newX; }

    public int getX() { return this.xCor; }

    public void setY(int newY) {this.yCor = newY;}

    public int getY() { return this.yCor; }

}
