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
    private int x_left_shift;


    //Are these finals necessary? FXML is passing in color values too
    /*

    final double DEFAULT_WIDTH = 200.0;
    final Color DEFAULT_DOT_COLOR = Color.GREEN;
    final Color DEFAULT_BORDER_COLOR = Color.BLACK;
    final Color DEFAULT_BACKGROUND_COLOR = Color.TRANSPARENT; */

    public TerrainView() {
        //Do we care?
        this.x_left_shift = 0;
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

    public int getXLeftShift() {
        return this.x_left_shift;
    }

    public void setXLeftShift(int x_left_shift) {
        this.x_left_shift = x_left_shift;
    }

    public Color getTerrainColor() {
        return this.terrainColor;
    }

    public void setTerrainColor(Color terrainColor) {
        this.terrainColor = terrainColor;
    }

    public void update() {
        this.getChildren().clear();

        // Many, many, rectangles
        for (int i=0; i<this.width; i++) {
            Rectangle sliver = new Rectangle(i, this.terrainModel.getYPos(i+this.x_left_shift),
                    2, this.height-this.terrainModel.getYPos(i+this.x_left_shift));
            sliver.setFill(this.getTerrainColor());
            this.getChildren().add(sliver);
        }

    }
}
