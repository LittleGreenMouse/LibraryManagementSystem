import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class LibraryServer {

    public static void main(String[] args) {

        try {
            LibraryService libraryServant = new LibraryServiceImpl();
            LibraryService stub = (LibraryService) UnicastRemoteObject.exportObject(libraryServant, 8889);

            LocateRegistry.createRegistry(1099);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("rmi://localhost:1099/LibraryService", stub);
            System.out.println("LibraryService is online.");
        } catch (RemoteException e) {
            System.out.println("Remote: " + e);
        } catch (AlreadyBoundException e) {
            System.out.println("Already bound: " + e);
        }

    }
}
