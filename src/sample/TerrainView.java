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

    final int DEFAULT_WIDTH = 1200;
    final int DEFAULT_HEIGHT = 800;
    final Color DEFAULT_COLOR = Color.DARKGREEN;

    public TerrainView() {
        this.terrainColor = DEFAULT_COLOR;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
    }

    public void setTerrainModel(TerrainModel terrainModel) {
        this.terrainModel = terrainModel;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return this.height;
    }

    public Color getTerrainColor() {
        return this.terrainColor;
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
