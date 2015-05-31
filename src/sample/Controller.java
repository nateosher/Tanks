package sample;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;


public class Controller implements EventHandler<KeyEvent>{
    public Group TerrainGroup;
    private TerrainModel terrainModel;
    private TerrainView terrainView;
    public Group TankGroup;
    private TankView tankView;
    public Group ProjectileGroup;
    private ProjectileView projectileView;

    public Controller() {
    }

    public void initialize() {

        this.terrainModel = new TerrainModel();
        for (Node node : this.TerrainGroup.getChildren()) {
            this.terrainView = (TerrainView)node;
            this.terrainView.setTerrainModel(this.terrainModel);
            this.terrainView.update();
        }

        this.tankView = new TankView();
        for (Node node : this.TankGroup.getChildren()) {
            this.tankView = (TankView)node;
            // this.tankView.getTank().setY(this.terrainModel.getYPos(this.tankView.getTank().getX()));
            // tank_view.setTerrainModel(this.terrainModel);
            this.tankView.update();
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        int tankXPos = this.tankView.getTank().getX();
        int offset = this.tankView.getTank().getStartXCor() + 5;
        // System.out.println("Got here");
        if ((code == KeyCode.LEFT || code == KeyCode.A) && (this.tankView.getTank().getX() + offset - 5) >= 0) {
            // move tank left
            int newX = tankXPos - 5;
            int newY = this.terrainModel.getYPos(newX + offset);
            this.tankView.getTank().setX(newX);
            this.tankView.getTank().setY(newY);
            // System.out.println("Left");
            this.tankView.getBody().setLayoutX(newX);
            this.tankView.getBody().setLayoutY(newY);
            keyEvent.consume();

        } else if ((code == KeyCode.RIGHT || code == KeyCode.D) && (this.tankView.getTank().getX() + offset + 5) <= 1200) {
            // move tank right
            // System.out.println("Right");
            int newX = tankXPos + 5;
            int newY = this.terrainModel.getYPos(newX + offset);
            this.tankView.getTank().setX(newX);
            this.tankView.getTank().setY(newY);
            this.tankView.getBody().setLayoutX(newX);
            this.tankView.getBody().setLayoutY(newY);
            keyEvent.consume();
        } else if (code == KeyCode.F) {
            System.out.println("pew!");
            this.ProjectileGroup = new ProjectileView();
            for (Node node : this.ProjectileGroup.getChildren()) {
                System.out.println("there's a projectile node!");
                this.projectileView = (ProjectileView)node;
                //this.projectileView.getProjectileModel().setPosX(this.tankView.getTank().getX());
                //this.projectileView.getProjectileModel().setPosX(this.tankView.getTank().getY());
                this.projectileView.update();
            }
            keyEvent.consume();
        }
        int testXCor = this.tankView.getTank().getX();
        int testYCor = this.tankView.getTank().getY();
        String testString = String.format("Tank x cor: %s, Tank y cor: %s", (testXCor), (testYCor));
        System.out.println(testString) ;

    }
}
