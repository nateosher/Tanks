package sample;

/**
 * 5/28/15.
 */

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ProjectileView extends Group {
    private ProjectileModel projectileModel;

    @FXML private Color projectileColor;
    @FXML private int projectileRadius;

    public ProjectileView (double pos_x, double pos_y, double vel_x, double vel_y,
                           int power) {
        this.projectileModel = new ProjectileModel(pos_x, pos_y, vel_x, vel_y,
                power);

    }

    public void update() {
        this.getChildren().clear();
        Circle projectile = new Circle(this.projectileModel.getPosX(),this.projectileModel.getPosY(),
                5);
        this.getChildren().add(projectile);
    }
}