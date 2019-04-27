package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import service.Book;

public class BookListCell {
    private final StringProperty id;
    private final StringProperty isbn;
    private final StringProperty name;
    private final StringProperty author;

    public BookListCell(int id, Book book) {
        this.id = new SimpleStringProperty(String.valueOf(id));
        isbn = new SimpleStringProperty(book.getISBN());
        name = new SimpleStringProperty(book.getName());
        author = new SimpleStringProperty(book.getAuthor());
    }

    public String getId() {
        return id.get();
    }

    public String getIsbn() {
        return isbn.get();
    }

    public String getName() {
        return name.get();
    }

    public String getAuthor() {
        return author.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public StringProperty isbnProperty() {
        return isbn;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty authorProperty() {
        return author;
    }
}
