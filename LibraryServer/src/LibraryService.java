import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.TreeMap;

public interface LibraryService extends Remote {

    /**
     * Get total number of books in library
     * @return A integer which means the total number of books in library
     * @throws RemoteException if call fails
     */
    int getLibrarySize() throws RemoteException;

    /**
     * Add a book to library
     * @param book a Book object
     * @return A Boolean value: true or false
     * @throws RemoteException if call fails
     */
    Boolean add(Book book) throws RemoteException;

    /**
     * Query a book from library using id
     * @param bookId Id of book which is wanted
     * @return A Book object. If not found, return null
     * @throws RemoteException if call fails
     */
    Book queryById(int bookId) throws RemoteException;

    /**
     * Query some books from library using name. The name can be a regular expression
     * @param name Name of books which are wanted
     * @return A list of Book object. If not found, return null
     * @throws RemoteException if call fails
     */
    TreeMap<Integer, Book> queryByName(String name) throws RemoteException;

    /**
     * Query an author's all books in the library
     * @param author Author name
     * @return A list of Book object. If not found, return null
     * @throws RemoteException if call fails
     */
    TreeMap<Integer, Book> queryByAuthor(String author) throws RemoteException;

    /**
     * Query all books in the library
     * @return A list of Book object.
     * @throws RemoteException if call fails
     */
    TreeMap<Integer, Book> queryTotal() throws RemoteException;

    /**
     * Delete a book from library using id
     * @param id The id of book which is wanted to be deleted
     * @return A Boolean value: true or false
     * @throws RemoteException if call fails
     */
    Boolean delete(int id) throws RemoteException;

    /**
     * Save information of all books into server hard disk
     * @return A Boolean vale true or false
     */
    Boolean saveData() throws RemoteException;
}