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
    final Color BACKGROUND_COLOR = Color.WHITE;

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

    /* Returns true if the projectile is out of the bounds of the screen,
    * and false otherwise.
    */
    public boolean outOfScreen() {
        return (this.projectileModel.getPosX()<0 ||
                this.projectileModel.getPosX()>1199 ||
                this.projectileModel.getPosY()>820);
    }

    /*
    * Called when a projectile intersects the terrain. This method clears the
    * projectile from the screen.
    */
    public void destroyProjectile() {
        this.projectileColor = BACKGROUND_COLOR;
        this.projectileBody.setCenterX(0);
        this.projectileBody.setCenterY(0);
        this.projectileBody.setFill(this.projectileColor);
        this.getChildren().clear();
    }
}