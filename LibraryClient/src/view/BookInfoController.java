package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import service.Book;

public class BookInfoController {
    @FXML
    public TextField id;
    @FXML
    public TextField isbn;
    @FXML
    public TextField name;
    @FXML
    public TextField author;

    /**
     * Initialize id and book information
     * @param id book's id
     * @param book book object
     */
    @FXML
    public void setInfo(int bookId, Book book) {
        id.setText(String.valueOf(bookId));
        isbn.setText(book.getISBN());
        name.setText(book.getName());
        author.setText(book.getAuthor());
    }
}
