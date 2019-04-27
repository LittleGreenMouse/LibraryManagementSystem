package stage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import service.Book;
import view.BookListCell;
import view.BookListController;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BookList extends Application {

    private Stage primaryStage = new Stage();
    private Parent root;
    private BookListController controller;

    private ObservableList<BookListCell> cells = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * As non-main to start stage.
     */
    public void show(TreeMap<Integer, Book> bookList) {
        Iterator iter = bookList.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry m = (Map.Entry) iter.next();
            int id = (int) m.getKey();
            Book book = (Book) m.getValue();
            cells.add(new BookListCell(id, book));
        }
        start(primaryStage);
    }

    @Override
    public void start(Stage primaryStage) {
        // Set primary stage
        primaryStage.setTitle("查询结果");
        primaryStage.setResizable(false);

        // Load layout and get controller
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/BookListLayout.fxml"));
            root = loader.load();
            controller = loader.getController();
        } catch (IOException e) {
            System.out.println("IO: " + e);
        }
        primaryStage.setScene(new Scene(root, 480, 360));
        primaryStage.show();
        controller.serInfo(cells);
        controller.setTable();
    }
}
