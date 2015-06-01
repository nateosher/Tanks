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
    private Circle projectileBody;
    private Color projectileColor;

    final Color DEFAULT_COLOR = Color.PINK;

    @FXML private int projectileRadius;

    public ProjectileView () {
        this.projectileColor = DEFAULT_COLOR;

    }

    public void setProjectileModel(ProjectileModel projectileModel) {
        this.projectileModel = projectileModel;
        this.projectileBody = new Circle(this.projectileModel.getPosX(),
                this.projectileModel.getPosY(),5);
        this.projectileBody.setFill(this.projectileColor);
    }

    public ProjectileModel getProjectileModel() { return this.projectileModel; }

    public void update() {
        this.getChildren().clear();
        System.out.println("hi mom" + this.projectileModel.getPosX() + " and " + this.projectileModel.getPosX());
        this.projectileBody.setLayoutX(this.projectileModel.getPosX());
        this.projectileBody.setLayoutY(this.projectileModel.getPosY());
        this.getChildren().add(projectileBody);
    }
}