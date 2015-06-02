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
    final Color BACKGROUND_COLOR = Color.WHITE;

    @FXML private int projectileRadius;

    public ProjectileView () {
        this.projectileColor = DEFAULT_COLOR;
    }

    public void createNewProjectile(ProjectileModel projectileModel) {
        this.projectileColor = DEFAULT_COLOR;
        this.projectileModel = projectileModel;
        this.projectileBody = new Circle(this.projectileModel.getPosX(),
                this.projectileModel.getPosY(),5);
        this.projectileBody.setLayoutX(0);
        this.projectileBody.setLayoutY(0);
        this.projectileBody.setFill(this.projectileColor);
    }

    public void update() {
        this.getChildren().clear();

        this.projectileBody.setCenterX(this.projectileModel.getPosX());
        this.projectileBody.setCenterY(this.projectileModel.getPosY());
        this.getChildren().add(projectileBody);
    }

    public boolean outOfScreen() {
        return (this.projectileModel.getPosX()<0 ||
                this.projectileModel.getPosX()>1199);
    }

    public void destroyProjectile() {
        this.projectileColor = BACKGROUND_COLOR;
        this.projectileBody.setCenterX(0);
        this.projectileBody.setCenterY(0);
        this.projectileBody.setFill(this.projectileColor);
        this.getChildren().clear();
    }
}