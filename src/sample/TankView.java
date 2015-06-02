package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;
import javafx.scene.transform.Rotate;

/**
 * Created by Nate on 5/28/15.
 */
public class TankView extends Group{
    private TerrainModel terrainModel;
    private TankModel tank;
    private Rectangle tankBody;
    private Rectangle tankNozzle;
    private Rotate rPos;
    private Rotate rNeg;

    public int getTerrainHeight(int xPos){ return this.terrainModel.getYPos(xPos); }

    public TankView(){

    }

    public void setTankModel(TankModel tankModel) {
        this.tank = tankModel;
        this.tankBody = new Rectangle(this.getTank().getX(),
                this.getTank().getY(),
                this.getTank().getWidth(),
                this.getTank().getHeight());
        this.tankBody.setFill(this.getTank().getTankCol());
        this.tankBody.setLayoutX(this.tank.getX());
        this.tankBody.setLayoutY(this.tank.getY());

        this.tankNozzle = new Rectangle(this.getTank().getX(),
                this.getTank().getY(),
                this.getTank().getNozzleLength(),
                this.getTank().getNozzleHeight());
        this.tankNozzle.setFill(Color.BLACK);
        this.tankNozzle.setLayoutX(this.tank.getX());
        this.tankNozzle.setLayoutY(this.tank.getY());

        this.rPos = new Rotate(-1, this.tankNozzle.getLayoutX(), this.
                tankNozzle.getLayoutY());
        this.rNeg = new Rotate(1, this.tankNozzle.getLayoutX(), this.
                tankNozzle.getLayoutY());
    }

    public TankModel getTank(){ return this.tank; }

    public Rectangle getBody(){ return this.tankBody; }

    public Rectangle getNozzle() { return this.tankNozzle; }

    public Rotate getrPos(){ return this.rPos; }

    public Rotate getrNeg(){ return this.rNeg; }

    public void update(){
        this.getChildren().clear();
        this.tankBody.setLayoutX(this.tank.getX());
        this.tankBody.setLayoutY(this.tank.getY());
        this.tankNozzle.setLayoutX(this.tank.getX()
                + (this.tank.getWidth() / 2));
        this.tankNozzle.setLayoutY(this.tank.getY());
        this.getChildren().add(this.tankBody);
        this.getChildren().add(this.tankNozzle);
    }

}