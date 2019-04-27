package stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import view.AddController;
import view.MainController;

import java.io.IOException;

public class Add extends Application {

    private Stage primaryStage = new Stage();
    private Parent root;
    private AddController controller;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * As non-main to start stage. Meanwhile give add main stage, when add close, update library size.
     * @param mainController use to re-get library size after add.
     */
    public void show(MainController mainController) {
        start(primaryStage);
        mainController.setSizeMessage();
    }

    @Override
    public void start(Stage primaryStage) {
        // Set primary stage
        primaryStage.setTitle("添加书目");
        primaryStage.setResizable(false);

        // Load layout and get controller
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AddLayout.fxml"));
            root = loader.load();
        } catch (IOException e) {
            System.out.println("IO: " + e);
        }
        primaryStage.setScene(new Scene(root, 480, 360));
        primaryStage.showAndWait();
    }
}
