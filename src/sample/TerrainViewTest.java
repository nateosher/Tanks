package sample;

import org.junit.Test;
import javafx.scene.paint.Color;

import static org.junit.Assert.*;

/**
 * Created by Nate Osher, Ben Wedin, Aidan Halloway-Bidwell, Raven Pillmann
 * on 6/2/15.
 */
public class TerrainViewTest {

    @Test
    public void testGetWidthShouldReturn1200() throws Exception {
        TerrainView terrainView = new TerrainView();
        TerrainModel terrainModel = new TerrainModel();
        terrainView.setTerrainModel(terrainModel);
        int expected = 1200;
        int actual = terrainView.getWidth();
        org.junit.Assert.assertEquals("fail - getWidth() failed to return 1200",
                expected, actual);
    }

    @Test
    public void testGetHeightShouldReturn800() throws Exception {
        TerrainView terrainView = new TerrainView();
        TerrainModel terrainModel = new TerrainModel();
        terrainView.setTerrainModel(terrainModel);
        int expected = 800;
        int actual = terrainView.getHeight();
        org.junit.Assert.assertEquals("fail - getHeight() failed to return 800",
                expected, actual);
    }

    @Test
    public void testGetTerrainColorShouldReturnDarkGreen() throws Exception {
        TerrainView terrainView = new TerrainView();
        TerrainModel terrainModel = new TerrainModel();
        terrainView.setTerrainModel(terrainModel);
        Color expected = Color.DARKGREEN;
        Color actual = terrainView.getTerrainColor();
        org.junit.Assert.assertEquals("fail - getTerrainColor failed to return" +
                "darkgreen", expected, actual);
    }
}