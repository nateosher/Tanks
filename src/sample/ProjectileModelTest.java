package sample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ravenpillmann on 6/1/15.
 */
public class ProjectileModelTest {

    @Test
    public void testGetPosXShouldReturn30() throws Exception {
        ProjectileModel projectileModel = new ProjectileModel(30, 40, 30.0, 1);
        int expected = 30;
        int actual = projectileModel.getPosX();
        org.junit.Assert.assertEquals("fail - getPosX() failed to return 30",
                expected, actual);
    }

    @Test
    public void testGetPosYShouldReturn40() throws Exception {
        ProjectileModel projectileModel = new ProjectileModel(30, 40, 30.0, 1);
        int expected = 40;
        int actual = projectileModel.getPosY();
        org.junit.Assert.assertEquals("fail - getPosY() failed to return 40",
                expected, actual);
    }

    @Test
    public void testGetVelXShouldReturn1() throws Exception {
        ProjectileModel projectileModel = new ProjectileModel(30, 40, 0.0, 1);
        double expected = 1;
        double actual = projectileModel.getVelX();
        org.junit.Assert.assertEquals("fail - getVelX() failed to return 1",
                expected, actual, 0.0);
    }

    @Test
    public void testGetVelYShouldReturn0() throws Exception {
        ProjectileModel projectileModel = new ProjectileModel(30, 40, 0.0, 1);
        double expected = 0.0;
        double actual = projectileModel.getVelY();
        org.junit.Assert.assertEquals("fail - getVelY() failed to return 0",
                expected, actual, 0.0);
    }

    @Test
    public void testGetDamageShouldReturn45() throws Exception {
        ProjectileModel projectileModel = new ProjectileModel(30, 40, 0.0, 1);
        double expected = 45;
        double actual = projectileModel.getDamage(0);
        org.junit.Assert.assertEquals("fail - getDamage() failed to return 45",
                expected, actual, 0.0);
    }

    @Test
    public void testGetBlastRadiusShouldReturn30() throws Exception {
        ProjectileModel projectileModel = new ProjectileModel(30, 40, 0.0, 1);
        double expected = 30.0;
        double actual = projectileModel.getBlastRadius();
        org.junit.Assert.assertEquals("fail - getBlastRadius() failed to return " +
                "30", expected, actual, 0.0);
    }
}