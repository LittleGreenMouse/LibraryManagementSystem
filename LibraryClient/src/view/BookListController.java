package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class BookListController {

    @FXML
    public TableView<BookListCell> bookTable;
    @FXML
    public TableColumn<BookListCell, String> id;
    @FXML
    public TableColumn<BookListCell, String> isbn;
    @FXML
    public TableColumn<BookListCell, String> name;
    @FXML
    public TableColumn<BookListCell, String> author;

    private ObservableList<BookListCell> cells = FXCollections.observableArrayList();

    @FXML
    public void serInfo(ObservableList<BookListCell> cells) {
        this.cells = cells;
        id.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        isbn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        name.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        author.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
    }

    public void setTable() {
        bookTable.setItems(cells);
    }
}
