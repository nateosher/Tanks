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

    public ProjectileView () {
        this.projectileModel = new ProjectileModel();
        this.update();
    }

    public ProjectileModel getProjectileModel() { return this.projectileModel; }

    public void update() {
        this.getChildren().clear();
        this.projectileModel.updatePosX();
        this.projectileModel.updatePosY();
        Circle projectile = new Circle(this.projectileModel.getPosX(),this.projectileModel.getPosY(),
                5);
        this.getChildren().add(projectile);
    }
}