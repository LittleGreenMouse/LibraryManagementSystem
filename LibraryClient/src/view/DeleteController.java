package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import service.Book;
import service.LibraryService;

import java.rmi.RemoteException;

public class DeleteController {

    @FXML
    public TextField id;
    @FXML
    public TextField bId;
    @FXML
    public TextField isbn;
    @FXML
    public TextField name;
    @FXML
    public TextField author;
    @FXML
    public Button deleteBtn;
    @FXML
    public Button clearBtn;

    private LibraryService library;
    private Book book;

    /**
     * Constructor
     * Initialize library to remote library
     */
    public DeleteController() {
        super();
        library = MainController.accessToLibrary();
    }

    /**
     * Disable delete button
     */
    public void disableBtn() {
        deleteBtn.setDisable(true);
        clearBtn.setDisable(true);
    }

    /**
     * Active delete button
     */
    public void activeBtn() {
        deleteBtn.setDisable(false);
        clearBtn.setDisable(false);
    }

    /**
     * Query the book according to input id and show it
     */
    @FXML
    public void query() {
        String message = id.getText();
        int bookId;
        if(!message.equals("")) {
            try {
                bookId = Integer.parseInt(message);
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR, "ID均为正整数，请检查输入的信息是否正确！");
                error.showAndWait();
                return;
            }
            try {
                book = library.queryById(bookId);
                bId.setText(message);
                try {
                    isbn.setText(book.getISBN());
                    name.setText(book.getName());
                    author.setText(book.getAuthor());
                    activeBtn();
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR, "该ID不存在");
                    error.showAndWait();
                }
            } catch (RemoteException e) {
                System.out.println("Remote: " + e);
            }
        } else {
            Alert error = new Alert(Alert.AlertType.ERROR, "请先输入查询信息！");
            error.showAndWait();
        }
    }

    /**
     * Clear textfield id to ""
     */
    @FXML
    public void clearId() {
        id.setText("");
    }

    /**
     * Delete book from remote library according to input id and disable delete button
     */
    @FXML
    public void delete() {
        String message = bId.getText();
        int bookId = Integer.parseInt(message);
        try {
            library.delete(bookId);
            clearInfo();
            disableBtn();
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "删除成功");
            alert.showAndWait();
        } catch (RemoteException e) {
            System.out.println("Remote: " + e);
        }
    }

    /**
     * Clear detail information to ""
     */
    @FXML
    public void clearInfo() {
        bId.setText("");
        isbn.setText("");
        name.setText("");
        author.setText("");
        disableBtn();
    }
}
