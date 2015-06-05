package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage stage;
    private Scene menu;
    private Scene map;

    /* Opens the main menu of the game */
    @Override
    public void start(final Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = (Parent)menuLoader.load();
        root.setFocusTraversable(true);
        this.menu = new Scene(root, 500, 500);

        this.stage.setTitle("Tanks?");
        this.stage.setScene(this.menu);
        this.stage.setResizable(false);
        this.stage.show();
        root.requestFocus();
    }

    /* Called when the user clicks the start button */
    public void onStartButton() throws Exception {
        startGame();
    }

    public void onQuitButton() throws Exception {
        System.exit(0);
    }

    /* Creates a new window and sets up a game of tanks */
    public void startGame() throws Exception {
        this.stage = new Stage();
        this.stage.setTitle("Tanks?");
        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("sample.fxml"));
        Parent root = (Parent)loader.load();
        root.setFocusTraversable(true);
        Controller controller = loader.getController();
        this.map = new Scene(root, 1200, 800);

        root.setOnKeyPressed(controller);

        stage.setScene(this.map);
        stage.setResizable(false);
        stage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
