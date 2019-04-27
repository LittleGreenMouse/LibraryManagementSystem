package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import service.Book;
import service.LibraryService;

import java.rmi.RemoteException;

public class AddController {
    @FXML
    public TextField isbn;
    @FXML
    public TextField name;
    @FXML
    public TextField author;

    private LibraryService library;
    Book book;

    /**
     * Constructor
     * Initialize library to remote library
     */
    public AddController() {
        super();
        library = MainController.accessToLibrary();
    }

    /**
     * Add a book to remote library according to user's input
     */
    @FXML
    public void add() {
        book = new Book(isbn.getText(), name.getText(), author.getText());
        try {
            library.add(book);
        } catch (RemoteException e) {
            System.out.println("Remote: " + e);
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "添加成功");
        alert.showAndWait();
        clear();
    }

    /**
     * Clear all input text to ""
     */
    @FXML
    public void clear() {
        isbn.setText("");
        name.setText("");
        author.setText("");
    }
}
