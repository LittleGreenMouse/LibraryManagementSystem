package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.TreeMap;

import service.Book;
import service.LibraryService;
import stage.Add;
import stage.BookInfo;
import stage.BookList;
import stage.Delete;

public class MainController {

    @FXML
    public TextField queryMessage;
    @FXML
    public RadioButton byID;
    @FXML
    public RadioButton byName;
    @FXML
    public RadioButton byAuthor;
    @FXML
    public Text sizeMessage;

    public ToggleGroup queryMethod;

    private Stage mainStage;
    private LibraryService library;
    private TreeMap<Integer, Book> bookList;
    private Book book;

    /**
     * Constructor
     * In it get access to remote library
     */
    public MainController() {
        super();
        library = accessToLibrary();
    }

    /**
     * Get remote library access
     */
    public static LibraryService accessToLibrary() {
        LibraryService library = null;
        try {
            // get register from localhost:1099
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            // get remote object by name
            library = (LibraryService) registry.lookup("rmi://localhost:1099/LibraryService");
            System.out.println("library service is gotten.");
        } catch (RemoteException e) {
            System.out.println("Remote: " + e);
        } catch (NotBoundException e) {
            System.out.println("Not bound: " + e);
        }
        return library;
    }

    public void setMainStage(Stage mainStage){
        this.mainStage = mainStage;
    }

    /**
     * Initialize library size to stage
     */
    @FXML
    public void setSizeMessage() {
        try {
            int size = library.getLibrarySize();
            String tmp = String.format("%04d", size);
            sizeMessage.setText("图书馆目前共有馆藏" + tmp + "种图书");
        } catch (RemoteException e) {
            System.out.println("Remote: " + e);
        }
    }

    /**
     * Open add stage
     */
    @FXML
    public void add() {
        stage.Add add = new Add();
        mainStage.setIconified(true);
        add.show(this);
        mainStage.setIconified(false);
    }

    /**
     * Open delete stage
     */
    @FXML
    public void delete() {
        stage.Delete delete = new Delete();
        mainStage.setIconified(true);
        delete.show(this);
        mainStage.setIconified(false);
    }

    /**
     * Call remote query method according to user's choice
     */
    @FXML
    public void query() {
        String message = queryMessage.getText();
        if(!message.equals("")) {
            try {
                if (byID.isSelected()) {
                    int id;
                    try {
                        id = Integer.parseInt(message);
                    } catch (Exception e) {
                        Alert error = new Alert(Alert.AlertType.ERROR, "ID均为正整数，请检查输入的信息是否正确！");
                        error.showAndWait();
                        return;
                    }
                    book = library.queryById(id);
                    try {
                        book.getISBN();
                    } catch (Exception e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR, "该ID不存在");
                        alert.showAndWait();
                        return;
                    }
                    stage.BookInfo bookInfo = new BookInfo();
                    bookInfo.show(id, book);
                } else if (byName.isSelected()) {
                    bookList = library.queryByName(message);
                    stage.BookList list = new BookList();
                    list.show(bookList);
                } else if (byAuthor.isSelected()) {
                    bookList = library.queryByAuthor(message);
                    stage.BookList list = new BookList();
                    list.show(bookList);
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
     * Call remote queryAll method
     */
    @FXML
    public void queryAll() {
        try {
            bookList = library.queryTotal();
            stage.BookList list = new BookList();
            list.show(bookList);
        } catch (RemoteException e) {
            System.out.println("Remote: " + e);
        }
    }

    /**
     * Call remote saveData method
     */
    public void saveData() {
        try {
            library.saveData();
        } catch (RemoteException e) {
            System.out.println("Remote: " + e);
        }
    }
}
