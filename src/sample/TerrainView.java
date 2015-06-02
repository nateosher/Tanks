/**
 * FourSidedDie.java
 * Jeff Ondich, 10 Nov 2014
 *
 * A View class for an MVC demo using JavaFX.
 */

package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class TerrainView extends Group {
    private TerrainModel terrainModel;

    @FXML private Color terrainColor;
    @FXML private int width;
    @FXML private int height;
    //private int x_left_shift;


    public TerrainView() {
        update();
    }

    public void setTerrainModel(TerrainModel terrainModel) { this.terrainModel = terrainModel;}

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    //public int getXLeftShift() {
    //    return this.x_left_shift;
    //}

    //public void setXLeftShift(int x_left_shift) {
    //    this.x_left_shift = x_left_shift;
    //}

    public Color getTerrainColor() {
        return this.terrainColor;
    }

    public void setTerrainColor(Color terrainColor) {
        this.terrainColor = terrainColor;
    }

    public void destroyChunk(int xCor, int radius) {
        this.terrainModel.destroyChunk(xCor, radius);
        update();
    }

    public void update() {
        this.getChildren().clear();
        for (int i=0; i<this.width; i++) {
            Rectangle sliver = new Rectangle(i, this.terrainModel.getYPos(i),
                    1, this.height-this.terrainModel.getYPos(i));
            // Rectangle sliver = new Rectangle(i, this.height-this.terrainModel.getYPos(i + this.x_left_shift),
                    // 1, this.terrainModel.getYPos(i+this.x_left_shift));
            //System.out.println(this.height - this.terrainModel.getYPos(i + this.x_left_shift) + ", " + this.terrainModel.getYPos(i + this.x_left_shift));
            sliver.setFill(this.getTerrainColor());
            this.getChildren().add(sliver);
        }
    }
}
