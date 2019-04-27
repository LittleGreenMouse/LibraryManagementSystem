package stage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import service.Book;
import view.BookInfoController;

import java.io.IOException;

public class BookInfo extends Application {

    private Stage primaryStage = new Stage();
    private Parent root;
    private BookInfoController controller;

    private int id;
    private Book book;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * As non-main to start stage.
     */
    public void show(int id, Book book) {
        this.id = id;
        this.book = book;
        start(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set primary stage
        primaryStage.setTitle("书目信息");
        primaryStage.setResizable(false);

        // Load layout and get controller
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BookInfoLayout.fxml"));
            root = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            System.out.println("IO: " + e);
        }
        primaryStage.setScene(new Scene(root, 480, 360));
        primaryStage.show();
        controller.setInfo(id, book);
    }
}
