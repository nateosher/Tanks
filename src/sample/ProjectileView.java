package sample;

/**
 * 5/28/15.
 */

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ProjectileView extends Group {
    private ProjectileModel projectileModel;
    private Circle projectileBody;
    private Color projectileColor;

    final Color DEFAULT_COLOR = Color.BLACK;
    final Color HIDDEN_COLOR = Color.DARKGREEN;

    public ProjectileView () {
        this.projectileColor = DEFAULT_COLOR;
    }

    /* Assigns the projectileView a projectileModel, and sets the initial
    * position to (0,0).
    */
    public void createNewProjectile(ProjectileModel projectileModel) {
        this.projectileColor = DEFAULT_COLOR;
        this.projectileModel = projectileModel;
        this.projectileBody = new Circle(this.projectileModel.getPosX(),
                this.projectileModel.getPosY(),5);
        this.projectileBody.setLayoutX(0);
        this.projectileBody.setLayoutY(0);
        this.projectileBody.setFill(this.projectileColor);
    }

    public ProjectileModel getProjectileModel(){
        return this.projectileModel;
    }

    /*
    * Updates the position of the projectileView, which is used during animation.
    */
    public void update() {
        this.getChildren().clear();
        this.projectileBody.setCenterX(this.projectileModel.getPosX());
        this.projectileBody.setCenterY(this.projectileModel.getPosY());
        this.getChildren().add(projectileBody);
    }

    /*
    * Called when a projectile intersects the terrain. This method clears the
    * projectile from the screen.
    */
    public void destroyProjectile() {
        this.projectileColor = HIDDEN_COLOR;
        this.projectileBody.setCenterX(1199);
        this.projectileBody.setCenterY(799);
        this.projectileBody.setFill(this.projectileColor);
        this.getChildren().clear();
    }
}