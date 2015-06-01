package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

/**
 * Created by Nate on 5/28/15.
 */
public class TankView extends Group{
    private TerrainModel terrainModel;
    private TankModel tank;
    private Rectangle tankBody;
    private Rectangle tankNozzle;

    public int getTerrainHeight(int xPos){ return this.terrainModel.getYPos(xPos); }

    public TankView(){

        // this.terrainModel = terrainModel;

    }

    public void setTankModel(TankModel tankModel) {
        this.tank = tankModel;
        this.tankBody = new Rectangle(this.getTank().getX(), this.getTank()
                .getY(), this.getTank().getWidth(), this.getTank().getHeight());
        this.tankBody.setFill(this.getTank().getTankCol());
        this.tankBody.setLayoutX(this.tank.getX());
        this.tankBody.setLayoutY(this.tank.getY());
    }

    public TankModel getTank(){ return this.tank; }

    public Rectangle getBody(){ return this.tankBody; }

    public void update(){
        this.getChildren().clear();
        this.tankBody.setLayoutX(this.tank.getX());
        this.tankBody.setLayoutY(this.tank.getY());
        this.getChildren().add(this.tankBody);
    }

}