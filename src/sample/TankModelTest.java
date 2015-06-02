package sample;

import org.junit.Test;
import javafx.scene.paint.Color;
import static org.junit.Assert.*;

/**
 * Created by oshern on 6/1/15.
 */
public class TankModelTest {

    TankModel testTankModel = new TankModel(0);

    @Test
    public void testGetPowerShouldDefaultTo50() throws Exception {
        int actual = this.testTankModel.getPower();
        int expected = 50;
        org.junit.Assert.assertEquals("Error in testGetPowerShouldDefaultTo50",
                expected, actual);
    }

    @Test
    public void testGetStartXCorShouldNotBeNull() throws Exception {
        int actual = this.testTankModel.getStartXCor();
        org.junit.Assert.assertNotNull("Error in testGetStartXCorShouldNotBeNull", actual);
    }

    @Test
    public void testGetWidthShouldReturn30() throws Exception {
        int actual = this.testTankModel.getWidth();
        org.junit.Assert.assertEquals("Error in testGetWidthShouldReturn30", actual, 30);
    }

    @Test
    public void testGetHeightShouldReturn20() throws Exception {
        int actual = this.testTankModel.getHeight();
        org.junit.Assert.assertEquals("Error in testGetHeightShouldReturn20", actual, 20);
    }

    @Test
    public void testGetTankColShouldReturnCorrectColor() throws Exception {
        Color actual = this.testTankModel.getTankCol();
        org.junit.Assert.assertEquals("Error in testGetTankColShouldReturnCorrectColor",
                Color.DARKORANGE, actual);
    }

    @Test
    public void testGetBorderColorShouldReturnBlack() throws Exception {
        Color actual = this.testTankModel.getBorderColor();
        org.junit.Assert.assertEquals("Error in testGetBorderColorShouldReturnBlack",
                Color.BLACK, actual);
    }

    @Test
    public void testGetNozzleXShouldDefaultToZero() throws Exception {
        int actual = this.testTankModel.getNozzleX();
        org.junit.Assert.assertEquals("Error in testGetHeightShouldReturn20", actual, 0);
    }

    @Test
    public void testGetNozzleYShouldDefaultToZero() throws Exception {
        int actual = this.testTankModel.getNozzleY();
        org.junit.Assert.assertEquals("Error in testGetNozzleYShouldDefaultToZero", actual, 0);
    }

    @Test
    public void testGetNozzleLengthShouldReturn20() throws Exception {
        int actual = this.testTankModel.getNozzleLength();
        org.junit.Assert.assertEquals("Error in testGetNozzleLengthShouldReturn20",
                actual, 20);
    }

    @Test
    public void testGetNozzleHeight() throws Exception {
        int actual = this.testTankModel.getNozzleHeight();
        org.junit.Assert.assertEquals("Error in testGetNozzleHeightShouldReturn3",
                actual, 3);
    }

}