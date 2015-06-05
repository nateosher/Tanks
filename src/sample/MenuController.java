package sample;

import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by oshern on 6/5/15.
 */
public class MenuController {
    public MenuController() {}

    /* Called when the user clicks the start button */
    public void onStartButton(Stage stage, Parent root) throws Exception {
        stage = new Stage();
        stage.setScene(this.map);
        stage.setResizable(false);
        stage.show();
        root.requestFocus();
    }
}
