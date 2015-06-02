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

    final int DEFAULT_WIDTH = 1200;
    final int DEFAULT_HEIGHT = 800;

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
            sliver.setFill(this.getTerrainColor());
            this.getChildren().add(sliver);
        }
    }
}
