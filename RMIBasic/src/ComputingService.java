/*
 * Interface file
 * Define all the interface provided to client
 * This file should be placed both server and client
 * Note: in order to let program run successfully, please give the two interface file same package name
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComputingService extends Remote {

    /**
     * add two integer to a sum
     * @param a an integer
     * @param b an integer
     * @return the sum of a and b
     * @throws RemoteException if call fails
     */
    int add(int a, int b) throws RemoteException;

}