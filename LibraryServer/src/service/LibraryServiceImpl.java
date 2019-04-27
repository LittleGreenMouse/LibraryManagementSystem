package service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class LibraryServiceImpl implements LibraryService {

    /**
     * The file name of local data file
     */
    final private String fileName;
    /**
     * All the books in library
     */
    private TreeMap<Integer, Book> bookList;
    /**
     * Local file's in stream and out stream
     */
    private FileInputStream inStream;
    private FileOutputStream outStream;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * constructor
     * initialize the in and out stream, bookList add librarySize
     */
    public LibraryServiceImpl() {
        fileName = "library.csv";
        try {
            // To make sure the file exists
            outStream = new FileOutputStream(fileName, true);
            inStream = new FileInputStream(fileName);
            in = new BufferedReader(new InputStreamReader(inStream));

            bookList = new TreeMap<>();

            String str;
            while ((str = in.readLine()) != null) {
                String[] buff = str.split(",");
                Book book = new Book(buff[1], buff[2], buff[3]);
                bookList.put(Integer.parseInt(buff[0]), book);
            }
        } catch (IOException e) {
            System.out.println("IO: " + e);
        } finally {
            try {
                if(in != null)  in.close();
                if(inStream != null)    inStream.close();
                if(outStream != null)   outStream.close();
            } catch (IOException e) {
                System.out.println("IO: " + e);
            }
        }
    }

    @Override
    public int getLibrarySize() throws RemoteException {
        return bookList.size();
    }

    @Override
    public Boolean add(Book book) throws RemoteException {
        if(bookList.size() == 0) {
            bookList.put(1, book);
        } else {
            int key = bookList.lastKey() + 1;
            bookList.put(key, book);
        }
        return true;
    }

    @Override
    public Book queryById(int bookId) throws RemoteException {
        return bookList.get(bookId);
    }

    @Override
    public TreeMap<Integer, Book> queryByName(String name) throws RemoteException {
        TreeMap<Integer, Book> ret = new TreeMap<>();
        Iterator iter = bookList.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry m = (Map.Entry) iter.next();
            Book book = (Book)m.getValue();
            if(book.getName().contains(name)) {
                ret.put((Integer)m.getKey(), (Book)m.getValue());
            }
        }
        return ret;
    }

    @Override
    public TreeMap<Integer, Book> queryByAuthor(String author) throws RemoteException {
        TreeMap<Integer, Book> ret = new TreeMap<>();
        Iterator iter = bookList.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry m = (Map.Entry) iter.next();
            Book book = (Book)m.getValue();
            if(book.getAuthor().equals(author)) {
                ret.put((Integer)m.getKey(), (Book)m.getValue());
            }
        }
        return ret;
    }

    @Override
    public TreeMap<Integer, Book> queryTotal() throws RemoteException {
        return bookList;
    }

    @Override
    public Boolean delete(int id) throws RemoteException {
        bookList.remove(id);
        return true;
    }

    @Override
    public Boolean saveData() throws RemoteException {
        try {
            outStream = new FileOutputStream(fileName);
            out = new PrintWriter(outStream);
            Iterator iter = bookList.entrySet().iterator();
            while(iter.hasNext()) {
                Map.Entry m = (Map.Entry) iter.next();
                Integer id = (Integer)m.getKey();
                Book book = (Book)m.getValue();
                out.println(id + "," + book.getISBN() + "," + book.getName() + "," + book.getAuthor());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
            return false;
        } finally {
            try {
                if(out != null) out.close();
                if(outStream != null)   outStream.close();
            } catch (IOException e) {
                System.out.println("IO: " + e);
            }
        }
        return true;
    }
}

