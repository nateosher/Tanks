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
import java.util.*;


public class Controller implements EventHandler<KeyEvent>{
    public Group TerrainGroup;
    private TerrainModel terrainModel;
    private TerrainView terrainView;
    public Group TankGroup;
    private TankView activeTankView;
    private TankModel activeTankModel;
    public Group ProjectileGroup;
    private ProjectileView projectileView;
    private List<TankModel> tankModels;
    private List<TankView> tankViews;
    private int activeTankIndex;

    public Controller() {
    }

    public void initialize() {

        this.terrainModel = new TerrainModel();
        for (Node node : this.TerrainGroup.getChildren()) {
            this.terrainView = (TerrainView)node;
            this.terrainView.setTerrainModel(this.terrainModel);
            this.terrainView.update();
        }
        //this.tankModel = new TankModel();
        //this.tankView = new TankView();
        //this.tankView.setTankModel(this.tankModel);
        //this.tankView.getTank().setTerrainModel(this.terrainModel);
        //this.tankModel = this.tankView.getTank();

        this.tankModels = new ArrayList<TankModel>();
        this.tankViews = new ArrayList<TankView>();

        for (Node node : this.TankGroup.getChildren()) {
            TankModel tankModel = new TankModel();
            tankModel.setTerrainModel(this.terrainModel);

            TankView tankView = (TankView)node;
            tankView.setTankModel(tankModel);
            tankView.update();


            tankModel.setPositionByX(80);

            tankView.getBody().setLayoutX(tankModel.getX());
            tankView.getBody().setLayoutY(tankModel.getY());

            this.tankModels.add(tankModel);
            this.tankViews.add(tankView);

            this.activeTankModel = tankModel;
            this.activeTankView = tankView;
        }
        this.activeTankIndex = 1;


    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCode code = keyEvent.getCode();
        // System.out.println("Got here");
        if (code == KeyCode.LEFT || code == KeyCode.A) {
            //if (this.tankView.getTank().getX() + offset - 5) >= 0)
            this.activeTankModel.moveLeft();
            this.activeTankView.getBody().setLayoutX(this.activeTankModel.getX());
            this.activeTankView.getBody().setLayoutY(this.activeTankModel.getY());
            keyEvent.consume();

        } else if (code == KeyCode.RIGHT || code == KeyCode.D) {
            // move tank right
            // System.out.println("Right");
            this.activeTankModel.moveRight();
            this.activeTankView.getBody().setLayoutX(this.activeTankModel.getX());
            this.activeTankView.getBody().setLayoutY(this.activeTankModel.getY());
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
        } else if (code == KeyCode.Q) {
            System.out.println("my turn");
            if(this.activeTankIndex==0) {
                this.activeTankModel = this.tankModels.get(1);
                this.activeTankView = this.tankViews.get(1);
                this.activeTankIndex = 1;
            }
            else {
                this.activeTankModel = this.tankModels.get(0);
                this.activeTankView = this.tankViews.get(0);
                this.activeTankIndex = 0;
            }
        }
        int testXCor = this.activeTankModel.getX();
        int testYCor = this.activeTankModel.getY();
        String testString = String.format("Tank x cor: %s, Tank y cor: %s", (testXCor), (testYCor));
        System.out.println(testString) ;

    }
}
