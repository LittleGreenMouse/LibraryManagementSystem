package stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import view.MainController;

import java.io.IOException;

public class Main extends Application {

    private MainController mainController;
    private Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set primary stage
        primaryStage.setTitle("图书管理系统");
        primaryStage.setResizable(false);

        // Load layout and get controller
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/MainLayout.fxml"));
            root = loader.load();
            mainController = loader.getController();
        } catch (IOException e) {
            System.out.println("IO: " + e);
        }
        primaryStage.setScene(new Scene(root, 800, 450));
        primaryStage.show();

        // give controller stage
        mainController.setMainStage(primaryStage);

        // Initialize library size
        mainController.setSizeMessage();

        // Initialize query mode select buttons
        mainController.queryMethod = new ToggleGroup();
        mainController.byName.setToggleGroup(mainController.queryMethod);
        mainController.byID.setToggleGroup(mainController.queryMethod);
        mainController.byAuthor.setToggleGroup(mainController.queryMethod);
        mainController.byName.setSelected(true);
        mainController.byName.requestFocus();
    }

    /**
     * When close main stage, call remote saveData() to save all the changes during user's use
     * @throws Exception if unknown exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        mainController.saveData();
    }
}
