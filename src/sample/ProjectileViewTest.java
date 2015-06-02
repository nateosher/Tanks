package sample;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ravenpillmann on 6/1/15.
 */
public class ProjectileViewTest {

    @Test
    public void testOutOfScreenShouldReturnFalse() throws Exception {
        ProjectileModel projectileModel = new ProjectileModel(-30, 40, 0, 1);
        ProjectileView projectileView = new ProjectileView();
        projectileView.createNewProjectile(projectileModel);
        org.junit.Assert.assertTrue("fail - outOfScreen() failed to return " +
                "true", projectileView.outOfScreen());
    }
}